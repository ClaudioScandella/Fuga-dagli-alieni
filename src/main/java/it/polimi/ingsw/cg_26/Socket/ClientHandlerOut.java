package it.polimi.ingsw.cg_26.Socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandlerOut extends Thread
{
	private Socket socket;
	private int idClient;
	private PrintWriter socketOut=null;

	public ClientHandlerOut(Socket socket, int idClient)
	{
		this.socket=socket;
		this.idClient=idClient;
	}
	

	@Override
	public void run()
	{
		String comando;
		
		try
		{
			socketOut = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("errore 1");
		}
//		try
//		{
//			Thread.sleep(3000);
//			System.out.println("fatto");
//		}
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
		socketOut.println(this.idClient);
		socketOut.flush();
		System.out.println("inviato id: "+idClient);
		Scanner scanner=new Scanner(System.in);
		while(true)
		{
			if(scanner.hasNextLine())
			{
				comando=scanner.nextLine();
				if(comando!=null)
				{
					socketOut.println(comando);
					socketOut.flush();
				}
			}
		}
	}
	
	public void inviaMex(String mex)
	{
		socketOut.println(mex);
		socketOut.flush();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
