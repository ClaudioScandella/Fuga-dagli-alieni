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

	@Before
	public void setUp() throws Exception {
		g1 = new Giocatore(1111,"Patrizia");
		g2 = new Giocatore(2222, "Claudio");
		controller.addGiocatore(g1);
		controller.addGiocatore(g2);
		controller.assegnaRuoli();
		partita.setNumeroGiocatoreCorrente(0);
		partita.setNumeroTurno(2);
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
				//se il comando è "passa": ha passato == true
		partita.setNumeroTurno(15);
		controller.giocatoreCorrente().setHaPassato(false);
		controller.giocatoreCorrente().setPuoPassare(true);
		partita.setStatoPescaOggetto(StatoPescaOggetto.NON_DEVE_PESCARE);
		controller.avanzaPartita("passo");
		assertTrue(controller.giocatoreCorrente().getHaPassato() == false);
				//se haPassato==true && controllaFinePartita == false
		partita.setNumeroTurno(15);
		controller.giocatoreCorrente().setHaPassato(true);
	
	}

/*	@Test
	public void testInserisciSettoreDestinazione() {
		controller.giocatoreCorrente().setPosizione("P08");
		controller.inserisciSettoreDestinazione("O09");
		assertTrue(controller.giocatoreCorrente().getPosizione() == "O09");
	}

	@Test
	public void testInserisciCartaOggetto() {
		controller.giocatoreCorrente().setCartaOggetto(new CartaOggetto(TipoOggetto.SEDATIVI));
		controller.inserisciCartaOggetto("sedativi");
		assertTrue(controller.giocatoreCorrente().getSedativi() == true);
	}

	@Test
	public void testInserisciSettoreLuci() {
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_LUCI);
		controller.inserisciSettoreLuci("N02");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
	}

	@Test
	public void testInserisciSettoreRumoreAScelta() {
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE);
		controller.inserisciSettoreRumoreAScelta("N02");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);	}

	@Test
	public void testInserisciProprioSettore() {
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE);
		controller.giocatoreCorrente().setPosizione("N02");
		controller.inserisciSettoreLuci("N02");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);	}

//	@Test
//	public void testUsaOscarta() {
//		fail("Not yet implemented");
//	}


	@Test
	public void testScartaOggetto() {
		controller.giocatoreCorrente().setCartaOggetto(new CartaOggetto(TipoOggetto.LUCI));
		controller.scartaOggetto("LUCI");
		assertFalse(controller.giocatoreCorrente().possiedeCartaOggetto("LUCI"));
	}
*/
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
