package ufo.primomiglio.backend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.paralleluniverse.fibers.Suspendable;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.sync.Sync;
import io.vertx.ext.sync.SyncVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Created by ufo on 11/13/15.
 */
public class WebServerVerticle extends SyncVerticle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int port = 8080;

    @Override
    @Suspendable
    public void start(final Future<Void> startFuture) {
        logger.info("Starting web server with port {}", port);

        // Create the HTTP server
        HttpServer server = vertx.createHttpServer(new HttpServerOptions().setHost("localhost").setPort(port));

        // Setup the Router - this is used to route requests on different paths
        // to different handlers
        Router rm = Router.router(vertx);

        rm.route().handler(BodyHandler.create());

        rm.mountSubRouter("/rest", restRouter());

        //The default static file directory is webroot
        rm.route("/*").handler(StaticHandler.create());

        server.requestHandler(rm::accept);

        server.listen(res -> {
            // When the web server is listening we'll say that the start of this
            // verticle is complete
            if (res.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(res.cause());
            }
        });

        logger.info("Web server is listening");
    }

    private Router restRouter() {
        Router restAPI = Router.router(vertx);

        restAPI.get("/*").handler(Sync.fiberHandler(context -> {
            HttpServerRequest request = context.request();
            String text = request.params().get("text");
            logger.debug("Received get param [{}]", text);
            request.response().end(text);
            logger.debug("Response sent");
        }));

        return restAPI;
    }

}
