package it.polimi.ingsw.cg_26;

import java.util.*;

public class LogicaDiGioco
{
	private int numeroGiocatori;
	private ArrayList<Giocatore> listaGiocatori=new ArrayList<Giocatore>();
	private Mappa mappa;
	private ArrayList<MazzoSettori> mazzoCarteSettore;
	private ArrayList<MazzoOggetti> mazzoCarteOggetto;
	private ArrayList<MazzoScialuppa> mazzoCarteScialuppa;
	private int turnoCorrente;
	private int maxTurni;
	private int indiceGiocatore;
	
	public void creaMazzi()
	{
		//crea mazzi carte
		mazzoCarteSettore=new ArrayList<MazzoSettori>();
		mazzoCarteOggetto=new ArrayList<MazzoOggetti>();
		mazzoCarteScialuppa=new ArrayList<MazzoScialuppa>();
	}
	
	public void assegnaRuoli()
	{
		//crea i giocatori e ne assegna i ruoli: umano o alieno
		for(int i=1;i<numeroGiocatori;i++)
		{
			if(i%2==0)
			{
				this.listaGiocatori.add(new Alieno(i));
			}
			else
			{
				this.listaGiocatori.add(new Umano(i));
			}
		}
	}
	
	public void posizionaGiocatore()
	{
		
	}
	
	public void scegliMappa()
	{
		
	}
	
	public void gioca()
	{
		
	}
	
	public void aggiornaStatoGioco()
	{
		
	}
	
}
