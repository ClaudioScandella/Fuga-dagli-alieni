package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LOGTest {
	
	private LOG log;

	@Before
	public void setUp() throws Exception {
		log = new LOG();
	}

	@Test
	public void testLOG() {
		assertTrue(log instanceof LOG);
	}

	@Test
	public void testGetLOG() {
		log.setLOG(1, 2, 3, "ciao");
		assertEquals(log.getLOG(1, 2, 3), "ciao");
	}

	@Test
	public void testAzzeraLog() {
		log.setLOG(3, 2, 4, "ciao");
		log.azzeraLog(3, 2);
		assertEquals(log.getLOG(3, 2, 4), "");
	}

}
