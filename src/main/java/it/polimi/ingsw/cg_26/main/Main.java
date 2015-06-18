package it.polimi.ingsw.cg_26.main;


import it.polimi.ingsw.cg_26.Socket.ClientHandler;
import it.polimi.ingsw.cg_26.controller.ControllerPartita;
import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.ModelPartita;

import java.io.IOException;
import java.util.ArrayList;


public class Main
{
	
	private int idPartita;
	private String nomeMappa;
//	private int idGiocatore=0;
	private ModelPartita modelPartita;
	private ControllerPartita controllerPartita;
	public enum Stato {DISPONIBILE, PIENA, INIZIATA, FINITA;}
	private Stato stato;

	public Main(int idPartita, String nomeMappa)
	{
		this.idPartita=idPartita;
		this.nomeMappa=nomeMappa;
	}
	
//	--------------------------------------------------------------------------------------------------
	
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
//		String messaggio="";
		for(ClientHandler client : clients)
		{
			this.controllerPartita.addGiocatore(new Giocatore(client.getIdClient(),client.getNomeClient()));
		}
		System.out.println("Giocatori aggiunti.");
//		this.controllerPartita.addGiocatore(new Giocatore(idGiocatore, nomeUtente));
//		ArrayList<Giocatore> giocatori=this.controllerPartita.assegnaRuoli();
//		for(Giocatore giocatore : giocatori)
//		{
//			
//		}
//		System.out.println("E' il turno del giocatore "+(this.controllerPartita.getPartita().getNumeroGiocatoreCorrente()+1)+" "+this.controllerPartita.giocatoreCorrente().getNomeUtente()+":");
	}
	
	public void inzializzaPartita() throws IOException
	{
		this.setStato(Stato.INIZIATA);
		this.modelPartita=new ModelPartita(idPartita, nomeMappa);
		this.controllerPartita=new ControllerPartita(modelPartita);
//		controllerPartita.iniziaPartita();
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
			System.out.println("A");
			this.controllerPartita.avanzaPartita(comando);
			break;
		case ATTESA_SETTORE_DESTINAZIONE:
			this.controllerPartita.inserisciSettoreDestinazione(comando);
			break;
		case ATTESA_CARTA:
			this.controllerPartita.inserisciCartaOggetto(comando);
			break;
		case ATTESA_MESSAGGIO:
			this.controllerPartita.scriviMessaggioChat(comando);
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
		}
		mexPrivato=this.generaMexPrivato();
		mexPubblico=this.generaMexPubblico();
		messaggi.set(0, mexPrivato);
		messaggi.set(1, mexPubblico);
		return messaggi;
	}

//	public static void main(String[] args) throws InterruptedException, IOException {
//		System.out.println("Inizializzo la partita.");
//		ModelPartita modelPartita=new ModelPartita(idPartita, nomeMappa);
//		ControllerPartita controllerPartita=new ControllerPartita(modelPartita);
////		Giocatore giocatore=new Giocatore(idGiocatore, idPartita);		
//		idPartita++;
//		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Claudio"));
//		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Diego"));
////		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Barbara"));
////		controllerPartita.addGiocatore(new Giocatore(idGiocatore++, "Andrea"));
//		
//		System.out.println("Partita pronta per iniziare. Attendo comando di inizio.");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String comando=br.readLine();
//		
//		if(comando.equals("inizia"))
//			System.out.println("Inizio la partita.");
//			
//		controllerPartita.iniziaPartita();
//		controllerPartita.terminaPartita();
//		controllerPartita.stampaVincitoriEPerdenti();
//	}
	
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
		if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 1).equals(""))
			;
		else if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 1).equals("1"))
			mexPubblico+=this.controllerPartita.giocatoreCorrente().getNomeUtente()+" ha pescato una carta settore.\n";
		else
			mexPubblico+=this.controllerPartita.giocatoreCorrente().getNomeUtente()+" non ha pescato una carta settore.\n";
		if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 2).equals(""))
			;
		else if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 2).equals("0"))
			mexPubblico+=this.controllerPartita.giocatoreCorrente().getNomeUtente()+" ha dichiarato silenzio.\n";
		else if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 2).equals(""))
			;
		else
			mexPubblico+=this.controllerPartita.giocatoreCorrente().getNomeUtente()+" ha dichiarato rumore in settore "+this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 2)+".\n";
		if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 3).equals("0"))
			mexPubblico+=this.controllerPartita.giocatoreCorrente().getNomeUtente()+" non ha pescato alcun oggetto.\n";
		else if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 3).equals("1"))
			mexPubblico+=this.controllerPartita.giocatoreCorrente().getNomeUtente()+" ha pescato un oggetto.\n";
		if(this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 4).equals(""))
			;
		else
			mexPubblico+=this.controllerPartita.getLog().getLOG(this.modelPartita.getNumeroGiocatoreCorrente(), this.modelPartita.getNumeroTurno(), 4);
		return mexPubblico;
	}
	
	//(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, giocatore.getNomeUtente()+" si � salvato con la carta difesa\n");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	

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
	
