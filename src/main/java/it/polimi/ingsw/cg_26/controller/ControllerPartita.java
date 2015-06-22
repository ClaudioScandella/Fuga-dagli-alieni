package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.GameState;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ControllerPartita
{
	private ModelPartita partita;
	private LOG log;
	private ControllerAzioni controllerAzioni;
	
	public ControllerPartita(ModelPartita partita)
	{
		this.partita=partita;
		log=new LOG();
		partita.setStato(GameState.RUNNING);
		System.out.print("PARTITA INIZIATA!");
	}
	
	public LOG getLog()
	{
		return log;
	}
	
	public void addGiocatore(Giocatore g)
	{
		this.partita.setGiocatore(g);
	}
	
	public void assegnaRuoli()
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
	
	public void avanzaPartita(String comando) throws IOException
	{
		if(partita.getNumeroTurno()!=40 && !(partita.getStato().equals(GameState.FINEGIOCO)))
		{
				controllerAzioni=new ControllerAzioni(comando, this);
				controllerAzioni.agisci();
				if(this.giocatoreCorrente().getHaPassato()==false)
					return;
				if(this.controllaFinePartita())
				{
					this.terminaPartita();
					return;
				}
				this.giocatoreCorrente().setHaPassato(false);
				
				int contatoreGiocatoriInGiocoDopoGiocatoreCorrente=0;
				for(int i=this.partita.getNumeroGiocatoreCorrente();i<this.partita.getGiocatori().size()-1;i++)
				{
					if((this.partita.getNumeroGiocatoreCorrente()+1)==this.partita.getGiocatori().size())
						break;
					if(this.partita.getGiocatori().get(i+1).getInVita())
						contatoreGiocatoriInGiocoDopoGiocatoreCorrente++;
				}
				if(contatoreGiocatoriInGiocoDopoGiocatoreCorrente==0)
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
			this.terminaPartita();
		}
		else
		{
			String nomeGiocatoreCheHaPassato=this.giocatoreCorrente().getNomeUtente();
			this.partita.aggiornaGiocatoreCorrente();
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, "Hai passato.\n");
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, nomeGiocatoreCheHaPassato+" ha passato.\n");
			
		}
	}
	
	public void inserisciSettoreDestinazione(String settore)
	{
		controllerAzioni.inserisciSettoreDestinazione(settore);
	}
	
	public void inserisciCartaOggetto(String oggetto)
	{
		controllerAzioni.inserisciCartaOggetto(oggetto);
	}
	
	public void inserisciSettoreLuci(String settore)
	{
		controllerAzioni.inserisciSettoreLuci(settore);
	}
	
	public void inserisciSettoreRumoreAScelta(String settore)
	{
		controllerAzioni.inserisciSettoreRumoreAScelta(settore);
	}
	
	public void inserisciProprioSettore(String settore)
	{
		controllerAzioni.inserisciProprioSettore(settore);
	}
	
	public void usaOscarta(String comando)
	{
		this.controllerAzioni.usaOscarta(comando);
	}
	
	public void scartaOggetto(String comando)
	{
		this.controllerAzioni.scartaOggetto(comando);
	}
	
	public ModelPartita getPartita()
	{
		return partita;
	}
	
	public boolean controllaFinePartita()
	{
		if(this.numeroUmaniInGioco()==0 || partita.getControllerMappa().numeroScialuppeBloccate()==4)
			return true;
		return false;
	}
	
	public void terminaPartita()
	{
		partita.setStato(GameState.FINEGIOCO);
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
		this.stampaVincitoriEPerdenti();
	}
	
	public void stampaVincitoriEPerdenti()
	{
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, "La partita � terminata.\nEcco i vincitori:");
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, "La partita � terminata.\nEcco i vincitori:");
		for(Giocatore giocatore : this.getPartita().getGiocatoriVincenti())
		{
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, " "+giocatore.getNomeUtente()+"("+giocatore.getPersonaggio().name()+")");
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, " "+giocatore.getNomeUtente()+"("+giocatore.getPersonaggio().name()+")");
		}
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, ".\nEcco i perdernti:");
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, ".\nEcco i perdenti:");
		for(Giocatore giocatore : this.getPartita().getGiocatoriPerdenti())
		{
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, " "+giocatore.getNomeUtente()+"("+giocatore.getPersonaggio().name()+")");
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, " "+giocatore.getNomeUtente()+"("+giocatore.getPersonaggio().name()+")");
		}
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, ".\n");
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, ".\n");
	}
	
//	--------------------------------------------------------------------------------------------------
	
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
