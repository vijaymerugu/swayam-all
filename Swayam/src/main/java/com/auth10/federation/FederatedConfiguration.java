
package com.auth10.federation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FederatedConfiguration {
	private static FederatedConfiguration instance = null;
	private Properties properties;

	public static FederatedConfiguration getInstance() {
		if (instance == null) {
			synchronized (FederatedConfiguration.class) {
				instance = load();
			}
		}

		return instance;
	}

	private static FederatedConfiguration load() {
		java.util.Properties props = new java.util.Properties();

		try {
			InputStream is = FederatedConfiguration.class.getResourceAsStream("/federation.properties");
			
			props.load(is);
		} catch (IOException e) {
			throw new RuntimeException("Configuration could not be loaded", e);
		}

		return new FederatedConfiguration(props);
	}

	private FederatedConfiguration(Properties properties) {
		this.properties = properties;
	}

	public String getStsUrl() {
		return this.properties.getProperty("federation.trustedissuers.issuer");
	}

	public String getStsFriendlyName() {
		return this.properties.getProperty("federation.trustedissuers.friendlyname");
	}
	
	public String getThumbprint() {
		return this.properties.getProperty("federation.trustedissuers.thumbprint");
	}

	public String getRealm() {
		return this.properties.getProperty("federation.realm");
	}

	public String getReply() {
		return this.properties.getProperty("federation.reply");
	}

	public String[] getTrustedIssuers() {
		String trustedIssuers = this.properties.getProperty("federation.trustedissuers.subjectname");
		
		if (trustedIssuers != null)
			return trustedIssuers.split("\\|");
		else
			return null;
	}

	public String[] getAudienceUris() {
		return this.properties.getProperty("federation.audienceuris").split("\\|");
	}
	
	public Boolean getEnableManualRedirect() {
		String manual = this.properties.getProperty("federation.enableManualRedirect");
		if (manual != null && Boolean.parseBoolean(manual)) {
			return true;
		}
		
		return false;
	}

}
