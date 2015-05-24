package it.polimi.ingsw.cg_26.model;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {
	
	private Partita p1, p2;
	private ArrayList<Giocatore> a1, a2;
	private Giocatore g1, g2, g3;

	@Before
	public void setUp() throws Exception {
		g1 = new Giocatore ("Patrizia",0,1234);
		g2 = new Giocatore ("Claudio",1,1234);
		a1 = new ArrayList<Giocatore>();
		a1.add(g1);
		a1.add(g2);
		p1 = new Partita(1234,a1,"Galilei");
		p1.setTurno(1);
		p1.setGiro(5);
		p1.setNumeroUmaniMorti(0);
		p1.setNumeroAlieniMorti(1);
		p1.scappa();
		p1.setLog(p1.getTurno(), g1.getIdGiocatore(), "B01");
		p1.setLog(p1.getTurno(), g2.getIdGiocatore(), "C03");
		p1.setLogPescato(p1.getTurno(), g1.getIdGiocatore(), "si");
		p1.setLogPescato(p1.getTurno(), g2.getIdGiocatore(), "no");
		p1.setLogPosizioneDichairata(p1.getTurno(), g1.getIdGiocatore(), "G08");
		p1.setLogPosizioneDichairata(p1.getTurno(), g2.getIdGiocatore(), "D07");
		
		g3 = new Giocatore("Michela",0,4321);
		a2 = new ArrayList<Giocatore>();
		a2.add(g3);
		p2 = new Partita(4321,a2,"Galvani");
		p2.setTurno(3);
		p2.setGiro(6);
		p2.setNumeroUmaniMorti(3);
		p2.setNumeroAlieniMorti(4);
		p2.scappa();
		p2.scappa();
	}

	@Test
	public void testGetIdPartita() {
		assertEquals(p1.getIdPartita(),1234);
		assertEquals(p2.getIdPartita(),4321);
	}

	@Test
	public void testGetNumeroGiocatori() {
		assertEquals(2,p1.getNumeroGiocatori());
		assertEquals(1,p2.getNumeroGiocatori());
	}

	@Test
	public void testGetTurno() {
		assertEquals(1,p1.getTurno());
		assertEquals(3,p2.getTurno());
	}

	@Test
	public void testGetTipoMappa() {
		assertEquals("Galilei", p1.getTipoMappa());
		assertEquals("Galvani", p2.getTipoMappa());
	}

	@Test
	public void testGetGiocatori() {
		assertEquals(p1.getGiocatori(),a1);
		assertEquals(p2.getGiocatori(),a2);
	}

	@Test
	public void testGetGiro() {
		assertEquals(p1.getGiro(),5);
		assertEquals(p2.getGiro(),6);
	}

	@Test
	public void testGetLog() {
		assertTrue(p1.getLog(1, 0, 0) == "B01");
		assertTrue(p1.getLog(1, 1, 0) == "C03");
	}

	@Test
	public void testPosizioneDichiarata() {
		assertTrue(p1.getLog(1, 0, 1) == "G08");
		assertTrue(p1.getLog(1, 1, 1) == "D07");
		
	}

	@Test
	public void testHaPescato() {
		assertTrue(p1.getLog(1,0,2) == "si");
		assertTrue(p1.getLog(1,1,2) == "no");
	}

	@Test
	public void testGetNumeroUmaniMorti() {
		assertTrue(p1.getNumeroUmaniMorti() == 0);
		assertTrue(p2.getNumeroUmaniMorti() == 3);
	}

	@Test
	public void testGetNumeroAlieniMorti() {
		assertTrue(p1.getNumeroAlieniMorti() == 1);
		assertTrue(p2.getNumeroAlieniMorti() == 4);
	}

	@Test
	public void testGetNumeroUmaniScappati() {
		assertTrue(p1.getNumeroUmaniScappati() == 1);
		assertTrue(p2.getNumeroUmaniScappati() == 2);
	}

}
