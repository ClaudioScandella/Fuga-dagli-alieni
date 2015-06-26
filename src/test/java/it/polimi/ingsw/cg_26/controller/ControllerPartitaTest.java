package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import it.polimi.ingsw.cg_26.main.Main;
import it.polimi.ingsw.cg_26.model.GameState;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.ModelPartita.StatoPescaOggetto;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import org.junit.Before;
import org.junit.Test;

public class ControllerPartitaTest {
	
	private ModelPartita partita = new ModelPartita(1234, "galilei");
	private ControllerPartita controller = new ControllerPartita(partita);
	private Giocatore g1, g2;
	private LOG log;
	private ControllerEffettoCarteSettore controllerEffettoCarteSettore;

	@Before
	public void setUp() throws Exception {
		g1 = new Giocatore(1111,"Patrizia");
		g2 = new Giocatore(2222, "Claudio");
		controller.addGiocatore(g1);
		controller.addGiocatore(g2);
		controller.assegnaRuoli();
		partita.setNumeroGiocatoreCorrente(0);
		partita.setNumeroTurno(2);
		log = new LOG();
	}

	@Test
	public void testControllerPartita() {
		assertTrue(controller instanceof ControllerPartita);
	}

	@Test
	public void testGetLog() {
		assertTrue(controller.getLog() instanceof LOG);
	}

	@Test
	public void testAddGiocatore() {
		assertTrue(partita.getGiocatori().contains(g1));
	}

	@Test
	public void testAssegnaRuoli() {
		assertTrue(g1.getPersonaggio() == Personaggio.UMANO || g1.getPersonaggio() == Personaggio.ALIENO);
	}

	@Test
	public void testAggiornaTurno() {
		partita.setNumeroTurno(3);
		controller.aggiornaTurno();
		assertTrue(partita.getNumeroTurno() == 4);
	}

	@Test
	public void testAvanzaPartita() throws IOException {
			//se il turno è 40
		partita.setNumeroTurno(40);
		controller.avanzaPartita("carta");
		assertTrue(g1.getVittoria_sconfitta() == "vittoria" || g1.getVittoria_sconfitta() == "sconfitta");	
			//se il turno non è 40
				//se il comando è "passa"
		partita.setNumeroTurno(15);
		controller.giocatoreCorrente().setPuoPassare(true);
		partita.setStatoPescaOggetto(StatoPescaOggetto.NON_DEVE_PESCARE);
		controller.avanzaPartita("passa");
		assertTrue(controller.giocatoreCorrente().getHaPassato() == false);		
				//se il comando  non è "passa" 
		int n = partita.getNumeroGiocatoreCorrente();
		partita.setNumeroTurno(15);
		controller.giocatoreCorrente().setPuoPassare(true);
		partita.setStatoPescaOggetto(StatoPescaOggetto.NON_DEVE_PESCARE);
		controller.avanzaPartita("carta");
		assertTrue(controller.giocatoreCorrente().getPuoPassare() == true);
		assertFalse(n == partita.getNumeroGiocatoreCorrente() );
	
	}

	@Test
	public void testInserisciSettoreDestinazione() throws IOException {
		controller.avanzaPartita("mossa");
		controller.giocatoreCorrente().setPosizione("P08");
		controller.inserisciSettoreDestinazione("O09");
		assertTrue(controller.giocatoreCorrente().getPosizione() == "O09");
	}

	@Test
	public void testInserisciCartaOggetto() throws IOException {
		controller.avanzaPartita("carta");
		controller.giocatoreCorrente().setCartaOggetto(new CartaOggetto(TipoOggetto.SEDATIVI));
		controller.inserisciCartaOggetto("sedativi");
		assertTrue(controller.giocatoreCorrente().getSedativi() == true);
	}

//	@Test
//	public void testInserisciSettoreLuci() throws IOException {
//		controller.avanzaPartita("mossa");
//		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_LUCI);
//		controller.inserisciSettoreLuci("N02");
//		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
//	}

//	@Test
//	public void testInserisciSettoreRumoreAScelta() throws IOException {
//		controller.avanzaPartita("mossa");
//		controller.inserisciSettoreDestinazione("P08");
//		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE);
//		controller.inserisciSettoreRumoreAScelta("P08");
//		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);	}

//	@Test
//	public void testInserisciProprioSettore() throws IOException {
//		controller.avanzaPartita("mossa");
//		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE);
//		controller.giocatoreCorrente().setPosizione("N02");
//		controller.inserisciSettoreLuci("N02");
//		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);	}

	@Test
	public void testUsaOscarta() {
		
	}


//	@Test
//	public void testScartaOggetto() throws IOException {
//		controller.avanzaPartita("carta");
//		controller.giocatoreCorrente().setCartaOggetto(new CartaOggetto(TipoOggetto.LUCI));
//		controller.scartaOggetto("luci");
//		assertFalse(controller.giocatoreCorrente().possiedeCartaOggetto("luci"));
//	}

	@Test
	public void testGetPartita() {
		
		assertTrue(controller.getPartita() instanceof ModelPartita);
	}

	@Test
	public void testControllaFinePartita() {
		//numeroUmaniInGioco == 0
	g1.setInVita(false);
	g2.setInVita(false);
	assertTrue(controller.controllaFinePartita());
		//numeroScialuppeBloccate == 0
	g1.setInVita(true);
	g2.setInVita(true);
	partita.getControllerMappa().getSettoriScialuppa().get(0).setBloccata(true);
	partita.getControllerMappa().getSettoriScialuppa().get(1).setBloccata(true);
	partita.getControllerMappa().getSettoriScialuppa().get(2).setBloccata(true);
	partita.getControllerMappa().getSettoriScialuppa().get(3).setBloccata(true);
	assertTrue(partita.getControllerMappa().numeroScialuppeBloccate() == 4);
	assertTrue(controller.controllaFinePartita());
		// numeroUmaniInGioco != 0 && numeroScialuppeBloccate != 0
	g1.setInVita(true);
	g2.setInVita(true);
	partita.getControllerMappa().getSettoriScialuppa().get(0).setBloccata(false);
	partita.getControllerMappa().getSettoriScialuppa().get(1).setBloccata(false);
	partita.getControllerMappa().getSettoriScialuppa().get(2).setBloccata(false);
	partita.getControllerMappa().getSettoriScialuppa().get(3).setBloccata(false);
	assertTrue(partita.getControllerMappa().numeroScialuppeBloccate() == 0);
	assertTrue(controller.numeroUmaniInGioco() != 0);
	assertFalse(controller.controllaFinePartita());
	}

	@Test
	public void testTerminaPartita() {
		partita.setStato(GameState.RUNNING);
		controller.terminaPartita();
		assertTrue(partita.getStato() == GameState.FINEGIOCO);
		assertTrue(partita.getGiocatoriPerdenti().size() != 0 || 
						partita.getGiocatoriVincenti().size() != 0);
	}

	@Test
	public void testGetGiocatoriInSettore() {
		g1.setPosizione("P08");
		controller.getGiocatoriInSettore("P08");
		assertTrue(controller.getGiocatoriInSettore("P08").contains(g1));
	}

	@Test
	public void testGiocatoreCorrente() {
		assertEquals(controller.giocatoreCorrente(), 
				partita.getGiocatori().get(partita.getNumeroGiocatoreCorrente()));
	}

	@Test
	public void testNumeroUmaniInGioco() {
		g1.setInVita(true);
		assertTrue(controller.numeroUmaniInGioco() == 1);
	}

	@Test
	public void testNumeroAlieniInGioco() {
		g2.setInVita(true);
		assertTrue(controller.numeroAlieniInGioco() == 1);
	}

}
