package it.polimi.ingsw.cg_26.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {

	private Giocatore g1 = new Giocatore(1234, "Patty");
	private Giocatore g2 = new Giocatore(4321, "Noemi");
	private Giocatore g3 = new Giocatore(4321, "Vivi");
	private CartaOggetto c1 = new CartaOggetto(TipoOggetto.SEDATIVI);
	private ArrayList<CartaOggetto> oggetti = new ArrayList<CartaOggetto>();


	
	@Before
	public void setUp() throws Exception {
		g1.setPersonaggio(Personaggio.UMANO, 1, "A01");
		g2.setPersonaggio(Personaggio.ALIENO, 1, "A05");
		g2.setPortata(2);
		g1.setAdrenalina(true);
		g1.setSedativi(true);
		g2.setPosizione("D08");
		g1.setInVita(false);
		g3.setHaUcciso(true);
		g1.setScialuppaRaggiunta(true);
		g1.setHaMosso(true);
		g1.setPuoPassare(true);
		g1.setHaPassato(true);
		g1.setCartaOggetto(c1);
		oggetti.add(c1);
		g1.setVittoria_sconfitta("vittoria");
		g2.setVittoria_sconfitta("sconfitta");
	}
	
	
	@Test
	public void testGiocatore() {
		assertTrue(g1 instanceof Giocatore);
	}

	@Test 
	public void testHaAttaccato(){
		g1.setHaAttaccato(true);
		assertTrue(g1.getHaAttaccato() == true);
	}
	
	@Test
	public void testGetPortata() {
		assertTrue(g1.getPortata() == 1);
		assertTrue(g2.getPortata() == 2);
		assertTrue(g3.getPortata() == 3);

	}

	@Test
	public void testGetAdrenalina() {
		assertTrue(g1.getAdrenalina());
		assertFalse(g2.getAdrenalina());
	}

	@Test
	public void testGetSedativi() {
		assertTrue(g1.getSedativi());
		assertFalse(g2.getSedativi());	}

	@Test
	public void testGetIdGiocatore() {
		assertTrue(g1.getIdGiocatore() == 1234);
		assertTrue(g2.getIdGiocatore() == 4321);
	}

	@Test
	public void testGetPosizione() {
		assertTrue(g1.getPosizione() == "A01");
		assertTrue(g2.getPosizione() == "D08");
		
	}

	@Test
	public void testGetPersonaggio() {
		assertEquals(g1.getPersonaggio(), Personaggio.UMANO);
		assertEquals(g2.getPersonaggio(), Personaggio.ALIENO);
	}

	@Test
	public void testGetInVita() {
		assertTrue(g1.getInVita() == false);
		assertTrue(g2.getInVita());
	}

	@Test
	public void testGetHaUcciso() {
		assertTrue(g3.getHaUcciso());
		assertFalse(g1.getHaUcciso());
	}

	@Test
	public void testGetScialuppaRaggiunta() {
		assertTrue(g1.getScialuppaRaggiunta());
		assertFalse(g2.getScialuppaRaggiunta());
	}

	@Test
	public void testGetHaMosso() {
		assertTrue(g1.getHaMosso());
		assertFalse(g2.getHaMosso());	
	}

	@Test
	public void testGetPuoPassare() {
		assertTrue(g1.getPuoPassare());
		assertFalse(g2.getPuoPassare());	}

	@Test
	public void testGetHaPassato() {
		assertTrue(g1.getHaPassato());
		assertFalse(g2.getHaPassato());
	}

	@Test
	public void testGetNomeUtente() {
		assertTrue(g1.getNomeUtente() == "Patty");
	}

	@Test
	public void testGetCarteOggetto() {
		assertEquals(g1.getCarteOggetto(), oggetti);
	}

	@Test
	public void testGetCartaOggetto() {
		assertEquals(g1.getCartaOggetto("SEDATIVI"), c1);
	}

	@Test
	public void testGetVittoria_sconfitta() {
		assertTrue(g1.getVittoria_sconfitta() == "vittoria");
		assertTrue(g2.getVittoria_sconfitta() == "sconfitta");
	}
	
	@Test
	public void testPuoAttaccare(){
		g1.setPuoAttaccare(true);
		assertTrue(g1.getPuoAttaccare());
	}
	
	@Test
	public void testSetPortata() {
		g3.setPortata(2);
		assertTrue(g3.getPortata() == 2);
	}

	@Test
	public void testSetAdrenalina() {
		g3.setAdrenalina(true);
		assertTrue(g3.getAdrenalina() == true);
	}

	@Test
	public void testSetSedativi() {
		g3.setSedativi(true);
		assertTrue(g3.getSedativi() == true);
	}

	@Test
	public void testSetPosizione() {
		g3.setPosizione("S03");
		assertTrue(g3.getPosizione() == "S03");

	}

	@Test
	public void testSetPersonaggio() {
		g3.setPersonaggio(Personaggio.UMANO, 1, "A08");
		assertTrue(g3.getPersonaggio() == Personaggio.UMANO);

	}
	
	@Test
	public void testSetCartaOggetto() {
		int numeroCarte = g3.getCarteOggetto().size();
		g3.setCartaOggetto(new CartaOggetto(TipoOggetto.ATTACCO));
		g3.setCartaOggetto(new CartaOggetto(TipoOggetto.LUCI));
		assertTrue(g3.getCarteOggetto().size() == numeroCarte+2);
	}
	
	@Test
	public void testSetInVita() {
		g3.setInVita(false);
		assertFalse(g3.getInVita());
	}

	@Test
	public void testSetHaUcciso() {
		g3.setHaUcciso(true);
		assertTrue(g3.getHaUcciso());
	}
	
	@Test
	public void testSetScialuppaRaggiunta() {
		g3.setScialuppaRaggiunta(true);
		assertTrue(g3.getScialuppaRaggiunta());
	}
	
	@Test
	public void testSetHaMosso() {
		g3.setHaMosso(true);
		assertTrue(g3.getHaMosso());
	}
	
	@Test
	public void testSetPuoPassare() {
		g3.setPuoPassare(false);
		assertFalse(g3.getPuoPassare());
	}
	
	@Test
	public void testSetHaPassato() {
		g3.setHaPassato(true);
		assertTrue(g3.getHaPassato());
	}
	
	@Test
	public void testSetVittoria_sconfitta() {
		g3.setVittoria_sconfitta("vittoria");
		assertTrue(g3.getVittoria_sconfitta() == "vittoria");
	}


	@Test
	public void testPossiedeCartaOggetto() {
		assertTrue(g1.possiedeCartaOggetto("SEDATIVI"));
		assertFalse(g1.possiedeCartaOggetto("LUCI"));
	}
	
	@Test
	public void testNumeroCarteOggettoPossedute() {
		assertTrue(g1.numeroCarteOggettoPossedute() == 1);
	}


	@Test
	public void testScartaOggetto() {
		CartaOggetto c1 = new CartaOggetto(TipoOggetto.ADRENALINA);
		CartaOggetto c2 = new CartaOggetto(TipoOggetto.LUCI);
		g3.setCartaOggetto(c1);
		g3.setCartaOggetto(c2);
		g3.scartaOggetto(c1);
		assertTrue(g3.possiedeCartaOggetto("LUCI"));
		assertFalse(g3.possiedeCartaOggetto("ADRENALINA"));
	}
}

