package it.polimi.ingsw.cg_26.Socket;

import java.net.Socket;

public class ClientHandler
{
	private ClientHandlerIn clientHandlerIn;
	private ClientHandlerOut clientHandlerOut;
	private int idClient;
	private Gestore gestore;
	private String nomeClient;

	public ClientHandler(Socket socket, int idClient, Gestore gestore)
	{
		this.gestore=gestore;
		this.idClient=idClient;
		clientHandlerIn=new ClientHandlerIn(socket,idClient,gestore);
		clientHandlerOut=new ClientHandlerOut(socket,idClient++);
		clientHandlerIn.start();
		clientHandlerOut.start();
		System.out.println("Threads partiti.");
	}

	public String getNomeClient()
	{
		return nomeClient;
	}

	public void setNomeClient(String nomeClient)
	{
		this.nomeClient = nomeClient;
	}
	
	public int getIdClient()
	{
		return this.idClient;
	}

	public ClientHandlerIn getClientHandlerIn()
	{
		return this.clientHandlerIn;
	}

	public ClientHandlerOut getClientHandlerOut()
	{
		return this.clientHandlerOut;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
