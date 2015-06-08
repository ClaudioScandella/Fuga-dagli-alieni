package it.polimi.ingsw.cg_26.controller;


import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.mappa.Settore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ControllerEffettoCarteOggetto
{
	private ControllerPartita partita;
	private String tipoCarta="";

	public ControllerEffettoCarteOggetto(String tipoCarta, ControllerPartita partita)
	{
		this.partita=partita;
		this.tipoCarta=tipoCarta;
	}

	public void eseguiEffettoCarta(CartaOggetto oggetto) throws IOException
	{
		switch(tipoCarta)
		{
		case "ADRENALINA":
			if(partita.giocatoreCorrente().getHaMosso()==true)
			{
				System.out.println("Hai gia mosso! Dovevi usare questa carta prima di muovere.");
				return;
			}
			System.out.println("Grande! Ora puoi muovere di due caselle!");
			partita.giocatoreCorrente().setPortata(2);			
			break;
		case "ATTACCO":
			System.out.println("Verifico se hai gia mosso e quindi puoi attaccare.");
			if(partita.giocatoreCorrente().getHaMosso()==false)
			{
				System.out.println("Non puoi attaccare! Devi muovere prima di farlo.");
				return;
			}
			partita.giocatoreCorrente().setPuoAttaccare(true);
			ControllerAzioni azione=new ControllerAzioni("attacco",partita);
			azione.agisci();
			break;
		case "LUCI":
			System.out.println("Scrivi il settore centrale tra quelli che vuoi illuminare.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String comando=br.readLine();
			if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(comando)==false)
			{
				return;
			}
			ArrayList<Settore> listaSettoriDaIlluminare=new ArrayList<>();
			listaSettoriDaIlluminare.addAll(partita.getPartita().getControllerMappa().settoriAdiacenti(comando));
			listaSettoriDaIlluminare.add(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(comando)));
			for(int i=0;i<listaSettoriDaIlluminare.size();i++)
			{
				System.out.print("Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: ");
				for(Giocatore giocatore : partita.getGiocatoriInSettore(listaSettoriDaIlluminare.get(i).getNome()))
				{
					System.out.print(giocatore.getNomeUtente()+" ");
				}
				System.out.println("\n");
			}
			break;
		case "SEDATIVI":
			if(partita.giocatoreCorrente().getHaMosso()==true)
			{
				System.out.println("Hai gia mosso. Dovevi usare questa carta prima di muovere.");
				return;
			}
			partita.giocatoreCorrente().setSedativi(true);
			break;
		case "TELETRASPORTO":
			partita.giocatoreCorrente().setPosizione(partita.getPartita().getControllerMappa().getPartenzaUmani());
			partita.giocatoreCorrente().scartaOggetto(oggetto);
			break;
		default:
			System.out.println("Non hai o non esiste questo oggetto.");
			return;
		}
		partita.giocatoreCorrente().scartaOggetto(oggetto);
		partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
		return ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
