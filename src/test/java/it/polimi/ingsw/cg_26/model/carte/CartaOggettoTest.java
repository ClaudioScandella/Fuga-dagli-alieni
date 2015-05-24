package it.polimi.ingsw.cg_26.model.carte;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto.TipoOggetto;

import org.junit.Before;
import org.junit.Test;

public class CartaOggettoTest {
	
	private CartaOggetto c1, c2, c3, c4, c5, c6;
	private TipoOggetto ADRENALINA, ATTACCO, DIFESA, LUCI, SEDATIVI,
		TELETRASPORTO;

	@Before
	public void setUp() throws Exception {
		c1 = new CartaOggetto(ADRENALINA);
		c2 = new CartaOggetto(ATTACCO);
		c3 = new CartaOggetto(DIFESA);
		c4 = new CartaOggetto(LUCI);
		c5 = new CartaOggetto(SEDATIVI);
		c6 = new CartaOggetto(TELETRASPORTO);
	}

	@Test
	public void testCartaOggetto() {
		assertTrue(c1.getTipoOggetto() == ADRENALINA);
		assertTrue(c2.getTipoOggetto() == ATTACCO);
		assertTrue(c3.getTipoOggetto() == DIFESA);
		assertTrue(c4.getTipoOggetto() == LUCI);
		assertTrue(c5.getTipoOggetto() == SEDATIVI);
		assertTrue(c6.getTipoOggetto() == TELETRASPORTO);
	}

	@Test
	public void testGetTipoOggetto() {
		assertTrue(c1.getTipoOggetto() == ADRENALINA);
		assertTrue(c2.getTipoOggetto() == ATTACCO);
		assertTrue(c3.getTipoOggetto() == DIFESA);
		assertTrue(c4.getTipoOggetto() == LUCI);
		assertTrue(c5.getTipoOggetto() == SEDATIVI);
		assertTrue(c6.getTipoOggetto() == TELETRASPORTO);
	}

}
