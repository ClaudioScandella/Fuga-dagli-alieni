package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;
import junit.framework.TestCase;

public class GiocatoreTest extends TestCase {
	
	private Giocatore g1, g2;
	private Personaggio ALIENO, UMANO;
	private CartaOggetto c1,c2, c3;
	private TipoOggetto ADRENALINA, ATTACCO;
	
	public GiocatoreTest(String nome){
		super(nome);
	}
	
	public void setUp(){
		c1 = new CartaOggetto(ADRENALINA);
		c2 = new CartaOggetto(ATTACCO);
		c3 = new CartaOggetto(ADRENALINA);
		
		g1 = new Giocatore("Patrizia", 1234);
		g1.setPosizione("B03");
		g1.setPersonaggio(ALIENO);
		g1.setCartaOggetto(c1);
		g1.setCartaOggetto(c2);
		
		g2 = new Giocatore ("Noemi",4321);
		g2.setPosizione("A08");
		g2.setPersonaggio(UMANO);
		g2.setCartaOggetto(c3);
	}

	public void testGetIdPartita() {
		assertTrue(g1.getIdPartita() == 1234);
		assertTrue(g2.getIdPartita() == 4321);
	}

	public void testGetIdGiocatore() {
		assertEquals(g1.getIdGiocatore(), 0);
		assertEquals(g2.getIdGiocatore(), 1);
	}

	public void testGetNomeUtente() {
		assertTrue(g1.getNomeUtente() == "Patrizia");
		assertTrue(g2.getNomeUtente() == "Noemi");
	}
	
	public void testGetPosizione(){
		assertTrue(g1.getPosizione() == "B03");
		assertTrue(g2.getPosizione() == "A08");
	}
	
	public void testGetPersonaggio(){
		assertTrue(g1.getPersonaggio() == ALIENO);
		assertTrue(g2.getPersonaggio() == UMANO);
	}
	
	public void testGetCartaOggetto(){
		assertTrue(g1.possiedoCartaOggetto(c1));
		assertTrue(g1.possiedoCartaOggetto(c2));
		assertTrue(g2.possiedoCartaOggetto(c3));
	}
	
	public void testNumeroCarteOggetto(){
		assertTrue(g1.numeroCarteOggetto() == 2);
		assertTrue(g2.numeroCarteOggetto() == 1);
	}
	
	public void testUsaCartaOggetto(){
		g1.usaCartaOggetto(c1);
		assertFalse(g1.possiedoCartaOggetto(c1));
		g2.usaCartaOggetto(c3);
		assertFalse(g2.possiedoCartaOggetto(c3));
	}
}
//mancano test da getListaMosse in poi...
