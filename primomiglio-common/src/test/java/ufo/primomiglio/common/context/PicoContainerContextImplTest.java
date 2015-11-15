package ufo.primomiglio.common.context;

import org.junit.Before;
import org.junit.Test;
import ufo.primomiglio.common.BaseAbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ufo on 11/15/15.
 */
public class PicoContainerContextImplTest extends BaseAbstractTest {

    private Context context;

    @Before
    public void setUp() {
        context = new PicoContainerContextImpl();
    }

    @Test
    public void should_add_class_type() throws InterruptedException {
        context.addComponent(String.class);
        assertNotNull(context.getComponent(String.class));
    }

    @Test
    public void should_add_class_instance() throws InterruptedException {
        context.addComponent("hello");
        assertEquals("hello", context.getComponent(String.class));
    }

}
