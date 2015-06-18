package it.polimi.ingsw.cg_26.Socket;

import it.polimi.ingsw.cg_26.main.Main;
import it.polimi.ingsw.cg_26.main.Main.Stato;

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
//	private static String nomeMappa;
	
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
	
	public void aggiungiClientAPartita(ClientHandler client, int idPartita)
	{
		clients=this.listaPartite_Clients.get(idPartita);
		clients.add(client);
//		this.listaPartite_Clients.put(this.getPartita(idPartita), clients);
		this.listaPartite_Clients.put(idPartita, clients);
		this.listaClients_Partita.put(client,this.getPartita(idPartita));
	}
	
	public void creaNuovaPartita(String nomeMappa, int idClientCreatore)
	{
		clients=new ArrayList<>();
		clients.clear();
		clients.add(this.getClient(idClientCreatore));
		Main main=new Main(idPartita,nomeMappa);
		listaPartite.add(main);
		listaPartiteHash.put(idPartita++,main);
		main.setStato(Stato.DISPONIBILE);
//		listaPartite_Clients.put(main, clients);
		listaPartite_Clients.put(main.getIdPartita(), clients);
		listaClients_Partita.put(this.getClient(idClientCreatore),main);
	}
	
	private void cercaEAggiungiClientAPartita(int idClient, String comando)
	{
		String messaggio="";
		//controllo se esistono partite con quella mappa:
		//se s�, allora lo aggiungo, altrimenti creo la partita e lo aggiungo in attesa di altri clients
		if(this.listaPartite.isEmpty())
		{
			//creo nuova partita
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
					messaggio="Si � aggiunto "+this.getClient(idClient).getNomeClient()+" alla partita.";
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
		
//		this.inviaMessaggioBroadcast(comando);
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
			if(comando.equals("inizia partita"))
			{
				//inizia la partita sse � possibile
//				int numeroGiocatoriInPartita=listaPartite_Clients.get(listaClients_Partita.get(this.getClient(idClientCheInviaComando))).size();
				if(listaPartite_Clients.get(main.getIdPartita()).size()>=2 && (main.getStato().equals(Stato.PIENA) || main.getStato().equals(Stato.DISPONIBILE)))
				{
					main.inzializzaPartita();
					main.aggiungiGiocatori(this.listaPartite_Clients.get(main.getIdPartita()));
					inviaMessaggioAClientsDiPartita(main.getIdPartita(),"Partita iniziata.");
					this.inviaMessaggioAClient(main.getControllerPartita().giocatoreCorrente().getIdGiocatore(), "E' il tuo turno. Inserisci il tuo comando:");
					this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(), main.getControllerPartita().giocatoreCorrente().getIdGiocatore(), "E' il turno di "+main.getControllerPartita().giocatoreCorrente().getNomeUtente()+".");
//					main.iniziaPartita();
				}
			}
			else
			{
				//invio comando a controller partita
				//prima faccio controllo se � turno del giocatore del client che ha inviato il comando? oppure controllo dopo
				if(this.listaClients_Partita.get(this.getClient(idClientCheInviaComando)).getControllerPartita().giocatoreCorrente().getIdGiocatore()!=idClientCheInviaComando)
					this.inviaMessaggioAClient(idClientCheInviaComando, "Non � il tuo turno.");
				else
				{
					ArrayList<String> messaggi;
					messaggi=main.avanzaPartita(comando);
					this.inviaMessaggioAClient(idClientCheInviaComando, messaggi.get(0));
					this.inviaMessaggioAClientsDiPartitaEsclusoClient(main.getIdPartita(), idClientCheInviaComando, messaggi.get(1));
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
	
//	--------------------------------------------------------------------------------------------------
	
	public void inviaMessaggioBroadcast(String mex)
	{
		for(ClientHandler client : listaCompletaClients)
		{
			client.getClientHandlerOut().inviaMex(mex);
		}
	}
	
	public void inviaMessaggioAClientsDiPartitaEsclusoClient(int idPartita, int idClientEscluso, String mex)
	{
		for(ClientHandler client : listaPartite_Clients.get(idPartita))
		{
			if(client.getIdClient()==idClientEscluso)
				continue;
			client.getClientHandlerOut().inviaMex(mex);
		}
	}
	
	public void inviaMessaggioAClient(int idClient, String mex)
	{
		this.getClient(idClient).getClientHandlerOut().inviaMex(mex);;
	}
	
	public void inviaMessaggioAClientsDiPartita(int idPartita, String mex)
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
