package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

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
		//controllerAzioni0 = new ControllerAzioni(null, controllerPartita);
		controllerAzioni1 = new ControllerAzioni("attacco", controllerPartita);
		controllerAzioni2 = new ControllerAzioni("mossa", controllerPartita);
		controllerAzioni3 = new ControllerAzioni("carta", controllerPartita);
		controllerAzioni4 = new ControllerAzioni("passo", controllerPartita);
		controllerAzioni5 = new ControllerAzioni("errore", controllerPartita);
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
	}

	@Test
	public void testControllerAzioni() {
		assertTrue(controllerAzioni1 instanceof ControllerAzioni);
	}

	@Test
	public void testAgisci() throws IOException {

		//attacco
			//attacca g1
		g1.setHaMosso(true);
		controllerAzioni1.agisci();
		assertFalse(g2.getInVita());
			//attacca g2
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

		//mossa
		partita.setNumeroGiocatoreCorrente(0);
		g1.setHaMosso(true);
		controllerAzioni2.agisci();
		assertTrue(g1.getPosizione() == "E02");
		
		partita.setNumeroGiocatoreCorrente(0);
		g1.setHaMosso(false);
		controllerAzioni2.agisci();
		assertFalse(g1.getPosizione() == "E02");
		
		partita.setNumeroGiocatoreCorrente(1);
		g2.setHaMosso(false);
		g2.setPosizione("C14");
		controllerAzioni2.agisci();
		assertFalse(g1.getPosizione() == "C14");
		
		//carta
		partita.setNumeroGiocatoreCorrente(0);
		controllerAzioni3.agisci();
		
		partita.setNumeroGiocatoreCorrente(1);
		int numero = g2.getCarteOggetto().size();
		controllerAzioni3.agisci();
		if(!(g2.possiedeCartaOggetto("adrenalina") || g2.possiedeCartaOggetto("sedativi") ||
			g2.possiedeCartaOggetto("difesa")))
				assertTrue(g2.getCarteOggetto().size() == numero-1);
		else
				assertTrue(g2.getCarteOggetto().size() == numero);
		
		//passo
		partita.setNumeroGiocatoreCorrente(0);
		g1.setPuoPassare(false);
		controllerAzioni4.agisci();
		assertFalse(g1.getHaPassato());
		
		g1.setPuoPassare(true);
		controllerAzioni4.agisci();
		assertTrue(g1.getHaPassato());
	}
}
