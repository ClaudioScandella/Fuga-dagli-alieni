package it.polimi.ingsw.cg_26;

import it.polimi.ingsw.cg_26.CartaScialuppa.Colore;

import java.util.*;

public class MazzoScialuppa
{
	private List<CartaScialuppa> mazzoScialuppa=new ArrayList<>();
	private CartaScialuppa cartaScialuppa;
	
	
	
	
	public MazzoScialuppa()
	{
		//creo le 6 carte scialuppa: 3 verdi, 3 rosse e le aggiungo a mazzoScialuppa
		for(int i=0;i<3;i++)
		{
			cartaScialuppa=new CartaScialuppa(Colore.VERDE);
			mazzoScialuppa.add(cartaScialuppa);
			cartaScialuppa=new CartaScialuppa(Colore.ROSSA);
			mazzoScialuppa.add(cartaScialuppa);
		}
	}
	
	//stampa il mazzo di carte
	public void mostraMazzo()
	{
        for (CartaScialuppa carta : this.mazzoScialuppa)
        {
            System.out.println(carta.getColore());
        }
    }
	
	//mescola il mazzo
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(this.mazzoScialuppa, new Random(seed));
    }
	
	public CartaScialuppa pescaCarta()
	{
		cartaScialuppa=mazzoScialuppa.get(0);
		mazzoScialuppa.remove(0);
		return cartaScialuppa;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
