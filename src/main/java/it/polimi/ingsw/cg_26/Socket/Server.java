package it.polimi.ingsw.cg_26.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	//IDEA: NON USO ARRAY LIST PER THREAD MA USO UN ARRAY DOVE NELLA POSIZIONE i CI SARA' IL THREAD CON IL CLIENT
	//CON ID=i. OPPURE USARE UNA HASH DOVE AD OGNI VALORE i MI RITORNERA' IL THREAD CORRISPONDENTE (CHIAVE-->VALORE)
	private static final int PORT=29999;
	private int idClient=0;
	private static Gestore gestore=new Gestore();
//	private ArrayList<ClientHandler> listaThread=new ArrayList<ClientHandler>();
//	private Hashtable<Integer, ClientHandler> listaClient;
	
	private void startServer() throws IOException
	{
		ServerSocket serverSocket=new ServerSocket(PORT);
		Socket socket;
		System.out.println("Server pronto.");
		while(true)
		{
			socket=serverSocket.accept();
			System.out.println("Si � aggiunto Client con id "+this.idClient);
			
			gestore.aggiungiClient(new ClientHandler(socket,idClient++,gestore));
//			Thread clientHandlerIn=new Thread(new ClientHandlerIn(socket,idClient));
//			Thread clientHandlerOut=new Thread(new ClientHandlerOut(socket,idClient++));
//			clientHandlerIn.start();
//			clientHandlerOut.start();
//			System.out.println("Threads partiti.");
//			gestore.aggiungiClient();
		}
	}

	public static void main(String[] args)
	{		
		Server server=new Server();
		try
		{
			server.startServer();
		}
		catch(IOException e)
		{
			System.out.println("Server non disponibile.");
		}
	}
}
