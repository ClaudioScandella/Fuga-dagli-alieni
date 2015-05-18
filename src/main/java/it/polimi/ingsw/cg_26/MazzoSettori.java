package it.polimi.ingsw.cg_26;

import it.polimi.ingsw.cg_26.CartaSettore.TipoSettore;

import java.util.*;

public class MazzoSettori
{
	private List<CartaSettore> mazzoSettori=new ArrayList<>();
	private CartaSettore cartaSettore;
//	private final int nCarteSettore=25;
	
	public MazzoSettori()
	{
		//crea tutte le carte e le inserisce in ArrayList carteSettore
		
		//creo le carte Silenzio
		cartaSettore=new CartaSettore(TipoSettore.SILENZIO, true);
		mazzoSettori.add(cartaSettore);
		mazzoSettori.add(cartaSettore);
		cartaSettore=new CartaSettore(TipoSettore.SILENZIO, false);
		for(int i=0;i<5;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
		
		//creo le carte RumoreAscelta
		cartaSettore=new CartaSettore(TipoSettore.RUMORE, true);
		mazzoSettori.add(cartaSettore);
		mazzoSettori.add(cartaSettore);
		mazzoSettori.add(cartaSettore);
		cartaSettore=new CartaSettore(TipoSettore.RUMORE, false);
		for(int i=0;i<6;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
		
		//creo le carte RumoreNelSettore
		cartaSettore=new CartaSettore(TipoSettore.RUMOREinSETTORE, true);
		mazzoSettori.add(cartaSettore);
		mazzoSettori.add(cartaSettore);
		mazzoSettori.add(cartaSettore);
		cartaSettore=new CartaSettore(TipoSettore.RUMOREinSETTORE, false);
		for(int i=0;i<6;i++)
		{
			mazzoSettori.add(cartaSettore);
		}
	}
	
	//stampa il mazzo di carte
	public void mostraMazzo()
	{
        for (CartaSettore carta : this.mazzoSettori)
        {
            System.out.println(carta.getTipoCartaSettore()+" "+carta.getConOggetto());
        }
    }
	
	//mescola il mazzo
	public void mischia()
	{
        long seed = System.nanoTime();
        Collections.shuffle(this.mazzoSettori, new Random(seed));
    }
	
	public CartaSettore pescaCarta()
	{
		cartaSettore=mazzoSettori.get(0);
		mazzoSettori.remove(0);
		return cartaSettore;
	}

}
