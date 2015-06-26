package it.polimi.ingsw.cg_26.main;

import it.polimi.ingsw.cg_26.controller.ControllerPartita;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.socket.ClientHandler;

import java.io.IOException;
import java.util.ArrayList;

public class Main
{
	private int idPartita;
	private String nomeMappa;
	private ModelPartita modelPartita;
	private ControllerPartita controllerPartita;
	public enum Stato {DISPONIBILE, PIENA, INIZIATA, FINITA;}
	private Stato stato;

	//COSTRUTTORE
	public Main(int idPartita, String nomeMappa)
	{
		this.idPartita=idPartita;
		this.nomeMappa=nomeMappa;
	}
	
//	---GETTER-----------------------------------------------------------------------------------------------
	
	public int getIdPartita()
	{
		return idPartita;
	}

	public String getNomeMappa()
	{
		return nomeMappa;
	}

	public Stato getStato()
	{
		return stato;
	}
	
	public ModelPartita getModelPartita()
	{
		return modelPartita;
	}

	public ControllerPartita getControllerPartita()
	{
		return controllerPartita;
	}

	public void setStato(Stato stato)
	{
		this.stato=stato;
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public void aggiungiGiocatori(ArrayList<ClientHandler> clients)
	{
		for(ClientHandler client : clients)
		{
			this.controllerPartita.addGiocatore(new Giocatore(client.getIdClient(),client.getNomeClient()));
		}
		System.out.println("Giocatori aggiunti.");
		this.controllerPartita.assegnaRuoli();
		}
	
	public void inzializzaPartita() throws IOException
	{
		this.setStato(Stato.INIZIATA);
		this.modelPartita=new ModelPartita(idPartita, nomeMappa);
		this.controllerPartita=new ControllerPartita(modelPartita);
	}
	
	public ArrayList<String> avanzaPartita(String comando) throws IOException
	{
		String mexPrivato="";
		String mexPubblico="";
		ArrayList<String> messaggi=new ArrayList<>();
		messaggi.add(mexPrivato);
		messaggi.add(mexPubblico);
		this.controllerPartita.getLog().azzeraLog(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno());
		this.controllerPartita.getLog().azzeraLog(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno());
		switch(this.modelPartita.getStatoAvanzamentoTurno())
		{
		case ATTESA_COMANDO:
			this.controllerPartita.avanzaPartita(comando);
			break;
		case ATTESA_SETTORE_DESTINAZIONE:
			this.controllerPartita.inserisciSettoreDestinazione(comando);
			break;
		case ATTESA_CARTA:
			this.controllerPartita.inserisciCartaOggetto(comando);
			break;
		case ATTESA_SETTORE_LUCI:
			this.controllerPartita.inserisciSettoreLuci(comando);
			break;
		case ATTESA_SETTORE_RUMORE:
			this.controllerPartita.inserisciSettoreRumoreAScelta(comando);
			break;
		case ATTESA_PROPRIO_SETTORE:
			this.controllerPartita.inserisciProprioSettore(comando);
			break;
		case ATTESA_CARTA_DA_SCARTARE:
			this.controllerPartita.scartaOggetto(comando);
			break;
		case ATTESA_USA_O_SCARTA:
			this.controllerPartita.usaOscarta(comando);
			break;
		}
		mexPrivato=this.generaMexPrivato();
		mexPubblico=this.generaMexPubblico();
		messaggi.set(0, mexPrivato);
		messaggi.set(1, mexPubblico);
		return messaggi;
	}

	public String generaMexPrivato()
	{
		String mexPrivato="";
		if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 5)!="")
			mexPrivato+=this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 5);
		return mexPrivato;
	}
	
	public String generaMexPubblico()
	{
		String mexPubblico="";
		if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 4).equals(""))
			;
		else
			mexPubblico+=this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 4);
		return mexPubblico;
	}
	
	public void eliminaClientDaPartita(int idClient)
	{
		Giocatore giocatore=null;
		for(Giocatore g : this.getModelPartita().getGiocatori())
		{
			if(g.getIdGiocatore()==idClient)
				giocatore=g;
		}
		if(this.controllerPartita.giocatoreCorrente().getIdGiocatore()==idClient)
		{
			giocatore.setVittoria_sconfitta("sconfitta");
			giocatore.setInVita(false);
			while(giocatore.getCarteOggetto().size()>0)
			{
				CartaOggetto oggetto=this.controllerPartita.giocatoreCorrente().getCarteOggetto().get(0);
				this.controllerPartita.giocatoreCorrente().scartaOggetto(oggetto);
				this.modelPartita.getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
			}
			if(this.controllerPartita.controllaFinePartita())
			{
				this.controllerPartita.terminaPartita();
				return;
			}
			int contatoreGiocatoriInGiocoDopoGiocatoreCorrente=0;
			for(int i=this.modelPartita.getNumeroGiocatoreCorrente();i<this.modelPartita.getGiocatori().size()-1;i++)
			{
				if((this.modelPartita.getNumeroGiocatoreCorrente()+1)==this.modelPartita.getGiocatori().size())
					break;
				if(this.modelPartita.getGiocatori().get(i+1).getInVita())
					contatoreGiocatoriInGiocoDopoGiocatoreCorrente++;
			}
			if(contatoreGiocatoriInGiocoDopoGiocatoreCorrente==0)
				this.controllerPartita.aggiornaTurno();
			if(this.modelPartita.getNumeroTurno()==40)
			{
				for(Giocatore g : this.modelPartita.getGiocatori())
					if(g.getPersonaggio().equals(Personaggio.ALIENO))
						g.setVittoria_sconfitta("vittoria");
					else
						if(g.getPersonaggio().equals(Personaggio.UMANO) && giocatore.getInVita())
							g.setVittoria_sconfitta("sconfitta");
				this.controllerPartita.terminaPartita();
			}
			else
				this.modelPartita.aggiornaGiocatoreCorrente();
			this.modelPartita.setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);

		}
		else
		{
			giocatore.setVittoria_sconfitta("sconfitta");
			giocatore.setInVita(false);
			while(giocatore.getCarteOggetto().size()>0)
			{
				CartaOggetto oggetto=this.controllerPartita.giocatoreCorrente().getCarteOggetto().get(0);
				this.controllerPartita.giocatoreCorrente().scartaOggetto(oggetto);
				this.modelPartita.getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
			}
			if(this.controllerPartita.controllaFinePartita())
			{
				this.controllerPartita.terminaPartita();
				return;
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((controllerPartita == null) ? 0 : controllerPartita
						.hashCode());
		result = prime * result + idPartita;
		result = prime * result
				+ ((modelPartita == null) ? 0 : modelPartita.hashCode());
		result = prime * result
				+ ((nomeMappa == null) ? 0 : nomeMappa.hashCode());
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
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
		Main other = (Main) obj;
		if (controllerPartita == null) {
			if (other.controllerPartita != null)
				return false;
		} else if (!controllerPartita.equals(other.controllerPartita))
			return false;
		if (idPartita != other.idPartita)
			return false;
		if (modelPartita == null) {
			if (other.modelPartita != null)
				return false;
		} else if (!modelPartita.equals(other.modelPartita))
			return false;
		if (nomeMappa == null) {
			if (other.nomeMappa != null)
				return false;
		} else if (!nomeMappa.equals(other.nomeMappa))
			return false;
		if (stato != other.stato)
			return false;
		return true;
	}
}
