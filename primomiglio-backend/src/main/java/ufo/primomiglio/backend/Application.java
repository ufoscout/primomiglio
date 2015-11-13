package ufo.primomiglio.backend;

import io.vertx.core.Vertx;
import ufo.primomiglio.backend.web.WebServerVerticle;

/**
 * Created by ufo on 11/13/15.
 */
public class Application {

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(WebServerVerticle.class.getName());
    }
}
