

package com.auth10.federation;

public class FederationException extends Exception {
	private static final long serialVersionUID = 1L;

	public FederationException() {
	}

	public FederationException(final String s) {
		super(s);
	}

	public FederationException(final Throwable throwable) {
		super(throwable);
	}

	public FederationException(final String s, final Throwable throwable) {
		super(s, throwable);
	}
}
