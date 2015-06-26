package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.mappa.Mappa;
import it.polimi.ingsw.cg_26.model.mappa.Settore;

import java.util.ArrayList;

/**
 * 
 * Contiene la classe ControllerMappa, che contiene i metodi utilizzati
 * per gestire la mappa durante lo svolgimento della partita.
 * 
 * @author Claudio e Patrizia
 *
 */
public class ControllerMappa
{
	private Mappa mappa;
	
//COSTRUTTORE
	/**
	 * Costruisce un ControllerMappa attribuiendo alla variabile mappa
	 * il riferimento all'oggetto di tipo Mappa passato in ingresso.
	 * 
	 * @param mappa
	 */
	public ControllerMappa(Mappa mappa)
	{
		this.mappa=mappa;
	}
	
//	---GETTER-----------------------------------------------------------------------------------------------
	/**
	 * Questo metodo controlla tutti i settori della mappa (presenti nella listaSettoriTotali della
	 * classe Mappa)e ritorna la stringa contenente le coordinate del settore di partenza degli alieni.
	 * Se nella mappa non esiste un settore partenza alieni, il metodo ritorna null.
	 * 
	 * @return coordinate del settore partenza alieni
	 */
	public String getPartenzaAlieni()
	{
		for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
		{
			if(mappa.getListaSettoriTotali().get(i).getNomeSupplementare()=="Partenza Alieni")
				return mappa.getListaSettoriTotali().get(i).getNome();
		}
		return null;
	}

	/**
	 * Questo metodo controlla tutti i settori della mappa (presenti nella listaSettoriTotali della
	 * classe Mappa) e ritorna la stringa contenente le coordinate del settore di partende
	 * degli umani.
	 * Se nella mappa non esiste un settore partenza umani, il metodo ritorna null.
	 * 
	 * @return coordinate del settore partenza umani
	 */
	public String getPartenzaUmani()
	{
		for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
		{
			if(mappa.getListaSettoriTotali().get(i).getNomeSupplementare()=="Partenza Umani")
				return mappa.getListaSettoriTotali().get(i).getNome();
		}
		return null;
	}
	
	/**
	 * Controlla tutti i settori della mappa (presenti nella listaSettoriTotali della classe
	 * Mappa) e salva nell'arraylist listaScialuppe tutti i settori di tipo scialuppa
	 * (scialuppa1, scialuppa2, scialuppa3, scialuppa4).
	 * Ritorna l'arraylist listaScialuppe, contenente tutti i settori scialuppa presenti 
	 * sulla mappa.
	 * 
	 * @return lista dei settori scialuppa
	 */
	public ArrayList<Settore> getSettoriScialuppa()
	{
		ArrayList<Settore> listaScialuppe=new ArrayList<Settore>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Scialuppa 1" ||
				settore.getNomeSupplementare()=="Scialuppa 2" ||
				settore.getNomeSupplementare()=="Scialuppa 3" ||
				settore.getNomeSupplementare()=="Scialuppa 4")
				listaScialuppe.add(settore);
		}
		return listaScialuppe;
	}
	
	/**
	 * Controlla tutti i settori della mappa (presenti nella listaSettoriTotali della classe
	 * Mappa) e salva nell'arraylist listaSettoriVuoti tutti i settori vuoti presenti 
	 * sulla mappa.
	 * Ritorna l'arraylist listaSettoriVuoti. 
	 * 
	 * @return lista dei settori vuoti
	 */
	public ArrayList<Settore> getSettoriVuoti()
	{
		ArrayList<Settore> listaSettoriVuoti=new ArrayList<>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore vuoto")
				listaSettoriVuoti.add(settore);
		}
		return listaSettoriVuoti;
	}
	
	public Mappa getMappa()
	{
		return this.mappa;
	}
	/**
	 * Controlla tutti i settori della mappa (presenti nella listaSettoriTotali della classe
	 * Mappa) e salva nell'arraylist listaSettoriSicuti tutti i settori sicuri
	 * Ritorna l'arraylist listaSettoriSicuri, contenente tutti i settori sicuri presenti 
	 * sulla mappa.
	 * 
	 * @return lista dei settori sicuri
	 */
	public ArrayList<Settore> getSettoriSicuri()
	{
		ArrayList<Settore> listaSettoriSicuri=new ArrayList<>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore sicuro")
				listaSettoriSicuri.add(settore);
		}
		return listaSettoriSicuri;
	}
	
	/**
	 * Controlla tutti i settori della mappa (presenti nella listaSettoriTotali della classe
	 * Mappa) e salva nell'arraylist listaSettoriPericolori tutti i settori pericolosi.
	 * Ritorna l'arraylist listaSettoriPericolosi, contenente tutti i settori pericolosi presenti 
	 * sulla mappa.
	 * 
	 * @return lista dei settori pericolosi
	 */
	public ArrayList<Settore> getSettoriPericolosi()
	{
		ArrayList<Settore> listaSettoriPericolosi=new ArrayList<>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore pericoloso")
				listaSettoriPericolosi.add(settore);
		}
		return listaSettoriPericolosi;
	}
	
//	--------------------------------------------------------------------------------------------------
	/**
	 * Conta, tra tutti i settori presenti sulla mappa, il numero di settori bloccati.
	 * Poiché solo i settoriScialuppa vengono bloccati (quando necessario), il metodo
	 * conta il numero di settori scialuppa bloccati.
	 * 
	 * @return numero di scialuppe bloccate
	 */
	public int numeroScialuppeBloccate()
	{
		int contatoreScialuppeBloccate=0;
		for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
		{
			if(mappa.getListaSettoriTotali().get(i).getBloccata()==true)
				contatoreScialuppeBloccate++;
		}
		return contatoreScialuppeBloccate;
	}
	
	/**
	 * Questo metodo ritorna true se la stringa passato in ingresso contiene il nome di
	 * un settore esistente, altrimenti ritorna false.
	 * 	Se la stringa passata in ingresso ha una lunghezza diversa da tre caratteri, controlla
	 * 	che la lista contenga il nome di un tipo di settore presente sulla mappa.
	 * 	Se la stringa passata in ingresso è composta da tre caratteri, verifica che il primo 
	 * 	carattere sia una lettera compresa tra A e W. Se il secondo carattere è zero, allora il
	 * 	terzo deve essere un numero compreso tra 1 e 9. Se invece il secondo carattere è diverso
	 * 	da zero, il terzo deve essere un numero compreso tra 0 e 4.
	 * 
	 * @param settore 
	 * @return
	 */
	public boolean verificaEsistenzaSettore(String settore)
	{
		if(settore.length()!=3)
		{
			for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
			{
				if(settore==mappa.getListaSettoriTotali().get(i).getNomeSupplementare())
					return true;
			}
		}
		else
		{
			if(settore.charAt(0)>='A' && settore.charAt(0)<='W')
			{
				if(settore.charAt(1)=='0')
					if(settore.charAt(2)>='1' && settore.charAt(2)<='9')
						return true;
				if(settore.charAt(1)=='1')
					if(settore.charAt(2)>='0' && settore.charAt(2)<='4')
						return true;
			}
		}
		return false;
	}
	
	/**
	 * Questo metodo controlla che un giocatore possa effettuare una mossa verificando
	 * il valore dei parametri che gli vengono passati in ingresso.
	 * Il metodo ritorna true se la mossa è valida, false altrimenti.
	 * 
	 * Per prima cosa verifica che il settore di destinazione sia diverso dal settore
	 * di partenza.
	 * Costruisce un arraylist settoriNonTransitabili, in cui aggiunge tutti i settori 
	 * vuoti, il settore partenza umani e quello partenza alieni.
	 * Costruisce poi un arraylist settoriRaggiungibili, a cui aggiunge i settori adiacenti
	 * al settore di partenza e rimuove tutti i settori presenti anche nella lista
	 * settoriNonTransitabili. Decrementa di uno il valore della portata.
	 * Successivamente, finchè il valore della portata è maggiore di zero, alla listaSettoriRaggiungibili
	 * aggiunge i settori vicini di ogni settore presente nella listaSettoriRaggiungibili, toglie
	 * tutti quelli presenti anche nella listaSettoriNonTransitabili e decrementa la portata di uno.
	 * Se il parametro scialuppa passato in ingresso è true, allora vengono aggiunti alla 
	 * listaSettoriRaggiungibili anche i 4 settori scialuppa presenti nella mappa.
	 * Se il settore destinazione è presente nella listaSettoriRaggiungibili, allora il metodo ritorna
	 * true e il giocatore potrà effettuare la mossa; altrimenti ritorna false. 
	 * 
	 * @param settoreDestinazione settore in cui il giocatore vuole spostarsi
	 * @param settorePartenza settore in cui il giocatore si trova
	 * @param portata numero di settori di cui il giocatore si può muovere
	 * @param scialuppa true se il giocatore può raggiungere una scialuppa (umano),
	 * 			false altrimenti
	 * @return
	 */
	public boolean verificaMossa(String settoreDestinazione, String settorePartenza, int portata, boolean scialuppa)
	{
		if(settoreDestinazione.equals(settorePartenza))
			return false;
		int indiceSettoreDestinazione=this.convertitoreStringa_Indice(settoreDestinazione);
		ArrayList<Settore> settoriRaggiungibili=new ArrayList<>();
		ArrayList<Settore> settoriNonTransitabili=new ArrayList<>();
		settoriNonTransitabili.add(mappa.getListaSettoriTotali().get(this.convertitoreStringa_Indice(this.getPartenzaAlieni())));
		settoriNonTransitabili.add(mappa.getListaSettoriTotali().get(this.convertitoreStringa_Indice(this.getPartenzaUmani())));
		settoriNonTransitabili.addAll(this.getSettoriVuoti());
		settoriRaggiungibili.addAll(this.settoriAdiacenti(settorePartenza));
		settoriRaggiungibili.removeAll(settoriNonTransitabili);
		portata--;
		while(portata>0)
		{
			int lunghezzaSettoriRaggiungibili=settoriRaggiungibili.size();
			for(int i=0;i<lunghezzaSettoriRaggiungibili;i++)
				settoriRaggiungibili.addAll(this.settoriAdiacenti(settoriRaggiungibili.get(i).getNome()));
			settoriRaggiungibili.removeAll(settoriNonTransitabili);
			portata--;
		}
		if(scialuppa)
			settoriRaggiungibili.addAll(getSettoriScialuppa());
		if(settoriRaggiungibili.contains(mappa.getListaSettoriTotali().get(indiceSettoreDestinazione)))
			return true;
		return false;
	}
	
	/**
	 * Data in ingresso una stringa contenente le coordinate di un settore, ritorna
	 * un arraylist contenente tutti i settori adiacenti.
	 * 
	 * @param settore
	 * @return
	 */
	public ArrayList<Settore> settoriAdiacenti(String settore)
	{
		if(this.verificaEsistenzaSettore(settore))
		{
			int indiceSettore=this.convertitoreStringa_Indice(settore);
			return mappa.getMappa().get(mappa.getListaSettoriTotali().get(indiceSettore));
		}
		return null;
	}
	
	/**
	 * Data in ingresso una strina contenente le coordinate di un settore, ritorna
	 * l'intero che corrisponde alla posizione di tale settore nell'arraylist
	 * listaSettoriTotali della classe Mappa.
	 * 
	 * @param nome contiene le coordinate del settore
	 * @return posizione del settore all'interno della listaSettoriTotali
	 */
	public int convertitoreStringa_Indice(String nome)
	{
		int lettera=nome.charAt(0)-65;
		int numero=((nome.charAt(1)-48)*10+nome.charAt(2)-48)-1;
		int indiceSettore=lettera+(numero*23);
		return indiceSettore;
	}
	
	/**
	 * Il metodo verifica se, con la mossa fatta, il giocatore vince la partita: ritorna true
	 * se il giocatore ha vinto, false altrimenti.
	 * 
	 * Se il personaggio del giocatore è alieno, allora ritorna false. Altrimenti controlla che la
	 * mossa sia valida. 
	 * 
	 * @param settoreDestinazione
	 * @param settorePartenza
	 * @param portata
	 * @param personaggio
	 * @return
	 */
	public boolean verificaVittoria(String settoreDestinazione, String settorePartenza, int portata, Personaggio personaggio)
	{
		if(personaggio==Personaggio.ALIENO)
			return false;
		if(verificaMossa(settoreDestinazione, settorePartenza, portata, true)==true)
			return true;
		return false;
	}
	
	/**
	 * Controlla se il settore avente le coordinate passate in ingresso come stringa
	 * è un settore scialuppa.
	 * Il metodo ritorna true se il settore è una scialuppa, false altrimenti.
	 * 
	 * @param settore coordinate del settore
	 * @return
	 */
	public boolean isScialuppa(String settore)
	{
		if(this.getSettoriScialuppa().contains(mappa.getListaSettoriTotali().get(this.convertitoreStringa_Indice(settore))))
			return true;
		return false;
	}
	
	/**
	 * Il metodo controlla se il settore, le cui coordinate sono passate in ingresso come stringa,
	 * è un settore sicuro oppure pericoloso.
	 * Se il settore è sicuro, ritorna "sicuro"; se il settore è pericoloso, ritorna "pericoloso".
	 * 
	 * @param nomeSettore coordinate del settore
	 * @return
	 */
	public String settoreSicuro_Pericoloso(String nomeSettore)
	{
		int indiceNomeSettore=this.convertitoreStringa_Indice(nomeSettore);
		if(this.mappa.getListaSettoriTotali().get(indiceNomeSettore).getNomeSupplementare()=="Settore pericoloso")
			return "pericoloso";
		return "sicuro";
	}
}
