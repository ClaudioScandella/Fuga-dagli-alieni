package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;

import java.io.IOException;

public class ControllerEffettoCarteSettore
{
	private ControllerPartita partita;
	
	public ControllerEffettoCarteSettore(ControllerPartita partita)
	{
		this.partita=partita;
	}
	
	public boolean pescaEdEseguiEffettoCarta() throws IOException
	{
		if(partita.getPartita().getControllerMazzoCarteSettore().numeroCarteSettoreNelMazzo()==0)
			partita.getPartita().getControllerMazzoCarteSettore().rigeneraMazzo();
		CartaSettore carta=this.partita.getPartita().getControllerMazzoCarteSettore().pescaCartaSettore();
		switch(carta.getTipoCartaSettore())
		{
		case SILENZIO:
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: SILENZIO!\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Carta pescata: SILENZIO!\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
			return carta.getConOggetto() ? true:false;
		case RUMOREaSCELTA:
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: RUMOREaSCELTA!\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci il settore dove vuoi far rumore.\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE);
			return carta.getConOggetto() ? true:false;
		case RUMOREproprioSETTORE:
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: RUMOREproprioSETTORE.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci la tua posizione.\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_PROPRIO_SETTORE);
			return carta.getConOggetto() ? true:false;
		default: return false;
		}
	}
	
	public void inserisciSettoreRumoreAScelta(String settore)
	{
		settore=settore.toUpperCase();
			if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==true)
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Rumore in settore: "+settore+".\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" dichiara rumore in settore: "+settore+".\n");
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
			}
			else
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Settore inesistente.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci il settore.\n");
			}
	}
	
	public void inserisciProprioSettore(String settore)
	{
		settore=settore.toUpperCase();
		if(this.partita.giocatoreCorrente().getPosizione().equals(settore))
		{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Rumore in settore: "+settore+".\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" dichiara rumore in settore: "+settore+".\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
		}
		else
		{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non sei in quel settore.\nInserisci la tua posizione.\n");
		}
	}
	
	public void usaOscarta(String comando)
	{
		if(comando.equals("uso"))
		{
			if(partita.giocatoreCorrente().getPersonaggio().equals(Personaggio.ALIENO))
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Sei un alieno. Sei obbligato a scartare.\n");
				return;
			}
			ControllerAzioni controllerAzioni=new ControllerAzioni("carta", this.partita);
			try
			{
				controllerAzioni.agisci();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return;
		}
		else if(comando.equals("scarta"))
		{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto()+".\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Scrivi il nome dell'oggetto che vuoi scartare.\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_CARTA_DA_SCARTARE);
		}
		else
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Comando non valido.\n");
	}

	public void scartaOggetto(String comando)
	{
		if(partita.giocatoreCorrente().possiedeCartaOggetto(comando.toUpperCase()))
		{
			CartaOggetto oggetto=partita.giocatoreCorrente().getCartaOggetto(comando.toUpperCase());
			partita.giocatoreCorrente().scartaOggetto(oggetto);
			partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Oggetto scartato.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" scarta oggetto "+comando.toLowerCase()+".\n");
			return;
		}
		else
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non possiedi l'oggetto.\n");
	}
}
