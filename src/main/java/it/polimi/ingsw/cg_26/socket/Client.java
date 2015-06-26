package it.polimi.ingsw.cg_26.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
	private final static int PORT = 29999;
	private int id;

	private void startClient() throws IOException, InterruptedException
	{
		Scanner socketIn = null;
		Socket socket=new Socket("127.0.0.1",PORT);
		System.out.println("Connessione stabilita.");
		try
		{
			socketIn = new Scanner(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("errore 5");
		}
		this.setID(socketIn.nextInt());
		Thread clientReading=new Thread(new ClientReading(socket,this.id));
		Thread clientWriting=new Thread(new ClientWriting(socket,this.id));
		clientReading.start();
		clientWriting.start();
		System.out.println("Il mio id è "+this.id);
	}

	public void setID(int id)
	{
		this.id=id;
	}
	
	public static void main(String[] args)
	{
		Client client = new Client();
		
		try
		{
			client.startClient();
		}
		catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
