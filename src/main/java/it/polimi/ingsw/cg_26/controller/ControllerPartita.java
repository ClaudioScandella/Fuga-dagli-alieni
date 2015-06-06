package it.polimi.ingsw.cg_26.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import model.GameState;
import model.Giocatore;
import model.ModelPartita;
import model.Giocatore.Personaggio;
import model.carte.CartaSettore;
import model.mappa.Mappa;

public class ControllerPartita {
	
	private ModelPartita partita;
//	private String mappa;
	
	public ControllerPartita(ModelPartita partita)
	{
		this.partita=partita;
	}
	
	public void addGiocatore(Giocatore g)
	{
		this.partita.setGiocatore(g);
	}
	
	private void assegnaRuoli()
	{
		this.mischiaGiocatori();
		for(int i=0;i<partita.getGiocatori().size();i++)
		{
			if(i%2==0)
				partita.getGiocatori().get(i).setPersonaggio(Personaggio.ALIENO, 2, partita.getControllerMappa().getPartenzaAlieni());
			else
				partita.getGiocatori().get(i).setPersonaggio(Personaggio.UMANO, 1, partita.getControllerMappa().getPartenzaUmani());
		}
		this.mischiaGiocatori();
	}

	public void mischiaGiocatori()
	{
        long seed = System.nanoTime();
        Collections.shuffle(partita.getGiocatori(), new Random(seed));
    }
	
	public void aggiornaTurno()
	{
		partita.setNumeroTurno((partita.getNumeroTurno())+1);
	}
	
	public void iniziaPartita() throws IOException
	{
		this.assegnaRuoli();
		partita.setStato(GameState.RUNNING);
		
		while(partita.getNumeroTurno()!=40 && partita.getStato()!=GameState.FINEGIOCO)
		{
			for(int i=0;i<partita.getGiocatori().size();i++)
			{
				partita.setNumeroGiocatoreCorrente(i);
				while(this.giocatoreCorrente().getHaPassato()==false)
				{
					System.out.println("E' il turno del giocatore "+(i+1)+" "+this.giocatoreCorrente().getNomeUtente()+":");
					System.out.println("\tInserisci l'azione che vuoi compiere.");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String comando=br.readLine();
					ControllerAzioni controllerAzioni=new ControllerAzioni(comando, this);
					controllerAzioni.agisci();
				}
				this.giocatoreCorrente().setHaPassato(false);
				if(this.controllaFinePartita())
					break;
			}
			this.aggiornaTurno();
		}
	}
	
	public ModelPartita getPartita()
	{
		return partita;
	}
	
	public boolean controllaFinePartita()
	{
		if(this.numeroUmaniInGioco()==0)
		{
			partita.setStato(GameState.FINEGIOCO);
			return true;
		}
		return false;
	}
	
	public void terminaPartita()
	{
		for(Giocatore giocatore : partita.getGiocatori())
		{
			if(giocatore.getPersonaggio().equals(Personaggio.UMANO))
			{
				if(giocatore.getVittoria_sconfitta()=="vittoria")
					partita.addGiocatoreVincente(giocatore);
				else
					partita.addGiocatorePerdente(giocatore);
			}
		}
		if(partita.getGiocatoriPerdenti().isEmpty())
		{
			for(Giocatore giocatore : partita.getGiocatori())
			{
				if(giocatore.getPersonaggio().equals(Personaggio.ALIENO))
					partita.addGiocatorePerdente(giocatore);
			}
		}
		else
		{
			for(Giocatore giocatore : partita.getGiocatori())
			{
				if(giocatore.getPersonaggio().equals(Personaggio.ALIENO))
					partita.addGiocatoreVincente(giocatore);
			}	
		}
	}
	
//	--------------------------------------------------------------------------------------------------
	//POSSIBILI METODI PER UNA EVENTUALE CLASSE CONTROLLERGIOCATORE ?!?!?!?!?
	
	public ArrayList<Giocatore> getGiocatoriInSettore(String settore)
	{
		ArrayList<Giocatore> listaGiocatoriInSettore=new ArrayList<>();
		for(Giocatore giocatore : partita.getGiocatori())
			if(giocatore.getPosizione().equals(settore))
				listaGiocatoriInSettore.add(giocatore);
		return listaGiocatoriInSettore;
	}
	
	public Giocatore giocatoreCorrente()
	{
		return partita.getGiocatori().get(partita.getNumeroGiocatoreCorrente());
	}
	
	public int numeroUmaniInGioco()
	{
		int contatoreUmani=0;
		for(Giocatore giocatore : partita.getGiocatori())
		{
			if(giocatore.getPersonaggio().equals(Personaggio.UMANO) && giocatore.getInVita()==true)
				contatoreUmani++;
		}
		return contatoreUmani;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
