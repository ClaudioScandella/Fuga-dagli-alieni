package it.polimi.ingsw.cg_26.model.mappa;

import static org.junit.Assert.*;

import org.junit.Test;

public class MappaTest {
	
	Mappa mappa=new Mappa("galilei");

	@Test
	public void testMappa() {
		assertTrue(mappa instanceof Mappa);
	}

	@Test
	public void testGetMappa() {
		assertEquals(mappa.getMappa().size(), 322);
	}

	@Test
	public void testGetNomeMappa() {
		
		assertTrue(mappa.getNomeMappa()=="galilei");
	}

	@Test
	public void testGetListaSettoriTotali() {
		
		assertEquals(mappa.getListaSettoriTotali().size(), 322 );
	
		}
}


