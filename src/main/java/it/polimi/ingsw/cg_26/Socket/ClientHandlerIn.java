package it.polimi.ingsw.cg_26.Socket;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
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
				this.gestore.aggiungiNomePersonale(comando);
				break;
			}
		}
		while(true)
		{
			try
			{
				comando=socketIn.nextLine().toLowerCase();
				if(comando.equals("exit"))
					break;
				this.gestore.riceviComando(comando);
			}
			catch (IllegalStateException e)
			{
				System.out.println("errore AAA");
			}
			catch (NoSuchElementException e)
			{
				System.out.println("errore AAB");
				if(gestore.eraInPartita(this.idClient))
				{
					System.out.println("è un casino");
					this.gestore.rimuoviClient(this.idClient);
				}
				break;
			}
		}
		socketIn.close();
	}
}

