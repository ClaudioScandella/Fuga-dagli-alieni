package it.polimi.ingsw.cg_26.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private static final int PORT=29999;
	private int idClient=0;
	private static Gestore gestore=new Gestore();
	
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
