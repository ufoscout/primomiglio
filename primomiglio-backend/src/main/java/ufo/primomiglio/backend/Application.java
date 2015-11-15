package ufo.primomiglio.backend;

import ufo.primomiglio.common.context.Context;
import ufo.primomiglio.common.context.PicoContainerContextImpl;

/**
 * Created by ufo on 11/13/15.
 */
public class Application {

    public static void main(String[] args) {
        startApplication(args);
    }

    public static Context startApplication(String... args) {
        Context context = new PicoContainerContextImpl();
        context.addToContext(ApplicationConfig::configureApplication);
        return context;
    }

}
