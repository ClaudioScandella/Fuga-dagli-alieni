package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.mappa.Settore;

import java.io.IOException;
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
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai gia mosso! Dovevi usare questa carta prima di muovere.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha attaccato in settore "+this.partita.giocatoreCorrente().getPosizione()+".\n");
				
				return;
			}
			System.out.println("Grande! Ora puoi muovere di due caselle!");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Grande! Ora puoi muovere di due caselle!\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto adrenalina.\n");
			
			partita.giocatoreCorrente().setPortata(2);			
			break;
		case "ATTACCO":
			System.out.println("Verifico se hai gia mosso e quindi puoi attaccare.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Verifico se hai gia mosso e quindi puoi attaccare.\n");
			
			if(partita.giocatoreCorrente().getHaMosso()==false)
			{
				System.out.println("Non puoi attaccare! Devi muovere prima di farlo.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi attaccare! Devi muovere prima di farlo.\n");
				
				return;
			}
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "OK puoi attaccare.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto attacco.\n");
			partita.giocatoreCorrente().setPuoAttaccare(true);
			ControllerAzioni azione=new ControllerAzioni("attacco",partita);
			azione.agisci();
			break;
		case "LUCI":
//			System.out.println("Scrivi il settore centrale tra quelli che vuoi illuminare.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Scrivi il settore centrale tra quelli che vuoi illuminare.\n");
			
			/*
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String comando=br.readLine();
			if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(comando)==false)
			{
				return;
			}
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto luci.\n");
			ArrayList<Settore> listaSettoriDaIlluminare=new ArrayList<>();
			listaSettoriDaIlluminare.addAll(partita.getPartita().getControllerMappa().settoriAdiacenti(comando));
			listaSettoriDaIlluminare.add(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(comando)));
			for(int i=0;i<listaSettoriDaIlluminare.size();i++)
			{
				System.out.print("Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: ");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: ");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: ");
				
				for(Giocatore giocatore : partita.getGiocatoriInSettore(listaSettoriDaIlluminare.get(i).getNome()))
				{
					System.out.print(giocatore.getNomeUtente()+" ");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, giocatore.getNomeUtente()+" ");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, giocatore.getNomeUtente()+" ");
					
				}
				System.out.println("\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, ".\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, ".\n");
				
			}
			*/
			break;
		case "SEDATIVI":
			if(partita.giocatoreCorrente().getHaMosso()==true)
			{
				System.out.println("Hai gia mosso. Dovevi usare questa carta prima di muovere.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai gia mosso. Dovevi usare questa carta prima di muovere.\n");
				
				return;
			}
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto sedativi.\n");
			partita.giocatoreCorrente().setSedativi(true);
			break;
		case "TELETRASPORTO":
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto teletrasporto.\n");
			partita.giocatoreCorrente().setPosizione(partita.getPartita().getControllerMappa().getPartenzaUmani());
			partita.giocatoreCorrente().scartaOggetto(oggetto);
			break;
		default:
			System.out.println("Non hai o non esiste questo oggetto.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non hai o non esiste questo oggetto.\n");
			return;
		}
		partita.giocatoreCorrente().scartaOggetto(oggetto);
		partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
		this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
		return ;
	}
	
	public void inserisciSettoreLuci(String settore)
	{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String comando=br.readLine();
		if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==false)
		{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Settore inesistente.\n");
			return;
		}
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto luci.\n");
		ArrayList<Settore> listaSettoriDaIlluminare=new ArrayList<>();
		listaSettoriDaIlluminare.addAll(partita.getPartita().getControllerMappa().settoriAdiacenti(settore));
		listaSettoriDaIlluminare.add(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)));
		for(int i=0;i<listaSettoriDaIlluminare.size();i++)
		{
			System.out.print("Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: ");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: ");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Nel settore "+listaSettoriDaIlluminare.get(i).getNome()+" ci sono: ");
			for(Giocatore giocatore : partita.getGiocatoriInSettore(listaSettoriDaIlluminare.get(i).getNome()))
			{
				System.out.print(giocatore.getNomeUtente()+" ");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, giocatore.getNomeUtente()+" ");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, giocatore.getNomeUtente()+" ");
			}
			System.out.println("\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, ".\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, ".\n");
		}
		this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}