package twitter.http;

import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpURLReader {

//    public String sendGetRequest(String urlString, Map<String, String> headers) throws IOException, Error {
//        return readFromIS(sendGetRequestAndGetIS(urlString, headers));
//    }

    public InputStream sendGetRequestAndGetIS(String urlString, Map<String, String> headers) throws IOException, Error {
        HttpURLConnection connection = openHttpConnection(urlString);
        setMethod(connection, "GET");
        setHeaders(connection, headers);
        return getISFromConnection(connection);
    }

//    public String sendPostRequest(String urlString, Map<String, String> headers, String query) throws IOException, Error {
//        return readFromIS(sendPostRequestAndGetIS(urlString, headers, query));
//    }

    public InputStream sendPostRequestAndGetIS(String urlString, Map<String, String> headers, String query) throws IOException, Error {
        HttpURLConnection connection = openHttpConnection(urlString);
        setMethod(connection, "POST");
        setHeaders(connection, headers);
        connection.setDoOutput(true);
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))) {
            out.write(query);
            out.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return getISFromConnection(connection);
    }

    private InputStream getISFromConnection(HttpURLConnection connection) throws IOException, Error {
        int responseCode = connection.getResponseCode();
        if (responseCode >= 200 && responseCode < 300) {
            return connection.getInputStream();
        } else {
            throw new Error(responseCode, connection.getResponseMessage(), readFromIS(connection.getErrorStream()));
        }
    }

//    private String readFromConnection(URLConnection connection) throws IOException {
//        return readFromIS(connection.getInputStream());
//    }

    private String readFromIS(InputStream is) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
            String singleLine;
            StringBuilder builder = new StringBuilder();
            while ((singleLine = in.readLine()) != null) {
                builder.append(singleLine);
                builder.append("\n");
            }
            return builder.toString();
        }
    }

    private HttpURLConnection openHttpConnection(String urlString) throws IOException {
        URL url = toURL(urlString);
        URLConnection urlConnection = url.openConnection();
        if (urlConnection instanceof HttpURLConnection) {
            return (HttpURLConnection) urlConnection;
        } else {
            throw new ConnectException("Not HTTP(S)");
        }
    }

    private void setMethod(HttpURLConnection connection, String method) {
        try {
            connection.setRequestMethod(method);
        } catch (ProtocolException ignored) { // unreachable state
        }
    }

    private void setHeaders(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private URL toURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed url: " + urlString);
        }
    }
}
