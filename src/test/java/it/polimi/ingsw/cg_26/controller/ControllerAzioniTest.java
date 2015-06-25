package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.ModelPartita.StatoPescaOggetto;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ControllerAzioniTest {
	
	private ControllerAzioni controllerAzioni0, controllerAzioni1, controllerAzioni2,
		controllerAzioni3, controllerAzioni4, controllerAzioni5;
	private ControllerPartita controllerPartita;
	private ModelPartita partita;
	private Giocatore g1, g2, g3;

	@Before
	public void setUp() throws Exception {
		partita = new ModelPartita(1234,"galilei");
		controllerPartita = new ControllerPartita(partita);
		controllerAzioni1 = new ControllerAzioni("attacco", controllerPartita);
		controllerAzioni2 = new ControllerAzioni("mossa", controllerPartita);
		controllerAzioni3 = new ControllerAzioni("carta", controllerPartita);
		controllerAzioni4 = new ControllerAzioni("passo", controllerPartita);
		controllerAzioni5 = new ControllerAzioni("pesco oggetto", controllerPartita);
		g1 = new Giocatore(1234,"Patrizia");
		g2 = new Giocatore(1234, "Claudio");
		g3 = new Giocatore (1234, "Michela");
		g1.setPersonaggio(Personaggio.ALIENO, 2, "L05");
		g2.setPersonaggio(Personaggio.UMANO, 1, "L05");
		g3.setPersonaggio(Personaggio.UMANO, 1, "L05");
		g2.setCartaOggetto(new CartaOggetto(TipoOggetto.ATTACCO));
		partita.setGiocatore(g1);
		partita.setGiocatore(g2);
		partita.setGiocatore(g3);
		CartaOggetto c1 = new CartaOggetto(TipoOggetto.DIFESA);
		g3.setCartaOggetto(c1);
		
	}

	@Test
	public void testControllerAzioni() {
		assertTrue(controllerAzioni1 instanceof ControllerAzioni);
	}

	@Test
	public void testAgisci() throws IOException {

		//ATTACCO
			//attacca g1, alieno e g3 possiede la carta difesa
		g1.setHaMosso(true);
		controllerAzioni1.agisci();
		assertFalse(g2.getInVita());
		assertTrue(g3.getInVita() == true);
		assertTrue(g1.getPortata() == 3);
			//attacca g1, alieno che ha già attaccato
		g2.setInVita(true);
		controllerAzioni1.agisci();
		assertTrue(g2.getInVita());
			//attacca g2, umano con carta oggetto
		g2.setInVita(true);
		g2.setPosizione("E02");
		g1.setPosizione("E02");
		partita.setNumeroGiocatoreCorrente(1);
		g2.setHaMosso(true);
		g2.setPuoAttaccare(true);
		controllerAzioni1.agisci();
		assertFalse(g1.getInVita());
			//attacca g3, umano senza carta oggetto 
		g2.setInVita(true);
		g1.setInVita(true);
		g2.setPosizione("E02");
		g1.setPosizione("E02");
		g3.setPosizione("E02");
		partita.setNumeroGiocatoreCorrente(2);
		g3.setHaMosso(true);
		controllerAzioni1.agisci();
		assertFalse(g3.possiedeCartaOggetto("ATTACCO"));
		assertTrue(g1.getInVita());
		assertTrue(g2.getInVita());
			
		//MOSSA
			//il giocatore ha già mosso
		partita.setNumeroGiocatoreCorrente(0);
		g1.setHaMosso(true);
		controllerAzioni2.agisci();
		assertFalse(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_SETTORE_DESTINAZIONE);
			//il giocatore non ha ancora mosso
		partita.setNumeroGiocatoreCorrente(0);
		g1.setHaMosso(false);
		controllerAzioni2.agisci();
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_SETTORE_DESTINAZIONE);
		
		//CARTA
			//il giocatore è alieno
		partita.setNumeroGiocatoreCorrente(0);
		controllerAzioni3.agisci();
		assertFalse(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_CARTA);
			//il giocatore è umano
		partita.setNumeroGiocatoreCorrente(1);
		controllerAzioni3.agisci();
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_CARTA);

	
		//PASSO
			//puòPassare == true e StatoPescaOggetto == DEVE_PESCARE
		partita.setNumeroGiocatoreCorrente(0);
		partita.setStatoPescaOggetto(StatoPescaOggetto.DEVE_PESCARE);
		g1.setPuoPassare(true);
		controllerAzioni4.agisci();
		assertFalse(g1.getHaPassato());
			//puòPassare == false e StatoPescaOggetto == DEVE_PESCARE
		partita.setNumeroGiocatoreCorrente(0);
		partita.setStatoPescaOggetto(StatoPescaOggetto.DEVE_PESCARE);
		g1.setPuoPassare(false);
		controllerAzioni4.agisci();
		assertFalse(g1.getHaPassato());
			//puòPassare == false e StatoPescaOggetto != DEVE_PESCARE
		partita.setNumeroGiocatoreCorrente(0);
		partita.setStatoPescaOggetto(StatoPescaOggetto.NON_DEVE_PESCARE);
		g1.setPuoPassare(false);
		controllerAzioni4.agisci();
		assertFalse(g1.getHaPassato());
			//puòPassare == true e StatoPescaOggetto != DEVE_PESCARE
			//con giocatore umano
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoPescaOggetto(StatoPescaOggetto.NON_DEVE_PESCARE);
		g2.setPuoPassare(true);
		controllerAzioni4.agisci();
		assertTrue(g2.getHaPassato());
		
		//OGGETTO
			//giocatore alieno
		partita.setNumeroGiocatoreCorrente(0);
		partita.setStatoPescaOggetto(StatoPescaOggetto.DEVE_PESCARE);
		controllerAzioni5.agisci();
		assertFalse(partita.getStatoPescaOggetto() == StatoPescaOggetto.DEVE_PESCARE);
			//giocatore umano
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoPescaOggetto(StatoPescaOggetto.DEVE_PESCARE);
		controllerAzioni5.agisci();
		assertTrue(partita.getStatoPescaOggetto() == StatoPescaOggetto.NON_DEVE_PESCARE);
	
	}

	@Test
	public void testInserisciSettoreDestinazione(){
			// settore esistente, settore scialuppa bloccata
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_CARTA);
		g2.setPosizione("C02");
		g2.setHaMosso(false);
		partita.getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getControllerMappa().convertitoreStringa_Indice("B02")).setBloccata(true);
		controllerAzioni2.inserisciSettoreDestinazione("B02");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
			//settore scialuppa non bloccato
		partita.setNumeroGiocatoreCorrente(1);
		g2.setPosizione("C02");
		g2.setHaMosso(false);
		partita.getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getControllerMappa().convertitoreStringa_Indice("B02")).setBloccata(false);
		controllerAzioni2.inserisciSettoreDestinazione("B02");
		assertTrue(partita.getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getControllerMappa().convertitoreStringa_Indice("B02")).getBloccata()==true);
			//settore pericoloso con carta sedativi
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_LUCI);
		g2.setSedativi(true);
		g2.setPosizione("C02");
		g2.setHaMosso(false);
		controllerAzioni2.inserisciSettoreDestinazione("d02");
		assertFalse(//partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO
				 partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE
				|| partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_PROPRIO_SETTORE);
		assertTrue(g2.getHaMosso()== true); 
			//settore pericoloso senza carta sedativi
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_LUCI);
		g2.setSedativi(false);
		g2.setPosizione("C02");
		g2.setHaMosso(false);
		controllerAzioni2.inserisciSettoreDestinazione("d02");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO
				|| partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE
				|| partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_PROPRIO_SETTORE);
			//settore sicuro
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_LUCI);
		g2.setSedativi(false);
		g2.setPosizione("C02");
		g2.setHaMosso(false);
		controllerAzioni2.inserisciSettoreDestinazione("c01");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO
				|| partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE
				|| partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_PROPRIO_SETTORE);
		assertTrue(g2.getHaMosso()==true);
			//settore non esistente
		partita.setNumeroGiocatoreCorrente(1);
		g2.setPosizione("C02");
		g2.setHaMosso(false);
		controllerAzioni2.inserisciSettoreDestinazione("c00");
		assertFalse(g2.getHaMosso());
			//settore a cui il personaggio non può arrivare
		partita.setNumeroGiocatoreCorrente(1);
		g2.setPosizione("C02");
		g2.setHaMosso(false);
		controllerAzioni2.inserisciSettoreDestinazione("G04");
		assertFalse(g2.getHaMosso());
	}
	
	@Test
	public void testInserisciCartaOggetto(){
			//possiede carta oggetto e non è "difesa"
		partita.setNumeroGiocatoreCorrente(1);
		g2.setSedativi(false);
		g2.setCartaOggetto(new CartaOggetto(TipoOggetto.SEDATIVI));
		g2.setHaMosso(false);
		controllerAzioni3.inserisciCartaOggetto("sedativi");
		assertTrue(g2.getSedativi() == true);
			//possiede carta oggetto ed è "difesa"
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_CARTA);
		g2.setCartaOggetto(new CartaOggetto(TipoOggetto.DIFESA));
		controllerAzioni3.inserisciCartaOggetto("difesa");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
			//non possiede carta oggetto
		partita.setNumeroGiocatoreCorrente(1);
		partita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_CARTA);
		controllerAzioni3.inserisciCartaOggetto("teletrasporto");
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_COMANDO);
	}
	
	@Test
	public void testPescaOggetto(){
			//mazzo vuoto
		partita.setNumeroGiocatoreCorrente(2);
		while(partita.getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo() != 0){
			partita.getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(partita.getControllerMazzoCarteOggetto().pescaCartaOggetto());
		}
		assertTrue(partita.getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo() == 0);
		controllerAzioni3.pescaOggetto();
		assertFalse(partita.getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo() == 0);

			//pesca e ha già 3 carte oggetto
		partita.setNumeroGiocatoreCorrente(2);
		while(g3.numeroCarteOggettoPossedute() != 4){
			controllerAzioni3.pescaOggetto();
		}
		controllerAzioni3.pescaOggetto();
		assertTrue(partita.getStatoAvanzamentoTurno() == StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
			//pesca e ha meno di 3 carte oggetto
		partita.setNumeroGiocatoreCorrente(2);
		g3.scartaOggetto(g3.getCarteOggetto().get(0));
		g3.setCartaOggetto(partita.getControllerMazzoCarteOggetto().pescaCartaOggetto());
		assertTrue(partita.getStatoPescaOggetto() == StatoPescaOggetto.NON_DEVE_PESCARE);
			//mazzo non rigenerabile
		partita.setNumeroGiocatoreCorrente(1);
		while(partita.getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo() != 0){
			partita.getControllerMazzoCarteOggetto().rimuoviCarta();
		}
		assertTrue(partita.getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo() == 0);
		controllerAzioni3.pescaOggetto();
		assertTrue(partita.getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo() == 0);
		assertTrue(partita.getStatoPescaOggetto() == StatoPescaOggetto.NON_DEVE_PESCARE);
	}
}
