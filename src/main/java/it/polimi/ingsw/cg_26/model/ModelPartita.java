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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
		return result;
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
