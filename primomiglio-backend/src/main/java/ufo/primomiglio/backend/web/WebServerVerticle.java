package ufo.primomiglio.backend.web;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.paralleluniverse.fibers.Suspendable;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.sync.Sync;
import io.vertx.ext.sync.SyncVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.StaticHandler;
import ufo.primomiglio.backend.usermanagement.provider.AuthUser;
import ufo.primomiglio.backend.usermanagement.provider.UMAuthProvider;
import ufo.primomiglio.backend.usermanagement.provider.UserContext;
import ufo.primomiglio.common.json.JacksonJsonSerializerService;
import ufo.primomiglio.common.jwt.JJWT_JWTServiceImpl;
import ufo.primomiglio.common.jwt.JWTService;

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
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.mountSubRouter("/rest", restRouter());

        loginFlow(router);

        // The default static file directory is webroot
        router.route().handler(StaticHandler.create());

        server.requestHandler(router::accept);

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

    private void loginFlow(Router router) {

        JWTService jwtService = new JJWT_JWTServiceImpl("secret".getBytes(), new JacksonJsonSerializerService());
        AuthProvider provider = new UMAuthProvider(jwtService);

        // protect the API
        router.route("/api/*").handler(JWTAuthHandler.create(provider, "/api/newToken"));

        // this route is excluded from the auth handler
        router.get("/api/newToken").handler(ctx -> {
            UserContext userContext = new UserContext();
            userContext.setUsername("francesco");
            userContext.setPermissions(new ArrayList<>());
            ctx.response().putHeader("Content-Type", "text/plain");
            ctx.response().end(jwtService.generate(userContext));
        });

        // this is the secret API
        router.get("/api/protected").handler(ctx -> {
            System.out.println("USER is: " + ctx.user());
            if ((ctx.user() != null) && (ctx.user() instanceof AuthUser)) {
                AuthUser user = (AuthUser) ctx.user();
                System.out.println("username is: " + user.userContext().getUsername());
            }
            ctx.response().putHeader("Content-Type", "text/plain");
            ctx.response().end("a secret you should keep for yourself...");
        });
    }

    private void modelLoginFlow(Router router) {
        // Create a JWT Auth Provider
        JWTAuth jwt = JWTAuth.create(vertx,
                new JsonObject().put("keyStore", new JsonObject().put("type", "jceks").put("path", "keystore.jceks").put("password", "secret")));

        // protect the API
        router.route("/api/*").handler(JWTAuthHandler.create(jwt, "/api/newToken"));

        // this route is excluded from the auth handler
        router.get("/api/newToken").handler(ctx -> {
            ctx.response().putHeader("Content-Type", "text/plain");
            ctx.response().end(jwt.generateToken(new JsonObject(), new JWTOptions().setExpiresInSeconds(60)));
        });

        // this is the secret API
        router.get("/api/protected").handler(ctx -> {
            System.out.println("USER is: " + ctx.user());
            if (ctx.user() != null) {
                System.out.println("username is: " + ctx.user().principal().encode());
            }
            ctx.response().putHeader("Content-Type", "text/plain");
            ctx.response().end("a secret you should keep for yourself...");
        });
    }

}
