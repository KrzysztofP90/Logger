package krzysztof.Logger.Server;

import com.sun.net.httpserver.HttpExchange;


import java.net.HttpCookie;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CookieHandler {

    private static final String SESSION_COOKIE_NAME = "sessionId";
    CookieHelper cookieHelper;

    public CookieHandler() {
        cookieHelper = new CookieHelper();
    }

    public String createCookieResponse(HttpExchange httpExchange) {

        Optional<HttpCookie> cookie = getSessionIdCookie(httpExchange);


        if (cookie.isPresent()) {  // Cookie already exists

        } else { // Create a new cookie

            String sessionId = createRandomId();
            cookie = Optional.of(new HttpCookie(SESSION_COOKIE_NAME, sessionId));
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.get().toString());
        }
        return  cookie.get().getValue();
    }



    public Optional<HttpCookie> getSessionIdCookie(HttpExchange httpExchange){
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        List<HttpCookie> cookies = cookieHelper.parseCookies(cookieStr);
        return cookieHelper.findCookieByName(SESSION_COOKIE_NAME, cookies);
    }


    private String createRandomId() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i< 15; i++) {
            String randInt = String.valueOf(random.nextInt(99));
            sb.append(randInt);
        }
        return sb.toString();
    }
}
