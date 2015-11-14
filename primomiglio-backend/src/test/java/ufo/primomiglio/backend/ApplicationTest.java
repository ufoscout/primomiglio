package ufo.primomiglio.backend;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApplicationTest extends BaseAbstractTest {

	@Test
	public void contextShouldLoad() {
	    assertNotNull(getContext());
	}

}
