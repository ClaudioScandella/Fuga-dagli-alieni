package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.carte.CartaOggetto;
import model.mappa.Settore;

public class ControllerEffettoCarteOggetto
{
	private ControllerPartita partita;
	private String tipoCarta;

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
			ControllerAzioni azione=new ControllerAzioni("attacco",partita);
			break;
		case "LUCI":
			System.out.println("Scrivi il settore centrale tra quelli che vuoi illuminare.");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String comando=br.readLine().toString();
			if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(comando)==false)
			{
				return;
			}
			ArrayList<Settore> listaSettoriDaIlluminare=new ArrayList<>();
			listaSettoriDaIlluminare.addAll(partita.getPartita().getControllerMappa().settoriAdiacenti(comando));
			listaSettoriDaIlluminare.add(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(comando)));
			for(int i=0;i<listaSettoriDaIlluminare.size();i++)
			{
				System.out.println("Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: "+partita.getGiocatoriInSettore(listaSettoriDaIlluminare.get(i).getNome()));
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
		}
		partita.giocatoreCorrente().scartaOggetto(oggetto);
		partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
		return ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
