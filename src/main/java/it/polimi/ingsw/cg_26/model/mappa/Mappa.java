package it.polimi.ingsw.cg_26.model.mappa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contiene la classe Mappa, che permette di creare la mappa leggendo in un file
 * di testo il tipo di settore da assegnare ad ogni coordinata.
 * 
 * @author Claudio e Patrizia
 *
 */
public class Mappa
{
	private HashMap<Settore, ArrayList<Settore>> mappa=new HashMap<>();
	private ArrayList<Settore> listaSettoriTotali=new ArrayList<>();//verra' riempito con tutti i possibili settori
	private ArrayList<Settore> listaSettoriAdiacenti;//usato per mettere in mappaGalilei la lista dei settori adiacenti ad una chiave
	private String nomeMappa;

	/** 
	 * Costruisce una mappa del tipo passato in ingresso (galilei, galvani fermi) 
	 * prendendo da un file di testo il tipo di settore da assegnare ad ogni 
	 * coordinata.
	 * Ogni coordinata (nomeSettore) è costituita da un carattere alfabetico e due caratteri 
	 * numerici
	 * Ogni carattere letto dal file di testo può essere:
	 * - '0': identifica un settore vuoto
	 * - '1': identifica un settore pericoloso
	 * - '2': identifica un settore sicuro
	 * - '3': identifica un settore partenza alieni
	 * - '4': identifica un settore partenza umani
	 * - '5': identifica un settore scialuppa1
	 * - '6': identifica un settore scialuppa2
	 * - '7': identifica un settore scialuppa3
	 * - '8': identifica un settore scialuppa4
	 * 
	 * Assegna poi ad ogni settore una lista di settori vicini.
	 * 
	 * @param nomeMappa è la stringa contenente il nome del tipo di mappa che 
	 * deve essere generata
	 */
	public Mappa(String nomeMappa)
	{
		this.nomeMappa = nomeMappa;
//		src/main/java/it/polimi/ingsw/cg_26/
		String percorsoMappa="src"+File.separatorChar+"main"+File.separatorChar+"java"+File.separatorChar+"it"+File.separatorChar+"polimi"+File.separatorChar+"ingsw"+File.separatorChar+"cg_26"+File.separatorChar+"fileMappe"+File.separatorChar+nomeMappa+".txt";
		FileReader reader=null;
		try
		{
			reader=new FileReader(percorsoMappa);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int carattereLetto=0;
		for(char coordinataNumerica1='0';coordinataNumerica1!='2';coordinataNumerica1++)
			for(char coordinataNumerica2='0';coordinataNumerica2!=':';coordinataNumerica2++)
			{
				if(coordinataNumerica1=='0'&&coordinataNumerica2=='0') continue;
				if(coordinataNumerica1=='1'&&coordinataNumerica2=='5') break;
				for(char coordinataAlfabetica='A';coordinataAlfabetica!='X';coordinataAlfabetica++)
				{
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
	
	//GETTER
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
