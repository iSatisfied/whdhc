package net.fhps.fmagana.whdhc;

public class URIBuilder {
	
	public boolean chooseRestEndpoint(Endpoint endpoint) {
		return validateEndpoint(endpoint) != null;
	}

	private String validateEndpoint(Endpoint endpoint) {
		return endpoint.getUri();
	}
}