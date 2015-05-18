package it.polimi.ingsw.cg_26.model.mappe;

import java.util.*;

import java.io.*;

public class MappaGalilei
{
	
	private static HashMap<Settore, ArrayList<Settore>> mappaGalilei=new HashMap<>();
	//int nMaxSettoriAdiacenti=6;
	ArrayList<Settore> listSettore=new ArrayList<>();//verrà riempito con tutti i possibili settori
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
					//System.out.println(nomeSettore);
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
						case '0': settoreTemporaneo=new SettoreVuoto(nomeSettore); break;
						case '1': settoreTemporaneo=new SettorePericoloso(nomeSettore); break;
						case '2': settoreTemporaneo=new SettoreSicuro(nomeSettore); break;
						case '3': settoreTemporaneo=new SettorePartenzaAlieni(nomeSettore); break;
						case '4': settoreTemporaneo=new SettorePartenzaUmani(nomeSettore); break;
						case '5': settoreTemporaneo=new SettoreScialuppa1(nomeSettore); break;
						case '6': settoreTemporaneo=new SettoreScialuppa2(nomeSettore); break;
						case '7': settoreTemporaneo=new SettoreScialuppa3(nomeSettore); break;
						case '8': settoreTemporaneo=new SettoreScialuppa4(nomeSettore); break;
					}
					listSettore.add(settoreTemporaneo);
				}
			}
//		inserisce ogni settore in HashMap con rispettiva lista dei settori adiacenti
		for(i=0;i<listSettore.size();i++)
		{
			listaSettoriAdiacenti=new ArrayList<>();
			if((i%23)!=22) {listaSettoriAdiacenti.add(listSettore.get(i+1));System.out.println("a");}//settore a dx
			if((i%23)!=0) {listaSettoriAdiacenti.add(listSettore.get(i-1));System.out.println("b");}//settore a sx
			if(i<=((listSettore.size())-24)) {listaSettoriAdiacenti.add(listSettore.get(i+23));System.out.println("c");}//settore in giu
			if((i-23)>=0) {listaSettoriAdiacenti.add(listSettore.get(i-23));System.out.println("d");}//settore in su
			if((i%23)%2==0) //se la colonna è pari
			{
				System.out.println("e");
				if((i%23)!=0&&i>22) {listaSettoriAdiacenti.add(listSettore.get(i-24));System.out.println("f");}//settore in alto a sx
				if((i%23)!=22&&i>22) {listaSettoriAdiacenti.add(listSettore.get(i-22));System.out.println("g");}//settore in alto a dx
			}
			else //se la colonna è dispari
			{
				System.out.println("h");
				if((i%23)!=22&&i<(listSettore.size()-22)) {listaSettoriAdiacenti.add(listSettore.get(i+24));System.out.println("i");}//settore in basso a dx
				if((i%23)!=0&&i<(listSettore.size()-22)) {listaSettoriAdiacenti.add(listSettore.get(i+22));System.out.println("j");}//settore in basso a sx
			}
		mappaGalilei.put(listSettore.get(i), listaSettoriAdiacenti);
		}
	
		System.out.println("FINE");
		
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
	public ArrayList<Settore> settoriAdiacenti(int index)
	{
		return mappaGalilei.get(index);
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
	
/*	//controlla se la mossa è valida
//	public boolean mossaValida(String nomeSettore)
//	{
////		traduci(nomeSettore);
//		
//		return false;
//	}
	
	private static int rateo=2;
	public boolean mossaValida(int index, String nome)
	{
		
		//visto che si passa il nome del settore qui ci vuole il traduttore nome-->indice
		int posizioneCorrente=51;
		listaSettoriAdiacenti=mappaGalilei.get(listSettore.get(posizioneCorrente));
//		System.out.println(listSettore.get(index).getNome());
//		System.out.println(listSettore.get(51).getNome());
		
		
		for(int k=0;k<listaSettoriAdiacenti.size();k++)
		{
//			System.out.println(listaSettoriAdiacenti.get(k).getNome());
			if(listaSettoriAdiacenti.get(k).getNome().equals(nome)) return true;
		}
		
		return false;
	}
	
	private boolean controllaVicini(int settoreUno, int settoreDue)
	{
		if(rateo==0) return false;
		
		
		
		if(if(listaSettoriAdiacenti.get().getNome().equals())
		
		return false;
	}
*/
}
