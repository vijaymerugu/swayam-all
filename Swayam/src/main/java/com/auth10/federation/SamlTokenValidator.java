
package com.auth10.federation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.EncryptedAssertion;
import org.opensaml.saml2.encryption.Decrypter;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.encryption.DecryptionException;
import org.opensaml.xml.encryption.InlineEncryptedKeyResolver;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.SecurityTestHelper;
import org.opensaml.xml.security.credential.CollectionCredentialResolver;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.opensaml.xml.security.keyinfo.KeyInfoCredentialResolver;
import org.opensaml.xml.security.keyinfo.KeyInfoHelper;
import org.opensaml.xml.security.keyinfo.StaticKeyInfoCredentialResolver;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.KeyInfo;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.impl.ExplicitKeySignatureTrustEngine;
import org.opensaml.xml.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@SuppressWarnings("deprecation")
public class SamlTokenValidator {
	public static final int MAX_CLOCK_SKEW_IN_MINUTES = 3;
	private List<String> trustedIssuers;
	private List<URI> audienceUris;
	private boolean validateExpiration = true;
	private String thumbprint;

	static final Logger logger = LoggerFactory
			.getLogger(SamlTokenValidator.class);

	public SamlTokenValidator() throws ConfigurationException {
		this(new ArrayList<String>(), new ArrayList<URI>());
	}

	public SamlTokenValidator(List<String> trustedIssuers,
			List<URI> audienceUris) throws ConfigurationException {
		super();
		this.trustedIssuers = trustedIssuers;
		this.audienceUris = audienceUris;
		DefaultBootstrap.bootstrap();
	}

	public List<String> getTrustedIssuers() {
		return this.trustedIssuers;
	}

	public void setTrustedIssuers(List<String> trustedIssuers) {
		this.trustedIssuers = trustedIssuers;
	}

	public List<URI> getAudienceUris() {
		return this.audienceUris;
	}

	public void setAudienceUris(List<URI> audienceUris) {
		this.audienceUris = audienceUris;
	}

	public boolean getValidateExpiration() {
		return validateExpiration;
	}

	public void setValidateExpiration(boolean value) {
		this.validateExpiration = value;
	}

	public List<Claim> validate(String envelopedToken)
			throws ParserConfigurationException, SAXException, IOException,
			FederationException, ConfigurationException, CertificateException,
			KeyException, SecurityException, ValidationException,
			UnmarshallingException, URISyntaxException,
			NoSuchAlgorithmException {

		SignableSAMLObject samlToken;

		if (envelopedToken.contains("RequestSecurityTokenResponse")) {
			samlToken = getSamlTokenFromRstr(envelopedToken);
		} else {
			samlToken = getSamlTokenFromSamlResponse(envelopedToken);
		}

		boolean valid = validateToken(samlToken);

		logger.debug("token=" + samlToken);

		if (!valid) {
			throw new FederationException("Invalid signature");
		}

		boolean trusted = false;

		for (String issuer : this.trustedIssuers) {
			trusted |= validateIssuerUsingSubjectName(samlToken, issuer);
		}

		logger.debug("trusted=" + trusted + " after checking subject name");

		if (!trusted && (this.thumbprint != null)) {
			trusted = validateIssuerUsingCertificateThumbprint(samlToken,
					this.thumbprint);
		}

		logger.debug("trusted=" + trusted + " after checking cert thumbprint");

		if (!trusted) {
			throw new FederationException(
					"The token was issued by an authority that is not trusted");
		}

		String address = null;
		if (samlToken instanceof org.opensaml.saml1.core.Assertion) {
			address = getAudienceUri((org.opensaml.saml1.core.Assertion) samlToken);
		}

		if (samlToken instanceof org.opensaml.saml2.core.Assertion) {
			address = getAudienceUri((org.opensaml.saml2.core.Assertion) samlToken);
		}

		URI audience = new URI(address);

		boolean validAudience = false;
		for (URI audienceUri : audienceUris) {
			validAudience |= audience.equals(audienceUri);
		}

		if (!validAudience) {
			throw new FederationException(String.format(
					"The token applies to an untrusted audience: %s",
					new Object[] { audience }));
		}

		List<Claim> claims = null;
		if (samlToken instanceof org.opensaml.saml1.core.Assertion) {
			logger.debug("getting claims from saml1");
			claims = getClaims((org.opensaml.saml1.core.Assertion) samlToken);
		}

		if (samlToken instanceof org.opensaml.saml2.core.Assertion) {
			logger.debug("getting claims from saml2");
			claims = getClaims((org.opensaml.saml2.core.Assertion) samlToken);
		}

		if (this.validateExpiration) {

			boolean expired = false;
			if (samlToken instanceof org.opensaml.saml1.core.Assertion) {
				Instant notBefore = ((org.opensaml.saml1.core.Assertion) samlToken)
						.getConditions().getNotBefore().toInstant();
				Instant notOnOrAfter = ((org.opensaml.saml1.core.Assertion) samlToken)
						.getConditions().getNotOnOrAfter().toInstant();
				expired = validateExpiration(notBefore, notOnOrAfter);
			}

			if (samlToken instanceof org.opensaml.saml2.core.Assertion) {
				Instant notBefore = ((org.opensaml.saml2.core.Assertion) samlToken)
						.getConditions().getNotBefore().toInstant();
				Instant notOnOrAfter = ((org.opensaml.saml2.core.Assertion) samlToken)
						.getConditions().getNotOnOrAfter().toInstant();
				expired = validateExpiration(notBefore, notOnOrAfter);
			}

			if (expired) {
				throw new FederationException("The token has been expired");
			}
		}

		return claims;
	}

	private static SignableSAMLObject getSamlTokenFromSamlResponse(
			String samlResponse) throws ParserConfigurationException,
			SAXException, IOException, UnmarshallingException {
		Document document = getDocument(samlResponse);

		Unmarshaller unmarshaller = Configuration.getUnmarshallerFactory()
				.getUnmarshaller(document.getDocumentElement());
		org.opensaml.saml2.core.Response response = (org.opensaml.saml2.core.Response) unmarshaller
				.unmarshall(document.getDocumentElement());
		SignableSAMLObject samlToken = response.getAssertions().get(0);

		return samlToken;
	}

	private static Assertion decryptAssertion(
			EncryptedAssertion encryptedAssertion) {
		logger.debug("loading private key file");
		// Load the private key file.
		InputStream pkis = SamlTokenValidator.class
				.getResourceAsStream("/rsa_private_key.pk8");

		if (pkis == null) {
			throw new RuntimeException("Couldn't load key file");
		}

		try {
			byte[] encodedPrivateKey = IOUtils.toByteArray(pkis);
			// Create the private key.
			logger.debug("encoded private key size=" + encodedPrivateKey.length);
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
					encodedPrivateKey);
			RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance(
					"RSA").generatePrivate(privateKeySpec);

			// Create the credentials.
			BasicX509Credential decryptionCredential = new BasicX509Credential();
			decryptionCredential.setPrivateKey(privateKey);

			// Create a decrypter.
			Decrypter decrypter = new Decrypter(null,
					new StaticKeyInfoCredentialResolver(decryptionCredential),
					new InlineEncryptedKeyResolver());

			logger.debug("decrypting using key");
			Assertion decryptedAssertion = decrypter
					.decrypt(encryptedAssertion);

			return decryptedAssertion;

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (DecryptionException e) {
			throw new RuntimeException(e);
		}

	}

	private static SignableSAMLObject getSamlTokenFromRstr(String rstr)
			throws ParserConfigurationException, SAXException, IOException,
			UnmarshallingException, FederationException {
		Document document = getDocument(rstr);
		logger.debug("response=" + rstr);
		Element samlTokenElement = null;

		String xpath = "//*[local-name() = 'EncryptedAssertion']";

		NodeList nodes = null;

		try {
			nodes = org.apache.xpath.XPathAPI.selectNodeList(document, xpath);
		} catch (TransformerException e) {
			logger.warn(e.getMessage(), e);
		}

		if (nodes != null) {

			if (nodes.getLength() > 0) { // Assertion is encrypted
				logger.debug("decrypting assertion");
				EncryptedAssertion encryptedAssertion = getEncryptedAssertion(nodes
						.item(0));
				Assertion decryptedAssertion = decryptAssertion(encryptedAssertion);
				samlTokenElement = marshall(decryptedAssertion);

			} else { // Check for decrypted assertion
				logger.debug("reading assertion");
				xpath = "//*[local-name() = 'Assertion']";
				try {
					nodes = org.apache.xpath.XPathAPI.selectNodeList(document,
							xpath);
					samlTokenElement = (Element) nodes.item(0);
				} catch (TransformerException e) {
					logger.warn(e.getMessage(), e);
				}
			}
		}

		if (samlTokenElement == null)
			throw new RuntimeException("Unable to get Assertion from request");

		Unmarshaller unmarshaller = Configuration.getUnmarshallerFactory()
				.getUnmarshaller(samlTokenElement);
		SignableSAMLObject samlToken = (SignableSAMLObject) unmarshaller
				.unmarshall(samlTokenElement);

		return samlToken;
	}

	private static EncryptedAssertion getEncryptedAssertion(Node node) {
		logger.debug("unmarshalling from document");
		// Unmarshall
		UnmarshallerFactory unmarshallerFactory = Configuration
				.getUnmarshallerFactory();
		Unmarshaller unmarshaller = unmarshallerFactory
				.getUnmarshaller((Element) node);
		EncryptedAssertion encryptedAssertion;
		try {
			encryptedAssertion = (EncryptedAssertion) unmarshaller
					.unmarshall((Element) node);
		} catch (UnmarshallingException e) {
			throw new RuntimeException(e);
		}
		return encryptedAssertion;
	}

	private static Element marshall(Assertion assertion) {
		logger.debug("marshalling");
		// Unmarshall
		MarshallerFactory marshallerFactory = Configuration
				.getMarshallerFactory();
		Marshaller marshaller = marshallerFactory.getMarshaller(assertion);
		Element element;
		try {
			element = marshaller.marshall(assertion);
			// Uncomment to see the assertion as XML
			// logAssertion(element);
		} catch (MarshallingException e) {
			throw new RuntimeException(e);
		}
		return element;
	}

	private static void logAssertion(Node node) {

		Document newXmlDocument;
		try {
			newXmlDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
		Node copyNode = newXmlDocument.importNode(node, true);
		newXmlDocument.appendChild(copyNode);
		printXmlDocument(newXmlDocument);
	}

	private static void printXmlDocument(Document document) {
		DOMImplementationLS domImplementationLS = (DOMImplementationLS) document
				.getImplementation();
		LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
		String string = lsSerializer.writeToString(document);
		logger.debug(string);
	}

	private static String getAudienceUri(
			org.opensaml.saml2.core.Assertion samlAssertion) {
		org.opensaml.saml2.core.Audience audienceUri = samlAssertion
				.getConditions().getAudienceRestrictions().get(0)
				.getAudiences().get(0);
		return audienceUri.getAudienceURI();
	}

	private static String getAudienceUri(
			org.opensaml.saml1.core.Assertion samlAssertion) {

		org.opensaml.saml1.core.Audience audienceUri = samlAssertion
				.getConditions().getAudienceRestrictionConditions().get(0)
				.getAudiences().get(0);
		return audienceUri.getUri();
	}

	private boolean validateExpiration(Instant notBefore, Instant notOnOrAfter) {

		Instant now = new Instant();
		Duration skew = new Duration(MAX_CLOCK_SKEW_IN_MINUTES * 60 * 1000);

		if (now.plus(skew).isBefore(notBefore)) {
			return true;
		}

		if (now.minus(skew).isAfter(notOnOrAfter)) {
			return true;
		}

		return false;
	}

	private static boolean validateToken(SignableSAMLObject samlToken)
			throws SecurityException, ValidationException,
			ConfigurationException, UnmarshallingException,
			CertificateException, KeyException {

		samlToken.validate(true);
		Signature signature = samlToken.getSignature();
		KeyInfo keyInfo = signature.getKeyInfo();
		X509Certificate pubKey = KeyInfoHelper.getCertificates(keyInfo).get(0);

		BasicX509Credential cred = new BasicX509Credential();
		cred.setEntityCertificate(pubKey);
		cred.setEntityId("signing-entity-ID");

		ArrayList<Credential> trustedCredentials = new ArrayList<Credential>();
		trustedCredentials.add(cred);

		CollectionCredentialResolver credResolver = new CollectionCredentialResolver(
				trustedCredentials);

		KeyInfoCredentialResolver kiResolver = SecurityTestHelper
				.buildBasicInlineKeyInfoResolver();
		ExplicitKeySignatureTrustEngine engine = new ExplicitKeySignatureTrustEngine(
				credResolver, kiResolver);

		CriteriaSet criteriaSet = new CriteriaSet();
		criteriaSet.add(new EntityIDCriteria("signing-entity-ID"));

		return engine.validate(signature, criteriaSet);
	}

	private static boolean validateIssuerUsingSubjectName(
			SignableSAMLObject samlToken, String subjectName)
			throws UnmarshallingException, ValidationException,
			CertificateException {

		Signature signature = samlToken.getSignature();
		KeyInfo keyInfo = signature.getKeyInfo();
		X509Certificate pubKey = KeyInfoHelper.getCertificates(keyInfo).get(0);

		String issuer = pubKey.getSubjectDN().getName();
		return issuer.equals(subjectName);
	}

	private static boolean validateIssuerUsingCertificateThumbprint(
			SignableSAMLObject samlToken, String thumbprint)
			throws UnmarshallingException, ValidationException,
			CertificateException, NoSuchAlgorithmException {

		Signature signature = samlToken.getSignature();
		KeyInfo keyInfo = signature.getKeyInfo();
		X509Certificate pubKey = KeyInfoHelper.getCertificates(keyInfo).get(0);

		String thumbprintFromToken = SamlTokenValidator
				.getThumbPrintFromCert(pubKey);

		return thumbprintFromToken.equalsIgnoreCase(thumbprint);
	}

	private static String getThumbPrintFromCert(X509Certificate cert)
			throws NoSuchAlgorithmException, CertificateEncodingException {

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] der = cert.getEncoded();
		md.update(der);
		byte[] digest = md.digest();
		return hexify(digest);
	}

	private static String hexify(byte bytes[]) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		StringBuffer buf = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; ++i) {
			buf.append(hexDigits[(bytes[i] & 0xf0) >> 4]);
			buf.append(hexDigits[bytes[i] & 0x0f]);
		}

		return buf.toString();
	}

	private static List<Claim> getClaims(
			org.opensaml.saml2.core.Assertion samlAssertion)
			throws SecurityException, ValidationException,
			ConfigurationException, UnmarshallingException,
			CertificateException, KeyException {

		ArrayList<Claim> claims = new ArrayList<Claim>();

		List<org.opensaml.saml2.core.AttributeStatement> attributeStmts = samlAssertion
				.getAttributeStatements();

		for (org.opensaml.saml2.core.AttributeStatement attributeStmt : attributeStmts) {
			List<org.opensaml.saml2.core.Attribute> attributes = attributeStmt
					.getAttributes();

			for (org.opensaml.saml2.core.Attribute attribute : attributes) {
				String claimType = attribute.getName();
				String claimValue = getValueFrom(attribute.getAttributeValues());
				claims.add(new Claim(claimType, claimValue));
			}
		}

		return claims;
	}

	private static List<Claim> getClaims(
			org.opensaml.saml1.core.Assertion samlAssertion)
			throws SecurityException, ValidationException,
			ConfigurationException, UnmarshallingException,
			CertificateException, KeyException {

		ArrayList<Claim> claims = new ArrayList<Claim>();

		List<org.opensaml.saml1.core.AttributeStatement> attributeStmts = samlAssertion
				.getAttributeStatements();

		for (org.opensaml.saml1.core.AttributeStatement attributeStmt : attributeStmts) {
			List<org.opensaml.saml1.core.Attribute> attributes = attributeStmt
					.getAttributes();

			for (org.opensaml.saml1.core.Attribute attribute : attributes) {
				String claimType = attribute.getAttributeNamespace() + "/"
						+ attribute.getAttributeName();
				String claimValue = getValueFrom(attribute.getAttributeValues());
				claims.add(new Claim(claimType, claimValue));
			}
		}

		return claims;
	}

	private static String getValueFrom(List<XMLObject> attributeValues) {

		StringBuffer buffer = new StringBuffer();

		for (XMLObject value : attributeValues) {
			if (buffer.length() > 0)
				buffer.append(',');
			buffer.append(value.getDOM().getTextContent());
		}

		return buffer.toString();
	}

	private static Document getDocument(String doc)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder documentbuilder = factory.newDocumentBuilder();
		return documentbuilder.parse(new InputSource(new StringReader(doc)));
	}

	public void setThumbprint(String thumbprint) {
		this.thumbprint = thumbprint;
	}

	public String getThumbprint() {
		return thumbprint;
	}
}
