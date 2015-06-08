package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.mappa.Mappa;
import it.polimi.ingsw.cg_26.model.mappa.Settore;

import java.util.ArrayList;

import org.junit.Test;


public class ControllerMappaTest {
	Mappa mappa=new Mappa("galilei");
	ControllerMappa controllerMappa=new ControllerMappa(mappa);
	ControllerMappa controllerMappa1=new ControllerMappa(mappa);
	ArrayList<Settore> settoriVuoti=new ArrayList<Settore>();
	ArrayList<Settore> listaScialuppe=new ArrayList<Settore>();
	ArrayList<Settore> settoriSicuri=new ArrayList<Settore>();
	ArrayList<Settore> settoriAdiacenti=new ArrayList<Settore>();
	ArrayList<Settore> settoriPericolosi=new ArrayList<Settore>();
	
	@Test
	public void testControllerMappa() {
		assertNotEquals(controllerMappa,controllerMappa1);
	}

	@Test
	public void testGetPartenzaAlieni() {
		
		assertEquals(controllerMappa.getPartenzaAlieni(),"L06");

	}

	@Test
	public void testGetPartenzaUmani() {
		assertEquals(controllerMappa.getPartenzaUmani(),"L08");
	}

	@Test
	public void testGetSettoriScialuppa() {
		
		listaScialuppe.add(mappa.getListaSettoriTotali().get(24));
		listaScialuppe.add(mappa.getListaSettoriTotali().get(44));
		listaScialuppe.add(mappa.getListaSettoriTotali().get(277));
		listaScialuppe.add(mappa.getListaSettoriTotali().get(297));
		assertEquals(controllerMappa.getSettoriScialuppa(),listaScialuppe);
	}

	@Test
	public void testGetSettoriVuoti() {
		
		settoriVuoti.add(mappa.getListaSettoriTotali().get(0));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(3));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(4));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(14));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(18));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(19));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(22));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(60));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(64));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(65));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(72));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(83));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(88));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(99));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(118));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(123));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(138));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(139));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(141));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(142));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(147));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(148));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(149));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(150));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(151));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(159));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(160));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(161));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(183));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(203));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(214));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(224));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(225));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(226));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(237));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(247));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(248));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(258));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(261));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(262));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(281));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(286));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(303));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(304));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(316));
		settoriVuoti.add(mappa.getListaSettoriTotali().get(317));
		
		assertEquals(controllerMappa.getSettoriVuoti(),settoriVuoti);
	}

	@Test
	public void testGetMappa() {
		assertTrue(controllerMappa.getMappa()==mappa);
	}

	@Test
	public void testGetSettoriSicuri() {
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore sicuro")
				settoriSicuri.add(settore);
		}
		assertEquals(controllerMappa.getSettoriSicuri(),settoriSicuri);
	}

	@Test
	public void testGetSettoriPericolosi() {
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore pericoloso")
				settoriPericolosi.add(settore);
		}
		assertEquals(controllerMappa.getSettoriPericolosi(),settoriPericolosi);
	}

	@Test
	public void testNumeroScialuppeBloccate() {
		assertTrue(controllerMappa.numeroScialuppeBloccate()==0);
	}

	@Test
	public void testVerificaEsistenzaSettore() {
		assertTrue(controllerMappa.verificaEsistenzaSettore("A01"));
		assertFalse(controllerMappa.verificaEsistenzaSettore("Z01"));
	}

	@Test
	public void testVerificaMossa() {
		assertTrue(controllerMappa.verificaMossa("G04", "J02", 3, false));
		assertFalse(controllerMappa.verificaMossa("C04", "E05", 2, false));
		assertTrue(controllerMappa.verificaMossa("V02", "V01", 1, true));
		assertTrue(controllerMappa.verificaMossa("V02", "U01", 2, true));
	}

	@Test
	public void testSettoriAdiacenti() {
		settoriAdiacenti.add(mappa.getListaSettoriTotali().get(1));
		settoriAdiacenti.add(mappa.getListaSettoriTotali().get(23));
		assertEquals(controllerMappa.settoriAdiacenti("A01"),settoriAdiacenti);
	}

	@Test
	public void testConvertitoreStringa_Indice() {
		assertEquals(controllerMappa.convertitoreStringa_Indice("K03"),56);
	}

	@Test
	public void testVerificaVittoria() {
		assertFalse(controllerMappa.verificaVittoria("V02", "V01", 1, Personaggio.ALIENO));
//		assertTrue(controllerMappa.verificaVittoria("V02", "V01", 1, Personaggio.UMANO));
	}

	@Test
	public void testIsScialuppa() {
		assertTrue(controllerMappa.isScialuppa("B02"));
		assertFalse(controllerMappa.isScialuppa("A01"));
	}

	@Test
	public void testSettoreSicuro_Pericoloso() {
		assertTrue(controllerMappa.settoreSicuro_Pericoloso("H03")=="sicuro");
		assertTrue(controllerMappa.settoreSicuro_Pericoloso("K08")=="pericoloso");
		assertFalse(controllerMappa.settoreSicuro_Pericoloso("H03")=="pericoloso");
		assertFalse(controllerMappa.settoreSicuro_Pericoloso("K08")=="sicuro");
	}

}

