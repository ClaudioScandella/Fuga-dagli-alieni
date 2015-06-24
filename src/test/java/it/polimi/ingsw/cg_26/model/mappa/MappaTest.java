package it.polimi.ingsw.cg_26.model.mappa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MappaTest {
	
	Mappa mappa=new Mappa("galilei");
	Mappa mappa1=new Mappa("galilei");

	@Test
	public void testMappa() {
		assertNotEquals(new Mappa("galilei"),new Mappa("galilei"));
	}

	@Test
	public void testGetMappa() {
		
		assertEquals(mappa.getMappa(),mappa1.getMappa());
	}

	@Test
	public void testGetNomeMappa() {
		
		assertTrue(mappa.getNomeMappa()=="galilei");
	}

	@Test
	public void testGetListaSettoriTotali() {
		assertEquals(mappa.getListaSettoriTotali(),mappa1.getListaSettoriTotali());
	}

}

