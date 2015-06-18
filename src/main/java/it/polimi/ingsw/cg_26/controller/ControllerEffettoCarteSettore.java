package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaSettore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerEffettoCarteSettore
{
	private ControllerPartita partita;
	
	public ControllerEffettoCarteSettore(ControllerPartita partita)
	{
		this.partita=partita;
	}
	
	public void pescaEdEseguiEffettoCarta() throws IOException
	{
		if(partita.getPartita().getControllerMazzoCarteSettore().numeroCarteSettoreNelMazzo()==0)
		{
			partita.getPartita().getControllerMazzoCarteSettore().rigeneraMazzo();
		}
		
		CartaSettore carta=this.partita.getPartita().getControllerMazzoCarteSettore().pescaCartaSettore();
		switch(carta.getTipoCartaSettore())
		{
		case SILENZIO:
//			System.out.println("Carta pescata: SILENZIO!");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: SILENZIO!\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Carta pescata: SILENZIO!\n");
			
			break;
		case RUMOREaSCELTA:
//			System.out.println("Carta pescata: RUMOREaSCELTA!");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: RUMOREaSCELTA!\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci il settore dove vuoi far rumore.\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_RUMORE);
			/*
			while(true)
			{
//				System.out.println("Inserisci il settore dove vuoi far rumore.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci il settore dove vuoi far rumore.\n");
				
				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String comando=br.readLine().toString();
				if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(comando)==true)
				{
//					System.out.println("Rumore in settore: "+comando);
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Rumore in settore: "+comando+".\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" dichiara rumore in settore: "+comando+".\n");
					
					break;
				}
			}
			*/
			break;
		case RUMOREproprioSETTORE:
//			System.out.println("Carta pescata: RUMOREproprioSETTORE");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: RUMOREproprioSETTORE.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci la tua posizione.\n");
			/*
			while(true)
			{
//				System.out.println("Inserisci la tua posizione.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci la tua posizione.\n");
				
				
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String comando=br.readLine().toString();
				if(this.partita.giocatoreCorrente().getPosizione().equals(comando))
				{
//					System.out.println("Rumore in settore: "+comando);
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Rumore in settore: "+comando+".\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" dichiara rumore in settore: "+comando+".\n");
					
					break;
				}
			}
			*/
		}
		
		if(carta.getConOggetto())
		{
//			System.out.println("La carta conteneva un oggetto. Pesca una carta oggetto.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La carta settore conteneva un oggetto. Pesca una carta oggetto.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "La carta settore conteneva un oggetto. "+this.partita.giocatoreCorrente().getNomeUtente()+" pesca una carta oggetto.\n");
			
			if(partita.getPartita().getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo()==0)
			{
				partita.getPartita().getControllerMazzoCarteOggetto().rigeneraMazzo();
				if(partita.getPartita().getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo()==0)
				{
//					System.out.println("Mazzo oggetti vuoto: nessuna carta pescata.");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Mazzo oggetti vuoto: nessuna carta pescata.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Mazzo oggetti vuoto: nessuna carta pescata.\n");
					
					return;
				}
//				System.out.println("Mazzo oggetti rigenerato dagli scarti.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Mazzo oggetti rigenerato dagli scarti.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Mazzo oggetti rigenerato dagli scarti.\n");
				
			}
			CartaOggetto cartaOggetto=partita.getPartita().getControllerMazzoCarteOggetto().pescaCartaOggetto();
			partita.giocatoreCorrente().setCartaOggetto(cartaOggetto);
//			System.out.println("Carta pescata: "+cartaOggetto.getTipoOggetto().name());
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: "+cartaOggetto.getTipoOggetto().name()+",\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Carta pescata.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 3, "0");
			if(partita.giocatoreCorrente().getCarteOggetto().size()==4)
			{
//				System.out.println("Attenzione! Hai 4 oggetti, usane o scartane uno.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione! Hai 4 oggetti, usane o scartane uno.\n");
				
				while(true)
				{
//					System.out.println("\"uso\" per usare; \"scarta\" per scartare");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "\"uso\" per usare; \"scarta\" per scartare\n");
					
					
					
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String comando=br.readLine();
					if(comando.equals("uso"))
					{
						if(partita.giocatoreCorrente().getPersonaggio().equals(Personaggio.ALIENO))
						{
//							System.out.println("Sei un alieno. Sei obbligato a scartare.");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Sei un alieno. Sei obbligato a scartare.\n");
							
							continue;
						}
						ControllerAzioni controllerAzioni=new ControllerAzioni("carta", this.partita);
						controllerAzioni.agisci();
						break;
					}
					else if(comando.equals("scarta"))
					{
//						System.out.println("Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto());
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto()+".\n");
						
//						System.out.println("Scrivi il nome dell'oggetto che vuoi scartare.");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Scrivi il nome dell'oggetto che vuoi scartare.\n");
						
						comando=br.readLine();
						if(partita.giocatoreCorrente().possiedeCartaOggetto(comando.toUpperCase()))
						{
							CartaOggetto oggetto=partita.giocatoreCorrente().getCartaOggetto(comando.toUpperCase());
							partita.giocatoreCorrente().scartaOggetto(oggetto);
							partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Oggetto scartato.\n");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" scarta oggetto "+comando.toLowerCase()+".\n");
							break;
						}
					}
				}
			}
		}
		if(carta.getConOggetto()==false)
		{
//			System.out.println("Carta senza oggetto.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta senza oggetto.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 3, "0");
			
		}
	}
	
	public void inserisciSettoreRumoreAScelta(String settore)
	{
//			System.out.println("Inserisci il settore dove vuoi far rumore.");
//			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci il settore dove vuoi far rumore.\n");
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			String comando=br.readLine().toString();
			if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==true)
			{
//				System.out.println("Rumore in settore: "+comando);
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
//			System.out.println("Inserisci la tua posizione.");
//			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci la tua posizione.\n");
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			String comando=br.readLine().toString();
		if(this.partita.giocatoreCorrente().getPosizione().equals(settore))
		{
//			System.out.println("Rumore in settore: "+comando);
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Rumore in settore: "+settore+".\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" dichiara rumore in settore: "+settore+".\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
		}
		else
		{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non sei in quel settore.\nInserisci la tua posizione.\n");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
