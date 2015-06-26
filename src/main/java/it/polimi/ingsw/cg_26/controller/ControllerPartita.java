package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.GameState;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Contiene la classe ControllerPartita, che permette di gestire la classe
 * ModelPartita del model.
 * 
 * @author Claudio e Patrizia
 *
 */
public class ControllerPartita
{
	private ModelPartita partita;
	private LOG log;
	private ControllerAzioni controllerAzioni;
	
	/**
	 * Costruttore. Assegna un riferimento alla partita passata in ingresso alla
	 * variabile partita, crea un LOG, mette lo stato della partita a RUNNING e
	 * stampa "PARTITA INIZIATA!"
	 * 
	 * @param partita
	 */
	public ControllerPartita(ModelPartita partita)
	{
		this.partita=partita;
		log=new LOG();
		partita.setStato(GameState.RUNNING);
		System.out.print("PARTITA INIZIATA!");
	}
	
	/**
	 * Ritorna il LOG
	 * 
	 * @return
	 */
	public LOG getLog()
	{
		return log;
	}
	
	/**
	 * Aggiunge alla partita il Giocatore g passato come parametro
	 * in ingresso
	 * 
	 * @param g è il giocatore che deve essere aggiunto alla partita
	 */
	public void addGiocatore(Giocatore g)
	{
		this.partita.setGiocatore(g);
	}
	
	/**
	 * Assegna un ruolo ai giocatore (attribuendo un valore alla variabile personaggio
	 * del Giocatore), imposta la portata (cioè il numero massimo di settori di 
	 * cui si può muovere un giocatore durante una mossa) e posiziona tutti i giocatori
	 * nel loro settore di partenza.
	 * 
	 * Per prima cosa mischia la lista dei giocatori, in modo tale che non sia possibile
	 * risalire al ruolo di un giocatore conoscendo l'ordine in cui sono stati aggiunti alla partita.
	 * Ad ogni giocatore viene quindi assegnato un personaggio e, a seconda di questo, una
	 * portata e la posizione iniziale.
	 * Dopodiché la lista dei giocatori viene mischiata nuovamente, per garantire che i personaggi
	 * dei giocatori non siano alternati.
	 */
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

	/**
	 * Permette di mischiare la lista dei giocatori.
	 */
	public void mischiaGiocatori()
	{
        long seed = System.nanoTime();
        Collections.shuffle(partita.getGiocatori(), new Random(seed));
    }
	
	/**
	 * Somma 1 al numero del turno.
	 */
	public void aggiornaTurno()
	{
		partita.setNumeroTurno((partita.getNumeroTurno())+1);
	}
	
	/**
	 * Se il numero del turno è diverso da 40 o lo stato di gioco non è FINEGIOCO,
	 * viene creato un ControllerAzioni e viene chiamato il metodo agisci(), in modo tale che
	 * il comando passato in ingresso venga eseguito. Dopodiché se la variabile haPassato del Giocatore
	 * corrente è uguale a false, allora il metodo ritorna; se invece è true, controlla se la partita
	 * è finita: se la partita è finita, chiama il metodo terminaPartita(); altrimenti pone haPassato a 
	 * false e conta il numero di giocatori che sono rimasti in gioco  dopo il giocatore corrente (cioè 
	 * il numero di giocatori che devono ancora giocare prima di passare al turno di gioco successivo) e,
	 * nel caso in cui questo numero sia zero, chiama il metodo aggiornaTurno().
	 * 
	 * Se il numero del turno è uguale a 40: controlla tutti i giocatori ed attribuisce il valore "vittoria" 
	 * a tutti gli alieni in gioco, e il valore "sconfitta" a tutti gli umani in gioco.
	 * Altrimenti: aggiorna il giocatore corrente.
	 * @param comando
	 * @throws IOException
	 */
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
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, "Hai passato.");
			this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, nomeGiocatoreCheHaPassato+" ha passato.");
			
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
	
	/**
	 * Se non ci sono umani in gioco o se le scialuppe sono tutte bloccate, la 
	 * partita è finita e il metodo ritorna true; altrimenti ritorna false. 
	 * 
	 * @return
	 */
	public boolean controllaFinePartita()
	{
		if(this.numeroUmaniInGioco()==0 || partita.getControllerMappa().numeroScialuppeBloccate()==4)
			return true;
		return false;
	}
	
	/**
	 * Cambia lo stato della partita in FINEGIOCO. Poi aggiunge i giocatori alle liste
	 * dei giocatori vincenti e perdenti, in base al valore della variabile vittoria_sconfitta
	 * del personaggio.
	 * 
	 * Controlla tutti i giocatori: se il giocatore è umano e ha "sconfitta" come valore
	 * della variabile vittoria_sconfitta, allora lo aggiunge alla lista dei perdenti; 
	 * altrimenti a quella dei vincenti.
	 * Al termine di questo passaggio, se la lista dei perdeni è ancora vuota, vi aggiunge tutti gli
	 * alieni. Se invece la lista dei perdenti non è vuota, aggiunge tutti gli alieni alla lista dei 
	 * giocatori vincenti.
	 * Dopodiché chiama il metodo stampaVincitoriEPerdenti().
	 */
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
	
	/**
	 * Questo metodo stampa la lista dei giocatori vincenti e quella dei perdenti.
	 */
	public void stampaVincitoriEPerdenti()
	{
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 5, "La partita è terminata.\nEcco i vincitori:");
		this.log.setLOG(this.partita.getNumeroGiocatoreCorrente(), this.partita.getNumeroTurno(), 4, "La partita è terminata.\nEcco i vincitori:");
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
	/**
	 * Ritorna un arraylist contentente tutti i giocatori presenti nel settore 
	 * passato in ingresso.
	 * 
	 * Controlla, per ogni giocatore, se è invita e se la sua posizione è uguale
	 * al settore considerato: in caso affemrativo lo aggiunge alla lista.
	 * 
	 * @param settore rappresenta le coordinate del settore
	 * @return lista di giocatori presenti nel settore
	 */
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
/**
 * Ritorna il giocatore che deve giocare.
 * 
 * @return giocatore corrente
 */
	public Giocatore giocatoreCorrente()
	{
		return partita.getGiocatori().get(partita.getNumeroGiocatoreCorrente());
	}
	
	/**
	 * Conta il numero degli umani in gioco, ossia quelli che non sono stati uccisi
	 * e che non sono ancora scappati (e che quindi hanno true come attributo
	 * della variabile inVita.
	 * 
	 * @return
	 */
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
	/**
	 * Conta il numero degli alieni in gioco, ossia quelli che non sono stati uccisi da un attacco
	 * umano effettuato grazie ad una carta oggetto.
	 * 
	 * @return
	 */
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
