package it.polimi.ingsw.cg_26.model.mappe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MappaTest {
	
	

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testMappa() {
	}

	@Test
	public void testGetNomeMappa() {
		Mappa mappa=new Mappa("galilei");
		
		assertTrue(mappa.getNomeMappa()=="galilei");
	}

}
