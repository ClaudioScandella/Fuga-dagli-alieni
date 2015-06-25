package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteSettore;

import org.junit.Before;
import org.junit.Test;

public class ControllerMazzoCarteSettoreTest {
	
	private MazzoCarteSettore mazzo, mazzo1, mazzo2;
	private ControllerMazzoCarteSettore controllerSettore, controllerSettore1, controllerSettore2;

	@Before
	public void setUp() throws Exception {
		mazzo = new MazzoCarteSettore();
		controllerSettore = new ControllerMazzoCarteSettore(mazzo);
		mazzo1 = new MazzoCarteSettore();
		controllerSettore1 = new ControllerMazzoCarteSettore(mazzo1);
		mazzo2 = new MazzoCarteSettore();
		controllerSettore2 = new ControllerMazzoCarteSettore(mazzo2);
	}

	@Test
	public void testControllerMazzoCarteSettore() {
		assertTrue(controllerSettore instanceof ControllerMazzoCarteSettore);
	}

	@Test
	public void testPesca() {
		assertTrue(controllerSettore.pesca() instanceof CartaSettore);
	}

	@Test
	public void testAggiungiCartaAScartiSettore() {
		controllerSettore.aggiungiCartaAScartiSettore(controllerSettore.pesca());
		assertTrue(mazzo.getScartiCarteSettore().size() == 1);
	}

	@Test
	public void testNumeroCarteSettoreNelMazzo() {
		assertTrue(controllerSettore.numeroCarteSettoreNelMazzo() == 25);
	}

	@Test
	public void testRigeneraMazzo() {
		while(mazzo2.getMazzoCarteSettore().size() != 0){
			controllerSettore2.pescaCartaSettore();
		}
		assertTrue(mazzo2.getMazzoCarteSettore().size() == 0);
		controllerSettore2.rigeneraMazzo();
		assertTrue(mazzo2.getMazzoCarteSettore().size() == 25);
			
		
		
	}

	@Test
	public void testRimuoviCarta() {
		int n = mazzo1.getMazzoCarteSettore().size();
		controllerSettore1.rimuoviCarta();
		assertTrue(mazzo1.getMazzoCarteSettore().size() == n-1);
	}

	@Test
	public void testPescaCartaSettore() {
		int n = mazzo1.getMazzoCarteSettore().size();
		int num = mazzo1.getScartiCarteSettore().size();
		CartaSettore c = controllerSettore1.pescaCartaSettore();
		assertTrue(c instanceof CartaSettore);
		assertTrue(mazzo1.getMazzoCarteSettore().size() == n-1);
		assertTrue(mazzo1.getScartiCarteSettore().size() == num+1);
	}

}
