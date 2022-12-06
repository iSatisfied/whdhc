package net.fhps.fmagana.whdhc;

import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import net.fhps.fmagana.whdhc.authenticate.ApiAuthentication;
import net.fhps.fmagana.whdhc.authenticate.Authentication;

public class WHDHC {

	private final static String DEFAULT_URI = "https://support.fhps.net/helpdesk/WebObjects/Helpdesk.woa/ra/";
	private final static List<Endpoint> endpoints = List.of(Endpoint.values());

	static String serial = "5m4wtt2";
	static String query = "?qualifier=(serialNumber%3D'" + serial + "')";

    public static void main(String[] args) throws Exception {
		String url = "";
		String answer;
		Authentication auth = null;
		Endpoint endpoint = null;
		
		try (Scanner s = new Scanner(System.in)) {
			System.out.print("Do you want to use the default URL? (" + DEFAULT_URI + ") ");
			answer = s.nextLine();

			if (answer.equalsIgnoreCase("yes")) {
				url = DEFAULT_URI;
			} else {
				System.out.print("Enter URL: ");
				url = s.nextLine();
			}

			System.out.println("\n" + url);

			System.out.print("What method will you be using to authenticate? (API or Login) ");
			answer = s.nextLine();

			if (answer.equalsIgnoreCase("api")) {
				var env = System.getenv();
				String key = env.get("WHD_KEY");
				auth = new ApiAuthentication(url, key.toCharArray());

				System.out.println(auth.authenticate());
			} else if (answer.equalsIgnoreCase("login")) {
				System.out.println("TODO: Having issues with this authentication method");
				throw new Exception();
			} else {
				System.out.println(answer);
				throw new Exception("Not authenticated");
			}

			listEndpoints();
			System.out.print("Chose an Endpoint: ");
			answer = s.nextLine().toUpperCase();

			if (endpoints.contains(Endpoint.valueOf(answer))) {
				endpoint = Endpoint.valueOf(answer);
			} else {
				System.out.println("Not a valid endpoint '" + answer + "'");
				throw new Exception();
			}

		} catch (Exception e) {
			// Handle exception
			System.out.println("Something broke: " + e.getMessage());
			System.exit(1);
		}

		url += endpoint.getUri();
		System.out.println(url);

		url += query;
		System.out.println(url);

		url += "&" + auth.generateParameters();
		System.out.println(url);
		
		// String whd = DEFAULT_URI + "Assets";
		// String serial = "5m4wtt2";
		// String query = "?qualifier=(serialNumber%3D'" + serial + "')&apiKey=" + key;

		HttpClient client = HttpClient.newHttpClient();

		// System.out.println(whd + query);

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.GET()
			.build();
			
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
			.thenApply(HttpResponse::body)
			.thenAccept(System.out::println)
			.join();  
	}

	private static void listEndpoints() {
		for (Endpoint endpoint : endpoints) {
			System.out.println(endpoint);
		}
	}
	
	// public void getURIs(HttpClient client, List<URI> uris) {
	// 	List<HttpRequest> requests = uris.stream()
    //         .map(HttpRequest::newBuilder)
    //         .map(reqBuilder -> reqBuilder.build())
    //         .collect();

	// 	CompletableFuture.allOf(requests.stream()
    //         .map(request -> client.sendAsync(request, asString()))
    //         .toArray(CompletableFuture<?>[]::new))
    //         .join();
	// }
}