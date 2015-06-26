package it.polimi.ingsw.cg_26.Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriting implements Runnable
{
	private Socket socket;
	private int id;
	private String comando;
	private PrintWriter socketOut=null;

	public ClientWriting(Socket socket, int id)
	{
		this.socket=socket;
		this.id=id;
	}

	@Override
	public void run()
	{
		try
		{
			socketOut = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("errore 4");
		}
		Scanner scanner=new Scanner(System.in);

		System.out.println("Inserisci il tuo nome:");
		while(true)
		{
			if(scanner.hasNextLine())
			{
				comando=scanner.nextLine();
				if(comando.equals(""))
				{
					System.out.println("Nome non valido. Inserisci un nome diverso.\n");
					continue;
				}
				comando=comando+this.aggiungiFirma();
				socketOut.println(comando);
				socketOut.flush();
				break;
			}
		}
		System.out.println("Inserisci il nome della mappa:");
		while(true)
		{
			if(scanner.hasNextLine())
			{
				comando=scanner.nextLine();
				comando=comando.toLowerCase();
				if(comando.equals("galilei") || comando.equals("fermi") || comando.equals("galvani"))
				{
					comando=comando+this.aggiungiFirma();
					socketOut.println(comando);
					socketOut.flush();
					break;
				}
				System.out.println("Mappa inesistente. Inserisci di nuovo:");
			}
		}

		while(true)
		{
			if(scanner.hasNextLine())
			{
				comando=scanner.nextLine();
				if(comando!=null)
				{
					if(comando.equals("exit"))
						break;
					comando=comando+this.aggiungiFirma();
					socketOut.println(comando.toLowerCase());
					socketOut.flush();
				}
			}
		}
		scanner.close();
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private String aggiungiFirma()
	{
		String firma="";
		if(this.id<10)
		{
			firma="00"+this.id;
		}
		else if(this.id>=10 && this.id<100)
		{
			firma="0"+this.id;
		}
		else if(this.id>=100)
		{
			firma=""+this.id;
		}
		return firma;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientWriting other = (ClientWriting) obj;
		if (comando == null) {
			if (other.comando != null)
				return false;
		} else if (!comando.equals(other.comando))
			return false;
		return true;
	}
}
