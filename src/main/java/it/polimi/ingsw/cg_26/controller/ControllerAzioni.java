package it.polimi.ingsw.cg_26.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.Giocatore;
import model.Giocatore.Personaggio;
import model.carte.CartaOggetto;
import model.carte.CartaScialuppa.Colore;

public class ControllerAzioni
{
	private enum Azione{ATTACCO, MOSSA, CARTA, PASSO;}
	private Azione azione=null;
	private ControllerPartita partita;

	public ControllerAzioni(String azione, ControllerPartita partita)
	{
		this.partita=partita;
		switch(azione.toLowerCase())
		{
			case "attacco":
				this.azione=Azione.ATTACCO;
				break;
			case "mossa":
				this.azione=Azione.MOSSA;
				break;
			case "carta":
				this.azione=Azione.CARTA;
				break;
			case "passo":
				this.azione=Azione.PASSO;
				break;
			default:
				System.out.println("Non hai inserito un comando valido.");
				break;
		}
	}

	public void agisci() throws IOException
	{
		if(this.azione==null)
			return;
		switch(azione)
		{
		case ATTACCO:
			System.out.println("Verifico se hai gia mosso e quindi puoi attaccare.");
			if(partita.giocatoreCorrente().getHaMosso()==false)
			{
				System.out.println("Non puoi attaccare! Devi muovere prima di farlo.");
				break;
			}
			System.out.println("OK puoi attaccare.");
			System.out.println("Verifico se hai ucciso qualcuno.");
			String settoreDiAttacco=partita.giocatoreCorrente().getPosizione();
			ArrayList<Giocatore> listaGiocatoriAttaccati=new ArrayList<>();
			listaGiocatoriAttaccati.addAll(partita.getGiocatoriInSettore(settoreDiAttacco));
			listaGiocatoriAttaccati.remove(partita.giocatoreCorrente());
			if(listaGiocatoriAttaccati.size()>0)
			{
				System.out.println("Hai ucciso qualcuno!");
				partita.giocatoreCorrente().setHaUcciso(true);
				for(Giocatore giocatore : listaGiocatoriAttaccati)
				{
					if(giocatore.possiedeCartaOggetto("DIFESA") && giocatore.getPersonaggio().equals(Personaggio.UMANO))
					{
						System.out.println("Il giocatore "+giocatore.getNomeUtente()+" si � salvato con la carta difesa.");
						CartaOggetto oggetto=giocatore.getCartaOggetto("DIFESA");
						giocatore.scartaOggetto(oggetto);
						partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
						listaGiocatoriAttaccati.remove(giocatore);
					}
					else
					{
						for(CartaOggetto oggetto : giocatore.getCarteOggetto())
						{
							giocatore.scartaOggetto(oggetto);
							partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
						}
						giocatore.setVittoria_sconfitta("sconfitta");
						giocatore.setInVita(false);
						giocatore.setPosizione(null);
					}
				}
				System.out.print("Hai ucciso:");
				for(Giocatore giocatore : listaGiocatoriAttaccati)
				{
					System.out.print(" "+giocatore.getNomeUtente());
				}
				System.out.println(".");
			}
			else
			{
				System.out.println("Non hai ucciso nessuno.");
			}			
			break;
		case MOSSA:
			if(partita.giocatoreCorrente().getHaMosso()==true)
			{
				System.out.println("Non puoi muovere! Hai gia mosso in questo turno.");
				break;
			}
			while(true)
			{
				System.out.println("Inserisci il settore: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String settore=br.readLine().toString();
				if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==true)
				{
					if(partita.getPartita().getControllerMappa().isScialuppa(settore)==true)
					{
						if(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).getBloccata()==false)
						{
							System.out.println("La scialuppa � bloccata");
							break;
						}
						if(partita.getPartita().getControllerMappa().verificaVittoria(settore, partita.giocatoreCorrente().getPosizione(), partita.giocatoreCorrente().getPortata(), partita.giocatoreCorrente().getPersonaggio())==true)
						{
							System.out.println("Ora pesca la carta scialuppa. Se � verde allora riesci a fuggire, altrimenti devi andare verso una nuova scialuppa.");
							if(partita.getPartita().getControllerMazzoCarteScialuppa().pesca().getColore()==Colore.VERDE)
							{
								System.out.println("HAI VINTO!");
								//aggiorno vittorie, spostamenti e altro
								partita.getPartita().getGiocatori().get(partita.giocatoreCorrente().getIdGiocatore()).setInVita(false);
								partita.getPartita().getGiocatori().get(partita.giocatoreCorrente().getIdGiocatore()).setVittoria_sconfitta("vittoria");
								partita.getPartita().getGiocatori().get(partita.giocatoreCorrente().getIdGiocatore()).setPosizione(null);
								partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).setBloccata(true);
								break;
							}
							System.out.println("La carta � rossa, non puoi fuggire da qui");
							partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).setBloccata(true);
							partita.giocatoreCorrente().setPosizione(settore);
							partita.giocatoreCorrente().setHaMosso(true);
							partita.giocatoreCorrente().setPuoPassare(true);
							break;
						}
					}
					else if(partita.getPartita().getControllerMappa().verificaMossa(settore,  partita.giocatoreCorrente().getPosizione(), partita.giocatoreCorrente().getPortata(),false)==true)
					{
						partita.giocatoreCorrente().setPosizione(settore);
						System.out.println("Posizione aggiornata!");
						partita.giocatoreCorrente().setHaMosso(true);
						partita.giocatoreCorrente().setPuoPassare(true);
						//aggiorno log e posizione giocatore
						//controllo tipo settore e agisco di conseguenza
						if(partita.getPartita().getControllerMappa().settoreSicuro_Pericoloso(settore)=="pericoloso")
						{
							System.out.println("Pesca la carta settore.");
							ControllerEffettoCarteSettore controllerEffettoCarteSettore=new ControllerEffettoCarteSettore(partita);
							controllerEffettoCarteSettore.pescaEdEseguiEffettoCarta();
						}
						else
						{
							System.out.println("Non viene pescata nessuna carta.");
						}
						break;
					}	
				}
				System.out.println("Input non valido.");
			}
			break;
		case CARTA:
			if(partita.giocatoreCorrente().getPersonaggio()==Personaggio.ALIENO)
			{
				System.out.println("Attenzione! Sei un alieno! Solo gli umani possono usare le carte oggetto.");
				break;
			}
			else
			{
				System.out.println("Ti mostro le carte che possiedi: "+partita.giocatoreCorrente().getCarteOggetto().toString());
				System.out.println("Scrivi il nome dell'oggetto che vuoi usare. Non puoi usare carta difesa.");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String comando=br.readLine().toString();
				System.out.println("Controllo che tu abbia davvero quella carta.");
				if(partita.giocatoreCorrente().possiedeCartaOggetto(comando.toUpperCase())==true)
				{
					System.out.println("Sembra proprio tu ce l'abbia.");
					System.out.println("Controllo che non hai deciso di usare la carta difesa.");
					if(comando.toLowerCase()=="difesa")
					{
						System.out.println("Attenzione! La carta difesa si attiva solo automaticamente quando vieni attaccato e solo se sei umano.");
						break;
					}
					System.out.println("Hai deciso di usare la carta "+comando.toLowerCase());
					System.out.println("Aziono gli effetti della carta.");
					ControllerEffettoCarteOggetto controllerEffettoCarteOggetto=new ControllerEffettoCarteOggetto(comando.toUpperCase(),partita);
					CartaOggetto oggetto=partita.giocatoreCorrente().getCartaOggetto(comando);
					controllerEffettoCarteOggetto.eseguiEffettoCarta(oggetto);
				}
			}
			break;
		case PASSO:
			if(partita.giocatoreCorrente().getPuoPassare()==false)
			{
				System.out.println("Non puoi passare! Devi prima muovere.");
			}
			else
			{
				if(partita.giocatoreCorrente().getPersonaggio()==Personaggio.UMANO)
					partita.giocatoreCorrente().setPortata(1);
				partita.giocatoreCorrente().setPuoPassare(false);
				partita.giocatoreCorrente().setHaMosso(false);
				partita.giocatoreCorrente().setHaPassato(true);
			}
			break;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
