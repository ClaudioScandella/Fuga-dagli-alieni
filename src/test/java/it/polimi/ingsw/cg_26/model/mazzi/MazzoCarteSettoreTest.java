package it.polimi.ingsw.cg_26.model.mazzi;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import org.junit.Before;
import org.junit.Test;

public class MazzoCarteSettoreTest {
	
	MazzoCarteSettore m1 = new MazzoCarteSettore();
	MazzoCarteSettore m2 = new MazzoCarteSettore();
	MazzoCarteSettore m3 = new MazzoCarteSettore();

	@Before
	public void setUp() throws Exception {
	}

/*	@Test
	public void testMischia() {
		fail("Not yet implemented");
	}
*/

	@Test
	public void testPesca() {
		assertTrue(m1.pesca().getTipoCartaSettore() == TipoSettore.SILENZIO);
		assertTrue(m1.pesca().getConOggetto() == false);
		m1.aggiungiScartiSettore(m1.pesca());
		m1.rimuoviCarta();		
	}

	@Test
	public void testLunghezzaListaSettori() {
		assertTrue(m2.lunghezzaListaSettori() == 25);
		m2.rimuoviCarta();
		assertTrue(m2.lunghezzaListaSettori() == 24);
		
	}


	@Test
	public void testRigeneraMazzo() {
		for(int i = 0; i < 25; i++){
			m3.aggiungiScartiSettore(m3.pesca());
			m3.rimuoviCarta();
		}
		assertTrue(m3.lunghezzaListaSettori() == 0);
		m3.rigeneraMazzo();
		assertTrue(m3.lunghezzaListaSettori() == 25);
	}


}
