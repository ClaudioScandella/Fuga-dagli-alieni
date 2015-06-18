package it.polimi.ingsw.cg_26.Socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientReading implements Runnable
{
	private Socket socket;
	private int id;
	
	public ClientReading(Socket socket, int id)
	{
		this.socket=socket;
		this.id=id;
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
			System.out.println("errore 3");
		}
		while(true)
		{
			if(socketIn.hasNextLine())
			{
				comando=socketIn.nextLine();
				if(comando.equals("esci"))
					break;
				System.out.println(comando);
			}
		}
		socketIn.close();

	}

}
