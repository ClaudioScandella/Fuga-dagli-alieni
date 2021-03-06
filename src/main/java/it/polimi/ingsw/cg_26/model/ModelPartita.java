package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.controller.ControllerMappa;
import it.polimi.ingsw.cg_26.controller.ControllerMazzoCarteOggetto;
import it.polimi.ingsw.cg_26.controller.ControllerMazzoCarteScialuppa;
import it.polimi.ingsw.cg_26.controller.ControllerMazzoCarteSettore;
import it.polimi.ingsw.cg_26.model.mappa.Mappa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteOggetto;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteScialuppa;
import it.polimi.ingsw.cg_26.model.mazzi.MazzoCarteSettore;

import java.util.ArrayList;

/**
 * Contiene la classe ModelPartita, in cui si tiene traccia
 * della mappa, dello stato di gioco, del turno, del numero del giocatore
 * corrente e delle liste di giocatori (tutti i giocatori, giocatori vincenti,
 * giocatori perdenti.
 * Vengono inoltre create le mappe, i tre tipi di mazzi e i rispettivi controller.
 *  
 * @author Claudio e Patrizia
 *
 */
public class ModelPartita {
	
	private int idPartita;
	private String nomeMappa;
	private ArrayList<Giocatore> giocatori;
//	private ArrayList<Giocatore> giocatoriFuoriGioco=new ArrayList<>();
	private ArrayList<Giocatore> giocatoriPerdenti=new ArrayList<>();
	private ArrayList<Giocatore> giocatoriVincenti=new ArrayList<>();
	private GameState stato;
	private StatoAvanzamentoTurno statoAvanzamentoTurno;
	/**
	 * 
	 * Indica se un giocatore deve pescare o meno una carta oggetto.
	 *
	 */
	public enum StatoPescaOggetto {DEVE_PESCARE, NON_DEVE_PESCARE;}
	private StatoPescaOggetto statoPescaOggetto=StatoPescaOggetto.NON_DEVE_PESCARE;
	private int numeroTurno=1;
	private int numeroGiocatoreCorrente=0;
	private Mappa mappa;
	private ControllerMappa controllerMappa;
	private MazzoCarteScialuppa mazzoCarteScialuppa;
	private ControllerMazzoCarteScialuppa controllerMazzoCarteScialuppa;
	private MazzoCarteSettore mazzoCarteSettore;
	private ControllerMazzoCarteSettore controllerMazzoCarteSettore;
	private MazzoCarteOggetto mazzoCarteOggetto;
	private ControllerMazzoCarteOggetto controllerMazzoCarteOggetto;
	
//	---COSTRUTTORE-----------------------------------------------------------------------------------------------

	/**
	 * Costruttore del ModelPartita. Inizializza una partita: assegna l'IdPartita
	 * che gli viene passato come parametro, costruisce la mappa (del tipo
	 * che gli viene passato in ingresso come parametro) e i tre mazzi di carte
	 * con i rispettivi controller.
	 * 
	 * @param idPartita è l'intero che permette di identificare una partita
	 * @param mappa è la stringa contenente il nome del tipo di mappa
	 */
	public ModelPartita(int idPartita, String mappa)
	{
		this.setStato(GameState.INIZIALIZZAZIONE);
		this.statoAvanzamentoTurno=StatoAvanzamentoTurno.ATTESA_COMANDO;
		giocatori=new ArrayList<>();
//		giocatoriFuoriGioco=new ArrayList<>();
		this.idPartita=idPartita;
		this.nomeMappa=mappa;
		this.mappa=new Mappa(mappa);
		controllerMappa=new ControllerMappa(this.mappa);
		mazzoCarteScialuppa=new MazzoCarteScialuppa();
		controllerMazzoCarteScialuppa=new ControllerMazzoCarteScialuppa(mazzoCarteScialuppa);
		mazzoCarteSettore=new MazzoCarteSettore();
		controllerMazzoCarteSettore=new ControllerMazzoCarteSettore(mazzoCarteSettore);
		mazzoCarteOggetto=new MazzoCarteOggetto();
		controllerMazzoCarteOggetto=new ControllerMazzoCarteOggetto(mazzoCarteOggetto);
	}
	
//	---GETTER-----------------------------------------------------------------------------------------------
	
	public GameState getStato()
	{
		return this.stato;
	}
	
	public StatoAvanzamentoTurno getStatoAvanzamentoTurno()
	{
		return this.statoAvanzamentoTurno;
	}
	
	public MazzoCarteSettore getMazzoCarteSettore(){
		return this.mazzoCarteSettore;
	}
	
	public StatoPescaOggetto getStatoPescaOggetto()
	{
		return this.statoPescaOggetto;
	}

	public int getIdPartita()
	{
		return idPartita;
	}

	public String getNomeMappa()
	{
		return nomeMappa;
	}

	public ArrayList<Giocatore> getGiocatori()
	{
		return giocatori;
	}

//	public ArrayList<Giocatore> getGiocatoriFuoriGioco()
//	{
//		return giocatoriFuoriGioco;
//	}
	
	public ArrayList<Giocatore> getGiocatoriPerdenti()
	{
		return giocatoriPerdenti;
	}
	
	public ArrayList<Giocatore> getGiocatoriVincenti()
	{
		return giocatoriVincenti;
	}

	public ControllerMappa getControllerMappa()
	{
		return controllerMappa;
	}

	public ControllerMazzoCarteScialuppa getControllerMazzoCarteScialuppa()
	{
		return controllerMazzoCarteScialuppa;
	}

	public ControllerMazzoCarteSettore getControllerMazzoCarteSettore()
	{
		return controllerMazzoCarteSettore;
	}

	public ControllerMazzoCarteOggetto getControllerMazzoCarteOggetto()
	{
		return controllerMazzoCarteOggetto;
	}
	
	public int getNumeroTurno()
	{
		return numeroTurno;
	}
	
	public int getNumeroGiocatoreCorrente()
	{
		return numeroGiocatoreCorrente;
	}
	
//	---SETTER-----------------------------------------------------------------------------------------------
	
	public void setStatoAvanzamentoTurno(StatoAvanzamentoTurno stato)
	{
		this.statoAvanzamentoTurno=stato;
	}
	
	public void setStatoPescaOggetto(StatoPescaOggetto stato)
	{
		this.statoPescaOggetto=stato;
	}
	
	public void setGiocatore(Giocatore nuovoGiocatore)
	{
		giocatori.add(nuovoGiocatore);
	}
	
	public void setStato(GameState stato)
	{
		this.stato=stato;
	}
	
	public void setNumeroTurno(int numeroTurno)
	{
		this.numeroTurno = numeroTurno;
	}
	
	public void setNumeroGiocatoreCorrente(int numeroGiocatoreCorrente)
	{
		this.numeroGiocatoreCorrente=numeroGiocatoreCorrente;
	}
	
	public void addGiocatorePerdente(Giocatore giocatorePerdente)
	{
		this.giocatoriPerdenti.add(giocatorePerdente);
	}
	
	public void addGiocatoreVincente(Giocatore giocatoreVincente)
	{
		this.giocatoriVincenti.add(giocatoreVincente);
	}
	
//	--------------------------------------------------------------------------------------------------
	
	/**
	 * Aggiorna il numero del giocatore corrente tralasciando i numeri 
	 * dei giocatori morti.
	 */
	public void aggiornaGiocatoreCorrente()
	{
		int contatore=0;
		for(Giocatore giocatore : this.giocatori)
		{
			if(!giocatore.getInVita())
				contatore++;
		}
		if(contatore==this.giocatori.size())
			return;
		this.numeroGiocatoreCorrente++;
		if(this.numeroGiocatoreCorrente>=this.giocatori.size())
			this.numeroGiocatoreCorrente=0;
		if(!this.giocatori.get(numeroGiocatoreCorrente).getInVita())
			this.aggiornaGiocatoreCorrente();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelPartita other = (ModelPartita) obj;
		if (stato != other.stato)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		return result;
	}
}
