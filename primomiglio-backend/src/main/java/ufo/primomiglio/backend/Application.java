package ufo.primomiglio.backend;

import ufo.primomiglio.backend.config.ApplicationConfig;
import ufo.primomiglio.backend.config.WebConfig;
import ufo.primomiglio.common.config.Context;

/**
 * Created by ufo on 11/13/15.
 */
public class Application {

    public static void main(String[] args) {
        startApplication(args);
    }

    public static Context startApplication(String... args) {
        Context context = Context.newContext();
        ApplicationConfig.configureApplication(context);
        WebConfig.configureWeb(context);
        return context;
    }

}
