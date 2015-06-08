package it.polimi.ingsw.cg_26.controller;


import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
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
			System.out.println("Carta pescata: SILENZIO!");
			break;
		case RUMOREaSCELTA:
			System.out.println("Carta pescata: RUMOREaSCELTA!");
			while(true)
			{
				System.out.println("Inserisci il settore dove vuoi far rumore.");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String comando=br.readLine().toString();
				if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(comando)==true)
				{
					System.out.println("Rumore in settore: "+comando);
					break;
				}
			}
			break;
		case RUMOREproprioSETTORE:
			System.out.println("Carta pescata: RUMOREproprioSETTORE");
			System.out.println("Rumore in settore: "+partita.giocatoreCorrente().getPosizione());
			break;
		}
		
		if(carta.getConOggetto())
		{
			System.out.println("La carta conteneva un oggetto. Pesca una carta oggetto.");
			if(partita.getPartita().getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo()==0)
			{
				partita.getPartita().getControllerMazzoCarteOggetto().rigeneraMazzo();
				if(partita.getPartita().getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo()==0)
				{
					System.out.println("Mazzo oggetti vuoto: nessuna carta pescata.");
					return;
				}
				System.out.println("Mazzo oggetti rigenerato dagli scarti.");
			}
			CartaOggetto cartaOggetto=partita.getPartita().getControllerMazzoCarteOggetto().pescaCartaOggetto();
			partita.giocatoreCorrente().setCartaOggetto(cartaOggetto);
			System.out.println("Carta pescata: "+cartaOggetto.getTipoOggetto().name());
			if(partita.giocatoreCorrente().getCarteOggetto().size()==4)
			{
				System.out.println("Attenzione! Hai 4 oggetti, usane o scartane uno.");
				while(true)
				{
					System.out.println("\"uso\" per usare; \"scarta\" per scartare");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					String comando=br.readLine();
					if(comando.equals("uso"))
					{
						if(partita.giocatoreCorrente().getPersonaggio().equals(Personaggio.ALIENO))
						{
							System.out.println("Sei un alieno. Sei obbligato a scartare.");
							continue;
						}
						ControllerAzioni controllerAzioni=new ControllerAzioni("carta", this.partita);
						controllerAzioni.agisci();
						break;
					}
					else if(comando.equals("scarta"))
					{
						System.out.println("Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto());
						System.out.println("Scrivi il nome dell'oggetto che vuoi scartare.");
						comando=br.readLine();
						if(partita.giocatoreCorrente().possiedeCartaOggetto(comando.toUpperCase()))
						{
							CartaOggetto oggetto=partita.giocatoreCorrente().getCartaOggetto(comando.toUpperCase());
							partita.giocatoreCorrente().scartaOggetto(oggetto);
							partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
							break;
						}
					}
				}
			}
		}
		if(carta.getConOggetto()==false)
		{
			System.out.println("Carta senza oggetto.");
		}
	}

}
