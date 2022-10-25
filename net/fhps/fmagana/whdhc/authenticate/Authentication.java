package net.fhps.fmagana.whdhc.authenticate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class Authentication {
	protected String uri;
	protected Type type;
	protected char[] token;

	public abstract boolean authenticate() throws IOException, InterruptedException;
	public abstract String generateParameters();

	protected int verifyToken(String uri) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(uri)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
	}

	public enum Type {
		LOGIN,
		API
	}
}