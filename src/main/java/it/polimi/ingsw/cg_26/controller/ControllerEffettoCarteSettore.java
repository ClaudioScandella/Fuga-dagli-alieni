package it.polimi.ingsw.cg_26.controller;

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
					return;
				}
			}
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
			}
			CartaOggetto cartaOggetto=partita.getPartita().getControllerMazzoCarteOggetto().pescaCartaOggetto();
			partita.giocatoreCorrente().setCartaOggetto(cartaOggetto);
		}
	}

}
