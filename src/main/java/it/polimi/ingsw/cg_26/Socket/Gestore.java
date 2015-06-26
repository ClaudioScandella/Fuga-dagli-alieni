package it.polimi.ingsw.cg_26.Socket;

import it.polimi.ingsw.cg_26.main.Main;
import it.polimi.ingsw.cg_26.main.Main.Stato;
import it.polimi.ingsw.cg_26.model.GameState;
import it.polimi.ingsw.cg_26.model.Giocatore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Gestore
{
	private Hashtable<Integer, ClientHandler> listaClientsHash=new Hashtable<Integer, ClientHandler>();
	private Hashtable<Integer, Main> listaPartiteHash=new Hashtable<Integer, Main>();
	private HashMap<Integer, ArrayList<ClientHandler>> listaPartite_Clients=new HashMap<Integer, ArrayList<ClientHandler>>();
	private Hashtable<ClientHandler, Main> listaClients_Partita=new Hashtable<ClientHandler, Main>();
	private ArrayList<ClientHandler> listaClientsNonInPartite=new ArrayList<>();
	private ArrayList<ClientHandler> listaCompletaClients=new ArrayList<>();
	private ArrayList<Main> listaPartite=new ArrayList<>();
	private ArrayList<ClientHandler> clients;
	private static int idPartita=0;
	
	public ClientHandler getClient(int idClient)
	{
		return listaClientsHash.get(idClient);
	}
	
	public Main getPartita(int idPartita)
	{
		return listaPartiteHash.get(idPartita);
	}
	
	public void aggiungiClient(ClientHandler client)
	{
		this.listaClientsHash.put(client.getIdClient(), client);
		this.listaCompletaClients.add(client);
		this.listaClientsNonInPartite.add(client);
	}
	
	private void aggiungiClientAPartita(ClientHandler client, int idPartita)
	{
		clients=this.listaPartite_Clients.get(idPartita);
		clients.add(client);
		this.listaPartite_Clients.put(idPartita, clients);
		this.listaClients_Partita.put(client,this.getPartita(idPartita));
		if(this.listaPartite_Clients.get(idPartita).size()==8)
			this.getPartita(idPartita).setStato(Stato.PIENA);
	}
	
	private void creaNuovaPartita(String nomeMappa, int idClientCreatore)
	{
		clients=new ArrayList<>();
		clients.clear();
		clients.add(this.getClient(idClientCreatore));
		Main main=new Main(idPartita,nomeMappa);
		listaPartite.add(main);
		listaPartiteHash.put(idPartita++,main);
		main.setStato(Stato.DISPONIBILE);
		listaPartite_Clients.put(main.getIdPartita(), clients);
		listaClients_Partita.put(this.getClient(idClientCreatore),main);
	}
	
	private void cercaEAggiungiClientAPartita(int idClient, String comando)
	{
		String messaggio="";
		if(this.listaPartite.isEmpty())
		{
			this.creaNuovaPartita(comando,idClient);
			messaggio="Fatto! "+this.getClient(idClient).getNomeClient()+", ti ho aggiunto alla partita appena creata.";
			this.inviaMessaggioAClient(idClient, messaggio);
		}
		else 
		{
			for(Main main : listaPartite)
			{
				if(main.getNomeMappa().equals(comando) && main.getStato().equals(Stato.DISPONIBILE))
				{
					this.aggiungiClientAPartita(this.getClient(idClient),main.getIdPartita());
					messaggio=this.getClient(idClient).getNomeClient()+" ti ho aggiunto ad una partita.";
					this.inviaMessaggioAClient(idClient, messaggio);
					messaggio="Si è aggiunto "+this.getClient(idClient).getNomeClient()+" alla partita.";
					this.inviaMessaggioAClientsDiPartita(main.getIdPartita(), messaggio);
					return;
				}
			}
			this.creaNuovaPartita(comando, idClient);
			messaggio="Fatto! "+this.getClient(idClient).getNomeClient()+", ti ho aggiunto alla partita appena creata.";
			this.inviaMessaggioAClient(idClient, messaggio);
		}
	}
	
	public void riceviComando(String comando)
	{
		try
		{
			this.elaboraComando(comando);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private synchronized void elaboraComando(String comando) throws IOException
	{
		int idClientCheInviaComando=this.estraiId(comando);
		comando=this.rimuoviFirma(comando);
		if(this.listaClientsNonInPartite.contains(this.listaClientsHash.get(idClientCheInviaComando)))
		{
			this.cercaEAggiungiClientAPartita(idClientCheInviaComando, comando);
			this.listaClientsNonInPartite.remove(this.getClient(idClientCheInviaComando));
		}
		else
		{
			Main main=listaClients_Partita.get(this.getClient(idClientCheInviaComando));
			if(main.getStato().equals(Stato.FINITA))
			{
				this.inviaMessaggioAClient(idClientCheInviaComando, "La tua partita è terminata. Non puoi inserire altro.");
				return;
			}
			if(comando.equals("inizia partita"))
			{
				if(listaPartite_Clients.get(main.getIdPartita()).size()>=2 && (main.getStato().equals(Stato.PIENA) || main.getStato().equals(Stato.DISPONIBILE)))
				{
					main.inzializzaPartita();
					main.aggiungiGiocatori(this.listaPartite_Clients.get(main.getIdPartita()));
					inviaMessaggioAClientsDiPartita(main.getIdPartita(),"Partita iniziata.");
					for(Giocatore giocatore : main.getModelPartita().getGiocatori())
					{
						this.inviaMessaggioAClient(giocatore.getIdGiocatore(), "Sei "+giocatore.getPersonaggio().name());
					}
					this.inviaMessaggioAClient(main.getControllerPartita().giocatoreCorrente().getIdGiocatore(), "E' il tuo turno. Inserisci il tuo comando:");
					this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(), main.getControllerPartita().giocatoreCorrente().getIdGiocatore(), "E' il turno di "+main.getControllerPartita().giocatoreCorrente().getNomeUtente()+".");
				}
				else
				{
					this.inviaMessaggioAClient(idClientCheInviaComando, "Impossibile iniziare la partita.");
					return;
				}
			}
			else
			{
				if(main.getStato().equals(Stato.DISPONIBILE) || main.getStato().equals(Stato.PIENA))
				{
					this.inviaMessaggioAClient(idClientCheInviaComando, "Attenzione: la tua partita non è ancora cominciata.\nL'unico comando che accetto è 'inizia partita' per iniziare.");
					return;
				}
				if(this.listaClients_Partita.get(this.getClient(idClientCheInviaComando)).getControllerPartita().giocatoreCorrente().getIdGiocatore()!=idClientCheInviaComando && !(comando.startsWith("chat ")))
					this.inviaMessaggioAClient(idClientCheInviaComando, "Non è il tuo turno.");
				else
				{
					if(comando.startsWith("chat "))
					{
						comando=comando.substring(5);
						this.inviaMessaggioAClient(idClientCheInviaComando, "Hai inviato: "+comando);
						this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(), idClientCheInviaComando, this.getClient(idClientCheInviaComando).getNomeClient()+" scrive: "+comando);
						return;
					}
					ArrayList<String> messaggi;
					messaggi=main.avanzaPartita(comando);
					this.inviaMessaggioAClient(idClientCheInviaComando, messaggi.get(0));
					this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(), idClientCheInviaComando, messaggi.get(1));
					if(messaggi.get(0).equals("Hai passato."))
					{
						if(main.getModelPartita().getStato().equals(GameState.FINEGIOCO))
						{
							main.setStato(Stato.FINITA);
							return;
						}
						this.inviaMessaggioAClient(main.getControllerPartita().giocatoreCorrente().getIdGiocatore(),"E' il tuo turno. Inserisci il tuo comando:");
						this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(), main.getControllerPartita().giocatoreCorrente().getIdGiocatore(), "E' il turno di "+main.getControllerPartita().giocatoreCorrente().getNomeUtente()+".");
					}
				}
			}
		}
	}
	
	public ArrayList<ClientHandler> get(Main key)
	{
		return listaPartite_Clients.get(key);
	}

	private int estraiId(String comando)
	{
		int id;
		int lunghezzaComando=comando.length();
		id=((comando.charAt(lunghezzaComando-3))-48)*100;
		id+=((comando.charAt(lunghezzaComando-2))-48)*10;
		id+=(comando.charAt(lunghezzaComando-1))-48;
		return id;
	}
	
	private String rimuoviFirma(String comando)
	{
		int lunghezzaComando=comando.length();
		return comando.substring(0, (lunghezzaComando-3));
	}

	public void aggiungiNomePersonale(String comando)
	{
		int idClientCheInviaComando=this.estraiId(comando);
		comando=this.rimuoviFirma(comando);
		this.listaClientsHash.get(idClientCheInviaComando).setNomeClient(comando);
	}
	
	public boolean eraInPartita(int idClient)
	{
		if(this.listaClientsNonInPartite.contains((this.getClient(idClient))))
		{
			return false;
		}
		return true;
	}
	
	public void rimuoviClient(int idClient)
	{
		String umanoAlieno="";
		if(this.listaClients_Partita.get(this.getClient(idClient)).getStato().equals(Stato.DISPONIBILE) || this.listaClients_Partita.get(this.getClient(idClient)).getStato().equals(Stato.PIENA))
		{
			this.listaPartite_Clients.get(this.listaClients_Partita.get(this.getClient(idClient)).getIdPartita()).remove(this.getClient(idClient));
			this.listaClients_Partita.get(this.getClient(idClient)).setStato(Stato.DISPONIBILE);
			this.inviaMessaggioAClientsDiPartitaEsclusoClient(this.listaClients_Partita.get(this.getClient(idClient)).getIdPartita(),idClient,this.getClient(idClient).getNomeClient()+" si è disconnesso.");
			return;
		}
		else if(this.listaClients_Partita.get(this.getClient(idClient)).getStato().equals(Stato.INIZIATA))
		{
			for(Giocatore giocatore : this.listaClients_Partita.get(this.getClient(idClient)).getModelPartita().getGiocatori())
			{
				if(giocatore.getIdGiocatore()==idClient)
					umanoAlieno=giocatore.getPersonaggio().name();
			}
			Main main=this.listaClients_Partita.get(this.getClient(idClient));
			main.getControllerPartita().getLog().azzeraLog(main.getModelPartita().getNumeroGiocatoreCorrente(), main.getModelPartita().getNumeroTurno());
			main.eliminaClientDaPartita(idClient);
			this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(),idClient,this.getClient(idClient).getNomeClient()+" si è disconnesso, quindi eliminato dalla partita. Era un "+umanoAlieno+"");
			if(this.listaClients_Partita.get(this.getClient(idClient)).getStato().equals(Stato.FINITA))
				this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(),idClient,main.getControllerPartita().getLog().getLOG(main.getModelPartita().getNumeroGiocatoreCorrente(), main.getModelPartita().getNumeroTurno(), 5));
			else
			{
				this.inviaMessaggioAClient(main.getControllerPartita().giocatoreCorrente().getIdGiocatore(),"E' il tuo turno. Inserisci il tuo comando:\n");
				this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(), main.getControllerPartita().giocatoreCorrente().getIdGiocatore(), "E' il turno di "+main.getControllerPartita().giocatoreCorrente().getNomeUtente()+".");
			}
		}
		return;
	}
	
//	--------------------------------------------------------------------------------------------------
	
	private void inviaMessaggioBroadcast(String mex)
	{
		for(ClientHandler client : listaCompletaClients)
		{
			client.getClientHandlerOut().inviaMex(mex);
		}
	}
	
	private void inviaMessaggioAClientsDiPartitaEsclusoClient(int idPartita, int idClientEscluso, String mex)
	{
		for(ClientHandler client : listaPartite_Clients.get(idPartita))
		{
			if(client.getIdClient()==idClientEscluso)
				continue;
			client.getClientHandlerOut().inviaMex(mex);
		}
	}
	
	private void inviaMessaggioAClient(int idClient, String mex)
	{
		this.getClient(idClient).getClientHandlerOut().inviaMex(mex);
	}
	
	private void inviaMessaggioAClientsDiPartita(int idPartita, String mex)
	{
		for(ClientHandler client : listaPartite_Clients.get(idPartita))
		{
			client.getClientHandlerOut().inviaMex(mex);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
		result = prime
				* result
				+ ((listaClientsHash == null) ? 0 : listaClientsHash.hashCode());
		result = prime
				* result
				+ ((listaClientsNonInPartite == null) ? 0
						: listaClientsNonInPartite.hashCode());
		result = prime
				* result
				+ ((listaClients_Partita == null) ? 0 : listaClients_Partita
						.hashCode());
		result = prime
				* result
				+ ((listaCompletaClients == null) ? 0 : listaCompletaClients
						.hashCode());
		result = prime * result
				+ ((listaPartite == null) ? 0 : listaPartite.hashCode());
		result = prime
				* result
				+ ((listaPartiteHash == null) ? 0 : listaPartiteHash.hashCode());
		result = prime
				* result
				+ ((listaPartite_Clients == null) ? 0 : listaPartite_Clients
						.hashCode());
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
		Gestore other = (Gestore) obj;
		if (clients == null) {
			if (other.clients != null)
				return false;
		} else if (!clients.equals(other.clients))
			return false;
		if (listaClientsHash == null) {
			if (other.listaClientsHash != null)
				return false;
		} else if (!listaClientsHash.equals(other.listaClientsHash))
			return false;
		if (listaClientsNonInPartite == null) {
			if (other.listaClientsNonInPartite != null)
				return false;
		} else if (!listaClientsNonInPartite
				.equals(other.listaClientsNonInPartite))
			return false;
		if (listaClients_Partita == null) {
			if (other.listaClients_Partita != null)
				return false;
		} else if (!listaClients_Partita.equals(other.listaClients_Partita))
			return false;
		if (listaCompletaClients == null) {
			if (other.listaCompletaClients != null)
				return false;
		} else if (!listaCompletaClients.equals(other.listaCompletaClients))
			return false;
		if (listaPartite == null) {
			if (other.listaPartite != null)
				return false;
		} else if (!listaPartite.equals(other.listaPartite))
			return false;
		if (listaPartiteHash == null) {
			if (other.listaPartiteHash != null)
				return false;
		} else if (!listaPartiteHash.equals(other.listaPartiteHash))
			return false;
		if (listaPartite_Clients == null) {
			if (other.listaPartite_Clients != null)
				return false;
		} else if (!listaPartite_Clients.equals(other.listaPartite_Clients))
			return false;
		return true;
	}
}
