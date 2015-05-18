package it.polimi.ingsw.cg_26;

import it.polimi.ingsw.cg_26.CartaOggetto.TipoOggetto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazzoOggetti
{
	private List<CartaOggetto> mazzoOggetti=new ArrayList<>();
	private CartaOggetto cartaOggetto;
	
	
	public MazzoOggetti()
	{
		//crea le carte oggetto: 2 per ognuno
			cartaOggetto=new CartaOggetto(TipoOggetto.ADRENALINA);
			mazzoOggetti.add(cartaOggetto);
			mazzoOggetti.add(cartaOggetto);
			cartaOggetto=new CartaOggetto(TipoOggetto.ATTACCO);
			mazzoOggetti.add(cartaOggetto);
			mazzoOggetti.add(cartaOggetto);
			cartaOggetto=new CartaOggetto(TipoOggetto.LUCI);
			mazzoOggetti.add(cartaOggetto);
			mazzoOggetti.add(cartaOggetto);
			cartaOggetto=new CartaOggetto(TipoOggetto.TELETRASPORTO);
			mazzoOggetti.add(cartaOggetto);
			mazzoOggetti.add(cartaOggetto);
			cartaOggetto=new CartaOggetto(TipoOggetto.SEDATIVI);
			mazzoOggetti.add(cartaOggetto);
			mazzoOggetti.add(cartaOggetto);
			mazzoOggetti.add(cartaOggetto);
			cartaOggetto=new CartaOggetto(TipoOggetto.DIFESA);
			mazzoOggetti.add(cartaOggetto);
	}
	
	public void mostraMazzo()
	{
        for (CartaOggetto carta : this.mazzoOggetti)
        {
            System.out.println(carta.getTipoOggetto());
        }
    }
	
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(this.mazzoOggetti, new Random(seed));
    }

}
