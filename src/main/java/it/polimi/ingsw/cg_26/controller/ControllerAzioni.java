package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


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
			if(!partita.giocatoreCorrente().getPuoAttaccare())
			{
				System.out.println("Attenzione sei un umano, puoi attaccare solo con la carta ATTACCO.");
				break;
			}
			if(partita.giocatoreCorrente().getHaAttaccato())
			{
				System.out.println("Non puoi attaccare! Lo hai gia fatto in questo turno.");
				break;
			}
			if(partita.giocatoreCorrente().getPersonaggio().equals(Personaggio.ALIENO))
			{
				System.out.println("Verifico se hai gia mosso e quindi puoi attaccare.");
				if(partita.giocatoreCorrente().getHaMosso()==false)
				{
					System.out.println("Non puoi attaccare! Devi muovere prima di farlo.");
					break;
				}
				System.out.println("OK puoi attaccare.");
			}
			partita.giocatoreCorrente().setHaAttaccato(true);
			System.out.println("Verifico se hai ucciso qualcuno.");
			String settoreDiAttacco=partita.giocatoreCorrente().getPosizione();
			ArrayList<Giocatore> listaGiocatoriAttaccati=new ArrayList<>();
			Giocatore giocatoreSalvatoConDifesa=null;
			listaGiocatoriAttaccati.addAll(partita.getGiocatoriInSettore(settoreDiAttacco));
			listaGiocatoriAttaccati.remove(partita.giocatoreCorrente());
			if(listaGiocatoriAttaccati.size()>0)
			{
				System.out.println("Hai ucciso qualcuno!");
				partita.giocatoreCorrente().setHaUcciso(true);
				for(Giocatore giocatore : listaGiocatoriAttaccati)
				{
					if(giocatore.possiedeCartaOggetto("DIFESA")==true && giocatore.getPersonaggio().equals(Personaggio.UMANO)==true)
					{
						giocatoreSalvatoConDifesa=giocatore;
						System.out.println("Il giocatore "+giocatore.getNomeUtente()+" si � salvato con la carta difesa.");
						CartaOggetto oggetto=giocatore.getCartaOggetto("DIFESA");
						giocatore.scartaOggetto(oggetto);
						partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
					}
					else
					{
						while(giocatore.getCarteOggetto().size()>0)
						{
							CartaOggetto oggetto=giocatore.getCarteOggetto().get(0);
							giocatore.scartaOggetto(oggetto);
							partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
						}
						giocatore.setVittoria_sconfitta("sconfitta");
						giocatore.setInVita(false);
						giocatore.setPosizione(null);
					}
				}
				listaGiocatoriAttaccati.remove(giocatoreSalvatoConDifesa);
				System.out.print("Hai ucciso:");
				for(Giocatore giocatore : listaGiocatoriAttaccati)
					System.out.print(" "+giocatore.getNomeUtente());
				System.out.println(".");
			}
			else
				System.out.println("Non hai ucciso nessuno.");
			if(this.partita.numeroUmaniInGioco()==0)
				for(Giocatore giocatore : this.partita.getPartita().getGiocatori())
					if(giocatore.getPersonaggio().equals(Personaggio.ALIENO))
						giocatore.setVittoria_sconfitta("vittoria");
			else
				if(this.partita.numeroAlieniInGioco()==0)
					for(Giocatore gamer : this.partita.getPartita().getGiocatori())
						if(gamer.getPersonaggio().equals(Personaggio.UMANO) && gamer.getInVita())
							gamer.setVittoria_sconfitta("vittoria");
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
				String settore=br.readLine();
				if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==true)
				{
					if(partita.getPartita().getControllerMappa().isScialuppa(settore)==true)
					{
						if(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).getBloccata()==true)
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
								partita.giocatoreCorrente().setInVita(false);
								partita.giocatoreCorrente().setVittoria_sconfitta("vittoria");
								partita.giocatoreCorrente().setPosizione(null);
								partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).setBloccata(true);
								partita.giocatoreCorrente().setHaMosso(true);
								partita.giocatoreCorrente().setPuoPassare(true);
								while(this.partita.giocatoreCorrente().getCarteOggetto().size()>0)
								{
									CartaOggetto oggetto=this.partita.giocatoreCorrente().getCarteOggetto().get(0);
									this.partita.giocatoreCorrente().scartaOggetto(oggetto);
									partita.getPartita().getControllerMazzoCarteOggetto().aggiungiCartaAScartiOggetto(oggetto);
								}
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
							if(partita.giocatoreCorrente().getSedativi())
								System.out.println("Non peschi carte settore perch� hai usato SEDATIVI.");
							else
							{
								System.out.println("Pesca la carta settore.");
								ControllerEffettoCarteSettore controllerEffettoCarteSettore=new ControllerEffettoCarteSettore(partita);
								controllerEffettoCarteSettore.pescaEdEseguiEffettoCarta();
							}
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
				System.out.println("Ti mostro comunque le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto());
				break;
			}
			else
			{
				System.out.println("Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto());
				System.out.println("Scrivi il nome dell'oggetto che vuoi usare. Non puoi usare carta difesa.");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String comando=br.readLine();
				System.out.println("Controllo che tu abbia davvero quella carta.");
				if(partita.giocatoreCorrente().possiedeCartaOggetto(comando.toUpperCase())==true)
				{
					System.out.println("Sembra proprio tu ce l'abbia.");
					System.out.println("Controllo che non hai deciso di usare la carta difesa.");
					if(comando.toLowerCase().equals("difesa"))
					{
						System.out.println("Attenzione! La carta difesa si attiva solo automaticamente quando vieni attaccato e solo se sei umano.");
						break;
					}
					System.out.println("Hai deciso di usare la carta "+comando.toLowerCase());
					System.out.println("Aziono gli effetti della carta.");
					ControllerEffettoCarteOggetto controllerEffettoCarteOggetto=new ControllerEffettoCarteOggetto(comando.toUpperCase(),partita);
					CartaOggetto oggetto=partita.giocatoreCorrente().getCartaOggetto(comando.toUpperCase());
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
				{
					partita.giocatoreCorrente().setPortata(1);
					partita.giocatoreCorrente().setPuoAttaccare(false);
					partita.giocatoreCorrente().setSedativi(false);
				}
				partita.giocatoreCorrente().setPuoPassare(false);
				partita.giocatoreCorrente().setHaMosso(false);
				partita.giocatoreCorrente().setHaPassato(true);
				partita.giocatoreCorrente().setHaAttaccato(false);
			}
			break;
		}
	}
	
	@Override
	public String toString()
	{
		String stringaDaStampare="";
		
		for(CartaOggetto oggetto : partita.giocatoreCorrente().getCarteOggetto())
		{
			stringaDaStampare+=oggetto.getTipoOggetto();
		}
		return stringaDaStampare;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
