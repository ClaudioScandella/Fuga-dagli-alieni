package it.polimi.ingsw.cg_26.model.mappe;

import java.util.*;
import java.io.*;

public class MappaGalilei
{
	
	private static HashMap<Settore, ArrayList<Settore>> mappaGalilei=new HashMap<>();
	//int nMaxSettoriAdiacenti=6;
	ArrayList<Settore> listSettore=new ArrayList<>();//verra' riempito con tutti i possibili settori
	ArrayList<Settore> listaSettoriAdiacenti;//usato per mettere in mappaGalilei la lista dei settori adiacenti ad una chiave
	static int i=0;
	String coordinata="";
	int c=0;
	
	public MappaGalilei()
	{
		//creo oggetto reader per leggere da file
		FileReader reader=null;
		try {
			reader=new FileReader("C:\\Users\\Claudio\\Desktop\\PROGETTO SOFTWARE\\File Mappe\\galilei.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//generatore di settori da A01 a W14 e li aggiunge a listSettore
		for(char coordinataNumerica1='0';coordinataNumerica1!='2';coordinataNumerica1++)
			for(char coordinataNumerica2='0';coordinataNumerica2!=':';coordinataNumerica2++)
			{
				if(coordinataNumerica1=='0'&&coordinataNumerica2=='0') continue;
				if(coordinataNumerica1=='1'&&coordinataNumerica2=='5') break;
				for(char coordinataAlfabetica='A';coordinataAlfabetica!='X';coordinataAlfabetica++)
				{
					
					coordinata=""+coordinataNumerica1+coordinataNumerica2;
					String nomeSettore=""+coordinataAlfabetica+coordinataNumerica1+coordinataNumerica2;
					Settore settoreTemporaneo= null;
					
					try
					{
						c=reader.read();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					switch (c)
					{
						case '0': settoreTemporaneo=new SettoreVuoto(nomeSettore, "Settore vuoto"); break;
						case '1': settoreTemporaneo=new SettorePericoloso(nomeSettore); break;
						case '2': settoreTemporaneo=new SettoreSicuro(nomeSettore); break;
						case '3': settoreTemporaneo=new SettorePartenzaAlieni(nomeSettore, "Partenza Alieni"); break;
						case '4': settoreTemporaneo=new SettorePartenzaUmani(nomeSettore, "Partenza Umani"); break;
						case '5': settoreTemporaneo=new SettoreScialuppa1(nomeSettore, "Scialuppa 1"); break;
						case '6': settoreTemporaneo=new SettoreScialuppa2(nomeSettore, "Scialuppa 2"); break;
						case '7': settoreTemporaneo=new SettoreScialuppa3(nomeSettore, "Scialuppa 3"); break;
						case '8': settoreTemporaneo=new SettoreScialuppa4(nomeSettore, "Scialuppa 4"); break;
					}
					listSettore.add(settoreTemporaneo);
				}
			}
//		inserisce ogni settore in HashMap con rispettiva lista dei settori adiacenti
		for(i=0;i<listSettore.size();i++)
		{
			listaSettoriAdiacenti=new ArrayList<>();
			if((i%23)!=22) listaSettoriAdiacenti.add(listSettore.get(i+1));
			if((i%23)!=0) listaSettoriAdiacenti.add(listSettore.get(i-1));
			if(i<=((listSettore.size())-24)) listaSettoriAdiacenti.add(listSettore.get(i+23));
			if((i-23)>=0) listaSettoriAdiacenti.add(listSettore.get(i-23));
			if((i%23)%2==0) //se la colonna e' pari
			{
				if((i%23)!=0&&i>22) listaSettoriAdiacenti.add(listSettore.get(i-24));
				if((i%23)!=22&&i>22) listaSettoriAdiacenti.add(listSettore.get(i-22));
			}
			else //se la colonna e' dispari
			{
				if((i%23)!=22&&i<(listSettore.size()-22)) listaSettoriAdiacenti.add(listSettore.get(i+24));
				if((i%23)!=0&&i<(listSettore.size()-22)) listaSettoriAdiacenti.add(listSettore.get(i+22));
			}
		mappaGalilei.put(listSettore.get(i), listaSettoriAdiacenti);
		}
	
		System.out.println("FINE");
//		System.out.println(""+mappaGalilei.get(listSettore.get(0)).size());
//		System.out.println(""+listSettore.get(0).getNome());
		
		/*
		for(int k=250;k<322;k++)
		{
		listaSettoriAdiacenti=new ArrayList<>();
		listaSettoriAdiacenti=mappaGalilei.get(listSettore.get(k));
		System.out.println(listSettore.get(k).getClass());
		for(i=0;i<listaSettoriAdiacenti.size();i++)
			System.out.println(listaSettoriAdiacenti.get(i).getNome());
		System.out.println("");
		}
		*/

		try
		{
			reader.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//metodo per tornare la lista dei settori adiacenti ad un dato settore, nella classe Settore
	public ArrayList<Settore> settoriAdiacenti(String nome)
	{
		int indice=convertitore(nome);
		return mappaGalilei.get(listSettore.get(indice));
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listSettore == null) ? 0 : listSettore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappaGalilei other = (MappaGalilei) obj;
		if (listSettore == null) {
			if (other.listSettore != null)
				return false;
		} else if (!listSettore.equals(other.listSettore))
			return false;
		return true;
	}
	
	public static int convertitore(String nome)
	{
		int lettera=nome.charAt(0)-65;
		int numero=((nome.charAt(1)-48)*10+nome.charAt(2)-48)-1;
		int indiceSettore=lettera+(numero*23);
		return indiceSettore;
	}
	
	//passo la portata (1 umani e 2/3 alieni) il settore destinazione e la posizione del giocatore, ma solo adesso xk non ho il getPosizione
//	public boolean mossaValida(String destinazione, String corrente, int portata)
//	{
//		int indiceDestinazione=convertitore(destinazione);
//		int indiceCorrente=convertitore(corrente);
//		
//		List<Settore> settoriRaggiungibili=new ArrayList<>();
//		
//		settoriRaggiungibili=mappaGalilei.get(listSettore.get(indiceCorrente));
//		while(portata>0)
//		{
//			for(int i=0;i<settoriRaggiungibili.size();i++)
//			settoriRaggiungibili.add(mappaGalilei.get(listSettore.get(i)).get(i));
//			portata--;
//		}
//		
//		
//		
//		
//		
//		
//		
//		
//		return false;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
