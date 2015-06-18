package it.polimi.ingsw.cg_26.Socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandlerIn extends Thread
{
	private Socket socket;
	private int idClient;
	private Gestore gestore;

	public ClientHandlerIn(Socket socket, int idClient, Gestore gestore)
	{
		this.socket=socket;
		this.idClient=idClient;
		this.gestore=gestore;
	}

	@Override
	public void run()
	{
		String comando;
		Scanner socketIn = null;
		try
		{
			socketIn = new Scanner(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("errore 2");
		}
		while(true)
		{
			if(socketIn.hasNextLine())
			{
				comando=socketIn.nextLine();
//				System.out.println("Sono il server e ho ricevuto: "+comando);
				this.gestore.aggiungiNomePersonale(comando);
				break;
			}
		}
		while(true)
		{
			if(socketIn.hasNextLine())
			{
				comando=socketIn.nextLine().toLowerCase();
				if(comando.equals(""))
					break;
//				System.out.println("Sono il server e ho ricevuto: "+comando);
				this.gestore.riceviComando(comando);
			}
		}
		socketIn.close();



	}





}

