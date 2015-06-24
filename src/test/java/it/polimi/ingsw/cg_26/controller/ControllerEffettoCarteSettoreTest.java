package it.polimi.ingsw.cg_26.controller;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore.TipoSettore;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ControllerEffettoCarteSettoreTest {
	
	private ControllerEffettoCarteSettore controller;
	private ControllerPartita controllerPartita;
	private ModelPartita partita;
	private ArrayList<CartaSettore> lista = new ArrayList<CartaSettore>();
	private CartaSettore c1, c2, c3;

	@Before
	public void setUp() throws Exception {
		partita = new ModelPartita(1234, "galilei");
		controllerPartita = new ControllerPartita(partita);
		controller = new ControllerEffettoCarteSettore(controllerPartita);
		c1 = new CartaSettore(TipoSettore.SILENZIO, false);
		c2 = new CartaSettore(TipoSettore.RUMOREaSCELTA, false);
		c3 = new CartaSettore(TipoSettore.RUMOREproprioSETTORE, false);

		
	}

	@Test
	public void testControllerEffettoCarteSettore() {
		assertTrue(controller instanceof ControllerEffettoCarteSettore);
	}

	@Test
	public void testPescaEdEseguiEffettoCarta() {

	}

}
