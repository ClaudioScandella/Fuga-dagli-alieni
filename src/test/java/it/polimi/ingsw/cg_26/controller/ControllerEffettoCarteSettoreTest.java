package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ControllerEffettoCarteSettoreTest {
	
	private ControllerEffettoCarteSettore controller;
	private ControllerPartita controllerPartita;
	private ModelPartita partita;
	private ArrayList<CartaSettore> lista = new ArrayList<CartaSettore>();
	private CartaSettore c1, c2, c3, c4, c5, c6;
	private Giocatore g1, g2;

	@Before
	public void setUp() throws Exception {
		g1 = new Giocatore(1111,"Patrizia");
		g1.setPersonaggio(Personaggio.UMANO, 1, "P08");
		g1.setCartaOggetto(new CartaOggetto(TipoOggetto.LUCI));
		g2 = new Giocatore(2222, "CLaudio");
		g2.setPersonaggio(Personaggio.ALIENO, 2, "D07");
		g2.setCartaOggetto(new CartaOggetto(TipoOggetto.LUCI));

		partita = new ModelPartita(1234, "galilei");
		controllerPartita = new ControllerPartita(partita);
		controller = new ControllerEffettoCarteSettore(controllerPartita);
		partita.setGiocatore(g1);
		partita.setGiocatore(g2);
		c1 = new CartaSettore(TipoSettore.SILENZIO, false);
		c2 = new CartaSettore(TipoSettore.RUMOREaSCELTA, false);
		c3 = new CartaSettore(TipoSettore.RUMOREproprioSETTORE, false);
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		c4 = new CartaSettore(TipoSettore.SILENZIO, true);
		c5 = new CartaSettore(TipoSettore.RUMOREaSCELTA, true);
		c6 = new CartaSettore(TipoSettore.RUMOREproprioSETTORE, true);
		lista.add(c4);
		lista.add(c5);
		lista.add(c6);
		partita.getMazzoCarteSettore().setMazzoCarteSettore(lista);
		
	}

	@Test
	public void testControllerEffettoCarteSettore() {
		assertTrue(controller instanceof ControllerEffettoCarteSettore);
	}

	@Test
	public void testPescaEdEseguiEffettoCarta() throws IOException {
		assertTrue(controller.pescaEdEseguiEffettoCarta() == false);
		assertTrue(controller.pescaEdEseguiEffettoCarta() == false);
		assertTrue(controller.pescaEdEseguiEffettoCarta() == false);
		assertTrue(controller.pescaEdEseguiEffettoCarta() == true);
		assertTrue(controller.pescaEdEseguiEffettoCarta() == true);
		assertTrue(controller.pescaEdEseguiEffettoCarta() == true);
	}
	
	@Test
	public void testInserisciSettoreRumoreAScelta(){
			//il settore esiste
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		controller.inserisciSettoreRumoreAScelta("D04");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
			//il settore non esiste
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		controller.inserisciSettoreRumoreAScelta("P15");
		assertFalse(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
	}

	@Test
	public void testInserisciProprioSettore(){
			//settore inserito == settore posizione
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		g1.setPosizione("P08");
		controller.inserisciProprioSettore("P08");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
			//settore inserito != settore posizione
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		g1.setPosizione("P08");
		controller.inserisciProprioSettore("D07");
		assertFalse(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
	}
	
	@Test
	public void usaOscartaTest(){
			//comando "usa" effettuato da umano
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		partita.setNumeroGiocatoreCorrente(0);
		assertTrue(controllerPartita.giocatoreCorrente().getPersonaggio() == Personaggio.UMANO);
		controller.usaOscarta("uso");
		assertEquals(partita.getStatoAvanzamentoTurno(), StatoAvanzamentoTurno.ATTESA_CARTA_USO);
			//comando "usa" effettuato da alieno
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		partita.setNumeroGiocatoreCorrente(1);
		assertTrue(controllerPartita.giocatoreCorrente().getPersonaggio() == Personaggio.ALIENO);
		controller.usaOscarta("uso");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
			//comando "scarta"
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		controller.usaOscarta("scarta");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_CARTA_DA_SCARTARE);
		
	}

	@Test
	public void testScartaOggetto(){
			//possiede cartaOggetto
		assertTrue(controllerPartita.giocatoreCorrente().possiedeCartaOggetto("LUCI"));
		controller.scartaOggetto("LUCI");
		assertFalse(controllerPartita.giocatoreCorrente().possiedeCartaOggetto("LUCI"));
			//non possiede cartaOggetto
		assertFalse(controllerPartita.giocatoreCorrente().possiedeCartaOggetto("TELETRASPORTO"));
		controller.scartaOggetto("TELETRASPORTO");
		assertFalse(controllerPartita.giocatoreCorrente().possiedeCartaOggetto("TELETRASPOTO"));
	}
}
