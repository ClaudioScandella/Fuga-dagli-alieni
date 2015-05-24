//fallisce testGetIdGiocatore

package it.polimi.ingsw.cg_26.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	
	private Giocatore g1, g2;
	private Personaggio ALIENO, UMANO;
	private CartaOggetto c1,c2, c3;
	private TipoOggetto ADRENALINA, ATTACCO;

	@Before
	public void setUp() throws Exception {
		c1 = new CartaOggetto(ADRENALINA);
		c2 = new CartaOggetto(ATTACCO);
		c3 = new CartaOggetto(ADRENALINA);
		
		g1 = new Giocatore("Patrizia",0, 1234);
		g1.setPosizione("B03");
		g1.setPersonaggio(ALIENO);
		g1.setCartaOggetto(c1);
		g1.setCartaOggetto(c2);
		
		
		g2 = new Giocatore ("Noemi",1,4321);
		g2.setPosizione("A08");
		g2.setPersonaggio(UMANO);
		g2.setCartaOggetto(c3);
	}

	@Test
	public void testGetIdPartita() {
		assertTrue(g1.getIdPartita() == 1234);
		assertTrue(g2.getIdPartita() == 4321);
	}

	@Test
	public void testGetIdGiocatore() {
		assertEquals(g1.getIdGiocatore(), 0);
		assertEquals(g2.getIdGiocatore(), 1);
	}

	@Test
	public void testGetNomeUtente() {
		assertTrue(g1.getNomeUtente() == "Patrizia");
		assertTrue(g2.getNomeUtente() == "Noemi");
	}

	@Test
	public void testGetPosizione() {
		assertTrue(g1.getPosizione() == "B03");
		assertTrue(g2.getPosizione() == "A08");
	}

	@Test
	public void testGetPersonaggio() {
		assertTrue(g1.getPersonaggio() == ALIENO);
		assertTrue(g2.getPersonaggio() == UMANO);
	}

	@Test
	public void testPossiedoCartaOggetto() {
		assertTrue(g1.possiedoCartaOggetto(c1));
		assertTrue(g1.possiedoCartaOggetto(c2));
		assertTrue(g2.possiedoCartaOggetto(c3));
	}

	@Test
	public void testNumeroCarteOggetto() {
		assertTrue(g1.numeroCarteOggetto() == 2);
		assertTrue(g2.numeroCarteOggetto() == 1);
	}

	@Test
	public void testUsaCartaOggetto() {
		g1.usaCartaOggetto(c1);
		assertFalse(g1.possiedoCartaOggetto(c1));
		g2.usaCartaOggetto(c3);
		assertFalse(g2.possiedoCartaOggetto(c3));
	}

	@Test
	public void testGetListaMosse() {
		g2.setPosizione("A05");
		ArrayList<String> lista = new ArrayList<>();
		lista.add("A08");
		lista.add("A05");
		assertEquals(lista,g2.getListaMosse());
	}

	@Test
	public void testGetMossaIesima() {
		g1.setPosizione("A05");		
		assertTrue(g1.getMossaIesima(0) == "B03");
		assertTrue(g1.getMossaIesima(1) == "A05");
	}

	@Test
	public void testGetInVita() {
		assertTrue(g1.getInVita());
		g2.morto();
		assertFalse(g2.getInVita());
	}

	@Test
	public void testGetHaUcciso() {
		assertFalse(g1.getHaUcciso());
		g2.setHaUcciso();
		assertTrue(g2.getHaUcciso());
	}

	@Test
	public void testGetScialuppaRaggiunta() {
		assertFalse(g1.getHaUcciso());
		g2.setHaUcciso();
		assertTrue(g2.getHaUcciso());
	}
	
	@Test
	public void testGetCartaOggetto(){
		assertTrue(g1.possiedoCartaOggetto(c1));
		assertTrue(g1.possiedoCartaOggetto(c2));
		assertTrue(g2.possiedoCartaOggetto(c3));
	}

}
