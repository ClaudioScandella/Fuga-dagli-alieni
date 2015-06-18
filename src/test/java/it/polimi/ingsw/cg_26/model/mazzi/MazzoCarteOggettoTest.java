package it.polimi.ingsw.cg_26.model.mazzi;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MazzoCarteOggettoTest {
	
	private MazzoCarteOggetto m1 = new MazzoCarteOggetto();
	private MazzoCarteOggetto m2 = new MazzoCarteOggetto();
	private ArrayList<CartaOggetto> array = new ArrayList<CartaOggetto>();
	
	@Before
	public void setUp() throws Exception {
		array.add(new CartaOggetto(TipoOggetto.ADRENALINA));
		m1.setMazzoCarteOggetto(array);
	}

	@Test
	public void testGetMazzoCarteOggetto() {
		assertEquals(m1.getMazzoCarteOggetto(), array);
		assertTrue(m2.getMazzoCarteOggetto().size() == 12);
	}

	@Test
	public void testGetScartiCarteSettore() {
		assertTrue(m1.getScartiCarteOggetto().size() == 0);
		assertTrue(m2.getScartiCarteOggetto().size() == 0);
	}

}
