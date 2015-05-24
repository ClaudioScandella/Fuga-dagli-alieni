package it.polimi.ingsw.cg_26.model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UtenteTest {
	
	private Utente u1;
	private Utente u2;
	private ArrayList<Integer> l1 = new ArrayList<Integer>();

	@Before
	public void setUp() throws Exception {
		u1 = new Utente("Patrizia");
		u2 = new Utente ("Claudio");
		l1.add(1234);
		l1.add(4321);
		u1.setIdPartite(1234);
		u1.setIdPartite(4321);
	}

	@Test
	public void testGetNomeUtente() {
		assertTrue(u1.getNomeUtente()=="Patrizia");
		assertTrue(u2.getNomeUtente()=="Claudio");
	}

	@Test
	public void testGetIdPartite() {
		assertEquals(u1.getIdPartite(), l1);
	}

}
