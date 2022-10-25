package net.fhps.fmagana.whdhc.authenticate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Objects;

public class LoginAuthentication extends Authentication {

    private String username;

    public LoginAuthentication(String uri, String username, char[] token) {
        this.type = Type.LOGIN;
        this.uri = Objects.requireNonNull(uri);
        this.username = Objects.requireNonNull(username);
        this.token = Objects.requireNonNull(token);
    }

    @Override
    public boolean authenticate() throws IOException, InterruptedException {
        String newUri = uri + "Session?" + generateParameters();
        System.out.println(newUri);
    
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(newUri)).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

        return response.statusCode() == 200;
    }

    @Override
    public String generateParameters() {
        StringBuilder parameters = new StringBuilder("login=&password=");
        parameters.insert(6, username);
        parameters.append(token);

        return parameters.toString();
    }
}
