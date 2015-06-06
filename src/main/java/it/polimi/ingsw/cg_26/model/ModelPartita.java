package it.polimi.ingsw.cg_26.model;

import java.util.ArrayList;

import model.mappa.Mappa;
import model.mazzi.MazzoCarteOggetto;
import model.mazzi.MazzoCarteScialuppa;
import model.mazzi.MazzoCarteSettore;
import controller.ControllerMappa;
import controller.ControllerMazzoCarteOggetto;
import controller.ControllerMazzoCarteScialuppa;
import controller.ControllerMazzoCarteSettore;

public class ModelPartita {
	
	private int idPartita;
	private String nomeMappa;
	private ArrayList<Giocatore> giocatori;
	private ArrayList<Giocatore> giocatoriFuoriGioco=new ArrayList<>();
	private ArrayList<Giocatore> giocatoriPerdenti=new ArrayList<>();
	private ArrayList<Giocatore> giocatoriVincenti=new ArrayList<>();
	private GameState stato;
	private LOG log;
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
	
//	--------------------------------------------------------------------------------------------------

	public ModelPartita(int idPartita, String mappa)
	{
//		int idGiocatore=0;
		this.setStato(GameState.INIZIALIZZAZIONE);
		giocatori=new ArrayList<>();
		giocatoriFuoriGioco=new ArrayList<>();
		this.idPartita=idPartita;
		this.nomeMappa=mappa;
		this.mappa=new Mappa(mappa);
		controllerMappa=new ControllerMappa(this.mappa);
		this.log=new LOG();
		mazzoCarteScialuppa=new MazzoCarteScialuppa();
		controllerMazzoCarteScialuppa=new ControllerMazzoCarteScialuppa(mazzoCarteScialuppa);
		mazzoCarteSettore=new MazzoCarteSettore();
		controllerMazzoCarteSettore=new ControllerMazzoCarteSettore(mazzoCarteSettore);
		mazzoCarteOggetto=new MazzoCarteOggetto();
		controllerMazzoCarteOggetto=new ControllerMazzoCarteOggetto(mazzoCarteOggetto);
//		this.setGiocatore(new Giocatore(idGiocatore++));
//		this.setGiocatore(new Giocatore(idGiocatore++));
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public GameState getStato()
	{
		return this.stato;
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

	public ArrayList<Giocatore> getGiocatoriFuoriGioco()
	{
		return giocatoriFuoriGioco;
	}
	
	public ArrayList<Giocatore> getGiocatoriPerdenti()
	{
		return giocatoriPerdenti;
	}
	
	public ArrayList<Giocatore> getGiocatoriVincenti()
	{
		return giocatoriVincenti;
	}

	public LOG getLog()
	{
		return log;
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
	
//	--------------------------------------------------------------------------------------------------

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
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
