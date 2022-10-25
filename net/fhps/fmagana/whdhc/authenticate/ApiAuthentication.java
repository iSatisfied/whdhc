package net.fhps.fmagana.whdhc.authenticate;

import java.io.IOException;
import java.util.Objects;

public class ApiAuthentication extends Authentication {

    public ApiAuthentication(String uri, char[] token) {
        this.type = Type.API;
        this.uri = Objects.requireNonNull(uri);
        this.token = Objects.requireNonNull(token);
    }

    @Override
    public boolean authenticate() throws IOException, InterruptedException {
        String newUri = uri + "Session?" + generateParameters();
        return verifyToken(newUri) == 200;
    }

    @Override
    public String generateParameters() {
        return "apiKey=" + String.valueOf(token);
    }
}
