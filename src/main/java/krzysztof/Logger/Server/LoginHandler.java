package krzysztof.Logger.Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "";
        String method = httpExchange.getRequestMethod();


        if(method.equals("GET")){

            response = "<html><body>" +
                    "<form method=\"POST\">\n" +
                    "<h1>Log In!</h1><br><br>\n" +
                    "  Login:<br>\n" +
                    "  <input type=\"text\" name=\"name\">\n" +
                    "  <br><br><br>\n" +
                    "  Password:<br>\n" +
                    "  <input type=\"password\" name=\"password\">\n" +
                    "  <br><br>\n" +
                    "  <input type=\"submit\" value=\"Log in!\">\n" +
                    "</form> " +
                    "</body></html>";
        }

        // If the form was submitted, retrieve it's content.
        if(method.equals("POST")){
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
            Map inputs = parseFormData(formData);



            response = "<html><body>" +
                    "<h1>You've logged, "+ inputs.get("name") + " !<br></h1>" +
                    "  <br><input type=\"submit\" value=\"Log out!\">\n" +
                    "</body><html>";
        }

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    private static Map<String, String> parseFormData(String formData) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            // We have to decode the value because it's urlencoded. see: https://en.wikipedia.org/wiki/POST_(HTTP)#Use_for_submitting_web_forms
            String value = new URLDecoder().decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }
}
