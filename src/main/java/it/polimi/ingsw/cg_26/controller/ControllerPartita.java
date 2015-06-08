package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.GameState;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


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
		
		while(partita.getNumeroTurno()!=40 && !(partita.getStato().equals(GameState.FINEGIOCO)))
		{
			for(int i=0;i<partita.getGiocatori().size();i++)
			{
				partita.setNumeroGiocatoreCorrente(i);
				if(!this.giocatoreCorrente().getInVita())
					continue;
				while(this.giocatoreCorrente().getHaPassato()==false)
				{
					System.out.println("E' il turno del giocatore "+(i+1)+" "+this.giocatoreCorrente().getNomeUtente()+":");
					System.out.println("\tInserisci l'azione che vuoi compiere.");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String comando=br.readLine();
					ControllerAzioni controllerAzioni=new ControllerAzioni(comando, this);
					controllerAzioni.agisci();
				}
				if(this.controllaFinePartita()==true)
					break;
				this.giocatoreCorrente().setHaPassato(false);
			}
			this.aggiornaTurno();
		}
		if(partita.getNumeroTurno()==40)
		{
			for(Giocatore giocatore : this.getPartita().getGiocatori())
				if(giocatore.getPersonaggio().equals(Personaggio.ALIENO))
					giocatore.setVittoria_sconfitta("vittoria");
				else
					if(giocatore.getPersonaggio().equals(Personaggio.UMANO) && giocatore.getInVita())
						giocatore.setVittoria_sconfitta("sconfitta");
		}
	}
	
	public ModelPartita getPartita()
	{
		return partita;
	}
	
	public boolean controllaFinePartita()
	{
		if(this.numeroUmaniInGioco()==0 || partita.getControllerMappa().numeroScialuppeBloccate()==4 || this.numeroAlieniInGioco()==0)
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
				if(giocatore.getVittoria_sconfitta()=="sconfitta")
					partita.addGiocatorePerdente(giocatore);
				else
					partita.addGiocatoreVincente(giocatore);
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
	
	public void stampaVincitoriEPerdenti()
	{
		System.out.println("La partita � terminata.");
		System.out.print("Ecco i vincitori: ");
		for(Giocatore giocatore : this.getPartita().getGiocatoriVincenti())
			System.out.print(giocatore.getNomeUtente()+" ");
		System.out.println(".");
		System.out.print("Ecco i perdenti: ");
		for(Giocatore giocatore : this.getPartita().getGiocatoriPerdenti())
			System.out.print(giocatore.getNomeUtente()+" ");
		System.out.println(".");
	}
	
//	--------------------------------------------------------------------------------------------------
	//POSSIBILI METODI PER UNA EVENTUALE CLASSE CONTROLLERGIOCATORE ?!?!?!?!?
	
	public ArrayList<Giocatore> getGiocatoriInSettore(String settore)
	{
		ArrayList<Giocatore> listaGiocatoriInSettore=new ArrayList<>();
		for(Giocatore giocatore : partita.getGiocatori())
		{
			if(!giocatore.getInVita())
				continue;
			if(giocatore.getPosizione().equals(settore))
				listaGiocatoriInSettore.add(giocatore);
		}
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
	
	public int numeroAlieniInGioco()
	{
		int contatoreAlieni=0;
		for(Giocatore giocatore : partita.getGiocatori())
		{
			if(giocatore.getPersonaggio().equals(Personaggio.ALIENO) && giocatore.getInVita()==true)
				contatoreAlieni++;
		}
		return contatoreAlieni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((partita == null) ? 0 : partita.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ControllerPartita other = (ControllerPartita) obj;
		if (partita == null) {
			if (other.partita != null)
				return false;
		} else if (!partita.equals(other.partita))
			return false;
		return true;
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
