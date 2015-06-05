package model.mappa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Mappa
{
	private HashMap<Settore, ArrayList<Settore>> mappa=new HashMap<>();
	private ArrayList<Settore> listaSettoriTotali=new ArrayList<>();//verra' riempito con tutti i possibili settori
	private ArrayList<Settore> listaSettoriAdiacenti;//usato per mettere in mappaGalilei la lista dei settori adiacenti ad una chiave
	private String nomeMappa;

	public Mappa(String nomeMappa)
	{
		this.nomeMappa = nomeMappa;
		
//		  /fuga_dagli_alieni/src/main/java/it/polimi/ingsw/cg_26/model/mappe/galilei.txt
//		String percorsoMappa="fuga_dagli_alieni"+File.separatorChar+"src"+File.separatorChar+"main"+File.separatorChar+"java"+File.separatorChar+"it"+File.separatorChar+"polimi"+File.separatorChar+"ingsw"+File.separatorChar+"cg_26"+File.separatorChar+"model"+File.separatorChar+"mappe"+File.separatorChar+nomeMappa+".txt";
//		String percorsoMappa="/fuga_dagli_alieni/src/main/java/it/polimi/ingsw/cg_26/model/mappe/"+nomeMappa+".txt";
//		String percorsoMappa="C:\\Users\\Claudio\\Desktop\\galilei.txt";
		String percorsoMappa="src"+File.separatorChar+"fileMappe"+File.separatorChar+nomeMappa+".txt";
		
		//creo oggetto reader per leggere da file
		FileReader reader=null;
		try
		{
			reader=new FileReader(percorsoMappa);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int carattereLetto=0;
		String coordinata="";
		
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
						carattereLetto=reader.read();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					
					switch (carattereLetto)
					{
						case '0': settoreTemporaneo=new SettoreVuoto(nomeSettore, "Settore vuoto"); break;
						case '1': settoreTemporaneo=new SettorePericoloso(nomeSettore, "Settore pericoloso"); break;
						case '2': settoreTemporaneo=new SettoreSicuro(nomeSettore, "Settore sicuro"); break;
						case '3': settoreTemporaneo=new SettorePartenzaAlieni(nomeSettore, "Partenza Alieni"); break;
						case '4': settoreTemporaneo=new SettorePartenzaUmani(nomeSettore, "Partenza Umani"); break;
						case '5': settoreTemporaneo=new SettoreScialuppa1(nomeSettore, "Scialuppa 1"); break;
						case '6': settoreTemporaneo=new SettoreScialuppa2(nomeSettore, "Scialuppa 2"); break;
						case '7': settoreTemporaneo=new SettoreScialuppa3(nomeSettore, "Scialuppa 3"); break;
						case '8': settoreTemporaneo=new SettoreScialuppa4(nomeSettore, "Scialuppa 4"); break;
					}
					listaSettoriTotali.add(settoreTemporaneo);
				}
			}
//		inserisce ogni settore in HashMap con rispettiva lista dei settori adiacenti
		for(int indice=0;indice<listaSettoriTotali.size();indice++)
		{
			listaSettoriAdiacenti=new ArrayList<>();
			if((indice%23)!=22) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice+1));
			if((indice%23)!=0) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice-1));
			if(indice<=((listaSettoriTotali.size())-24)) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice+23));
			if((indice-23)>=0) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice-23));
			if((indice%23)%2==0) //se la colonna e' pari
			{
				if((indice%23)!=0&&indice>22) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice-24));
				if((indice%23)!=22&&indice>22) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice-22));
			}
			else //se la colonna e' dispari
			{
				if((indice%23)!=22&&indice<(listaSettoriTotali.size()-22)) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice+24));
				if((indice%23)!=0&&indice<(listaSettoriTotali.size()-22)) listaSettoriAdiacenti.add(listaSettoriTotali.get(indice+22));
			}
			mappa.put(listaSettoriTotali.get(indice), listaSettoriAdiacenti);
		}

		try
		{
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public HashMap<Settore, ArrayList<Settore>> getMappa()
	{
		return this.mappa;
	}

	public String getNomeMappa() {
		return nomeMappa;
	}

	public ArrayList<Settore> getListaSettoriTotali() {
		return listaSettoriTotali;
	}
}
























