package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerAzioni
{
	private enum Azione{ATTACCO, MOSSA, CARTA, PASSO, CHAT;}
	private Azione azione=null;
	private ControllerPartita partita;
	private ControllerEffettoCarteOggetto controllerEffettoCarteOggetto;
	private ControllerEffettoCarteSettore controllerEffettoCarteSettore;

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
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_DESTINAZIONE);
				break;
			case "carta":
				this.azione=Azione.CARTA;
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_CARTA);
				break;
			case "passo":
				this.azione=Azione.PASSO;
				break;
			case "chat":
				this.azione=Azione.CHAT;
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_MESSAGGIO);
				break;
			default:
//				System.out.println("Non hai inserito un comando valido.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non hai inserito un comando valido.\n");
				break;
		}
	}

	public void agisci() throws IOException
	{
//		String messaggioDaRitornare="";
		if(this.azione==null)
			return;
		switch(azione)
		{
		case ATTACCO:
			if(!partita.giocatoreCorrente().getPuoAttaccare())
			{
//				System.out.println("Attenzione sei un umano, puoi attaccare solo con la carta ATTACCO.");
//				return "Attenzione sei un umano, puoi attaccare solo con la carta ATTACCO.";
//				break;
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione sei un umano, puoi attaccare solo con la carta ATTACCO.\n");
				return;
			}
			if(partita.giocatoreCorrente().getHaAttaccato())
			{
//				System.out.println("Non puoi attaccare! Lo hai gia fatto in questo turno.");
//				break;
//				return "Non puoi attaccare! Lo hai gia fatto in questo turno.";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi attaccare! Lo hai gia fatto in questo turno.\n");
				return;
			}
			if(partita.giocatoreCorrente().getPersonaggio().equals(Personaggio.ALIENO))
			{
//				System.out.println("Verifico se hai gia mosso e quindi puoi attaccare.");
//				messaggioDaRitornare="Verifico se hai gia mosso e quindi puoi attaccare.\n";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Verifico se hai gia mosso e quindi puoi attaccare.\n");
				if(partita.giocatoreCorrente().getHaMosso()==false)
				{
//					System.out.println("Non puoi attaccare! Devi muovere prima di farlo.");
//					break;
//					messaggioDaRitornare+="Non puoi attaccare! Devi muovere prima di farlo.\n";
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi attaccare! Devi muovere prima di farlo.\n");
					return;
				}
//				System.out.println("OK puoi attaccare.");
//				messaggioDaRitornare+="OK puoi attaccare.\n";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "OK puoi attaccare.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha attaccato in settore "+this.partita.giocatoreCorrente().getPosizione()+".\n");
			}
			partita.giocatoreCorrente().setHaAttaccato(true);
//			System.out.println("Verifico se hai ucciso qualcuno.");
//			messaggioDaRitornare+="Verifico se hai ucciso qualcuno.\n";
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Verifico se hai ucciso qualcuno.\n");
			String settoreDiAttacco=partita.giocatoreCorrente().getPosizione();
			ArrayList<Giocatore> listaGiocatoriAttaccati=new ArrayList<>();
			Giocatore giocatoreSalvatoConDifesa=null;
			listaGiocatoriAttaccati.addAll(partita.getGiocatoriInSettore(settoreDiAttacco));
			listaGiocatoriAttaccati.remove(partita.giocatoreCorrente());
			if(listaGiocatoriAttaccati.size()>0)
			{
//				System.out.println("Hai ucciso qualcuno!");
//				messaggioDaRitornare+="Hai ucciso qualcuno!\n";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai ucciso qualcuno.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha ucciso qualcuno.\n");
				partita.giocatoreCorrente().setHaUcciso(true);
				for(Giocatore giocatore : listaGiocatoriAttaccati)
				{
					if(giocatore.possiedeCartaOggetto("DIFESA")==true && giocatore.getPersonaggio().equals(Personaggio.UMANO)==true)
					{
						giocatoreSalvatoConDifesa=giocatore;
//						System.out.println("Il giocatore "+giocatore.getNomeUtente()+" si � salvato con la carta difesa.");
//						messaggioDaRitornare+="Il giocatore "+giocatore.getNomeUtente()+" si � salvato con la carta difesa\n";
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, giocatore.getNomeUtente()+" si � salvato con la carta difesa\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, giocatore.getNomeUtente()+" si � salvato con la carta difesa\n");
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
//				System.out.print("Hai ucciso:");
//				messaggioDaRitornare+="Hai ucciso:";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai ucciso:");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Sono stati uccisi:");
				for(Giocatore giocatore : listaGiocatoriAttaccati)
				{
//					System.out.print(" "+giocatore.getNomeUtente());
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, " "+giocatore.getNomeUtente());
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, " "+giocatore.getNomeUtente());
				}
//				System.out.println(".");
//				messaggioDaRitornare+=".\n";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, ".\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, ".\n");
			}
			else
//				System.out.println("Non hai ucciso nessuno.");
//			messaggioDaRitornare+="Non hai ucciso nessuno.\n";
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non hai ucciso nessuno.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Non � stato ucciso nessuno.\n");
			if(this.partita.numeroUmaniInGioco()==0)
				for(Giocatore giocatore : this.partita.getPartita().getGiocatori())
					if(giocatore.getPersonaggio().equals(Personaggio.ALIENO))
						giocatore.setVittoria_sconfitta("vittoria");
			else
				if(this.partita.numeroAlieniInGioco()==0)
					for(Giocatore gamer : this.partita.getPartita().getGiocatori())
						if(gamer.getPersonaggio().equals(Personaggio.UMANO) && gamer.getInVita())
							gamer.setVittoria_sconfitta("vittoria");
			return ;
		case MOSSA:
			if(partita.giocatoreCorrente().getHaMosso()==true)
			{
//				System.out.println("Non puoi muovere! Hai gia mosso in questo turno.");
//				return "Non puoi muovere! Hai gia mosso in questo turno.\n";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi muovere! Hai gia mosso in questo turno.\n");
				return;
			}
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_DESTINAZIONE);
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci settore destinazione.\n");
			/*
			while(true)
			{
//				System.out.println("Inserisci il settore: ");
//				return "Inserisci settore destinazione";
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci settore destinazione.\n");
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_DESTINAZIONE);
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String settore=br.readLine();
				if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==true)
				{
					if(partita.getPartita().getControllerMappa().isScialuppa(settore)==true)
					{
						if(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).getBloccata()==true)
						{
//							System.out.println("La scialuppa � bloccata");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La scialuppa � bloccata.\n");
							break;
						}
						if(partita.getPartita().getControllerMappa().verificaVittoria(settore, partita.giocatoreCorrente().getPosizione(), partita.giocatoreCorrente().getPortata(), partita.giocatoreCorrente().getPersonaggio())==true)
						{
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 0, settore);
//							System.out.println("Ora pesca la carta scialuppa. Se � verde allora riesci a fuggire, altrimenti devi andare verso una nuova scialuppa.");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ora pesca la carta scialuppa. Se � verde allora riesci a fuggire, altrimenti devi andare verso una nuova scialuppa.\n");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" � arrivato alla scialuppa "+settore+".\n");
							
							if(partita.getPartita().getControllerMazzoCarteScialuppa().pesca().getColore()==Colore.VERDE)
							{
//								System.out.println("HAI VINTO!");
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai pescato verde. HAI VINTO!\n");
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Ha pescato verde. Ha vinto.\n");
								
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
//							System.out.println("La carta � rossa, non puoi fuggire da qui");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La carta � rossa, non puoi fuggire da qui.\n");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Ha pescato carta rossa. Non pu� fuggire e la sciluppa risulta bloccata per tutti.\n");
							
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
//						System.out.println("Posizione aggiornata!");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Posizione aggiornata.\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha fatto la sua mossa.\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 0, settore);
						
						partita.giocatoreCorrente().setHaMosso(true);
						partita.giocatoreCorrente().setPuoPassare(true);
						//aggiorno log e posizione giocatore
						//controllo tipo settore e agisco di conseguenza
						if(partita.giocatoreCorrente().getSedativi())
						{
//							System.out.println("Non peschi carte settore perch� hai usato SEDATIVI.");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non peschi carte settore perch� hai usato sedativi.\n");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" non pesca carte settore perch� ha usato sedativi.\n");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 1, "0");
						}
						else if(partita.getPartita().getControllerMappa().settoreSicuro_Pericoloso(settore)=="pericoloso")
						{
//								System.out.println("Pesca la carta settore.");
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Pesca la carta settore.\n");
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" pesca la carta settore.\n");
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 1, "1");
								ControllerEffettoCarteSettore controllerEffettoCarteSettore=new ControllerEffettoCarteSettore(partita);
								controllerEffettoCarteSettore.pescaEdEseguiEffettoCarta();
						}
						else
						{
//							System.out.println("Non viene pescata nessuna carta.");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non peschi nessuna carta.\n");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Non viene pescata nessuna carta.\n");
							this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 1, "0");
						}
						break;
					}	
				}
//				System.out.println("Input non valido.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Input non valido.\n");
				
			}
			*/
			break;
		case CARTA:
			if(partita.giocatoreCorrente().getPersonaggio()==Personaggio.ALIENO)
			{
				System.out.println("Attenzione! Sei un alieno! Solo gli umani possono usare le carte oggetto.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione! Sei un alieno! Solo gli umani possono usare le carte oggetto.\n");
				
				System.out.println("Ti mostro comunque le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto());
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ti mostro comunque le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto()+".\n");
				
				break;
			}
			else
			{
//				System.out.println("Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto());
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto()+".\n");
				
//				System.out.println("Scrivi il nome dell'oggetto che vuoi usare. Non puoi usare carta difesa.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Scrivi il nome dell'oggetto che vuoi usare. Non puoi usare carta difesa.\n");
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_CARTA);
				
				/*
//				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//				String comando=br.readLine();
				
				System.out.println("Controllo che tu abbia davvero quella carta.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Controllo che tu abbia davvero quella carta.\n");
				
				if(partita.giocatoreCorrente().possiedeCartaOggetto(comando.toUpperCase())==true)
				{
//					System.out.println("Sembra proprio tu ce l'abbia.");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Sembra proprio tu ce l'abbia.\n");
					
//					System.out.println("Controllo che non hai deciso di usare la carta difesa.");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Controllo che non hai deciso di usare la carta difesa.\n");
					
					if(comando.toLowerCase().equals("difesa"))
					{
//						System.out.println("Attenzione! La carta difesa si attiva solo automaticamente quando vieni attaccato e solo se sei umano.");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione! La carta difesa si attiva solo automaticamente quando vieni attaccato e solo se sei umano.\n");
						
						break;
					}
//					System.out.println("Hai deciso di usare la carta "+comando.toLowerCase());
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai deciso di usare la carta "+comando.toLowerCase()+".\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto "+comando.toLowerCase()+".\n");
					
//					System.out.println("Aziono gli effetti della carta.");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Aziono gli effetti della carta.\n");
					
					ControllerEffettoCarteOggetto controllerEffettoCarteOggetto=new ControllerEffettoCarteOggetto(comando.toUpperCase(),partita);
					CartaOggetto oggetto=partita.giocatoreCorrente().getCartaOggetto(comando.toUpperCase());
					controllerEffettoCarteOggetto.eseguiEffettoCarta(oggetto);
				}
				*/
			}
			break;
		case PASSO:
			if(partita.giocatoreCorrente().getPuoPassare()==false)
			{
//				System.out.println("Non puoi passare! Devi prima muovere.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi passare! Devi prima muovere.\n");
				
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
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
		case CHAT:
//			System.out.println("Scrivi qualcosa nella chat.");
			
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_MESSAGGIO);
//			return "Scrivi qualcosa nella chat.";
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Scrivi qualcosa nella chat.\n");
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			String messaggio=br.readLine();
//			System.out.println("Giocatore "+this.partita.giocatoreCorrente().getIdGiocatore()+" scrive: "+messaggio);
//			break;
		}
		return;
	}
	
	public void inserisciSettoreDestinazione(String settore)
	{
//		System.out.println("Inserisci il settore: ");
//		return "Inserisci settore destinazione";
//		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci settore destinazione.\n");
//		this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_DESTINAZIONE);
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String settore=br.readLine();
		if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==true)
		{
			if(partita.getPartita().getControllerMappa().isScialuppa(settore)==true)
			{
				if(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).getBloccata()==true)
				{
//					System.out.println("La scialuppa � bloccata");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La scialuppa � bloccata.\n");
					return;
				}
				if(partita.getPartita().getControllerMappa().verificaVittoria(settore, partita.giocatoreCorrente().getPosizione(), partita.giocatoreCorrente().getPortata(), partita.giocatoreCorrente().getPersonaggio())==true)
				{
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 0, settore);
//					System.out.println("Ora pesca la carta scialuppa. Se � verde allora riesci a fuggire, altrimenti devi andare verso una nuova scialuppa.");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ora pesca la carta scialuppa. Se � verde allora riesci a fuggire, altrimenti devi andare verso una nuova scialuppa.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" � arrivato alla scialuppa "+settore+".\n");
					
					if(partita.getPartita().getControllerMazzoCarteScialuppa().pesca().getColore()==Colore.VERDE)
					{
//						System.out.println("HAI VINTO!");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai pescato verde. HAI VINTO!\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Ha pescato verde. Ha vinto.\n");
						
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
						return;
					}
//					System.out.println("La carta � rossa, non puoi fuggire da qui");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La carta � rossa, non puoi fuggire da qui.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Ha pescato carta rossa. Non pu� fuggire e la sciluppa risulta bloccata per tutti.\n");
					
					partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).setBloccata(true);
					partita.giocatoreCorrente().setPosizione(settore);
					partita.giocatoreCorrente().setHaMosso(true);
					partita.giocatoreCorrente().setPuoPassare(true);
					return;
				}
			}
			else if(partita.getPartita().getControllerMappa().verificaMossa(settore,  partita.giocatoreCorrente().getPosizione(), partita.giocatoreCorrente().getPortata(),false)==true)
			{
				partita.giocatoreCorrente().setPosizione(settore);
//				System.out.println("Posizione aggiornata!");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Posizione aggiornata.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha fatto la sua mossa.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 0, settore);
				
				partita.giocatoreCorrente().setHaMosso(true);
				partita.giocatoreCorrente().setPuoPassare(true);
				//aggiorno log e posizione giocatore
				//controllo tipo settore e agisco di conseguenza
				if(partita.giocatoreCorrente().getSedativi())
				{
//					System.out.println("Non peschi carte settore perch� hai usato SEDATIVI.");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non peschi carte settore perch� hai usato sedativi.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" non pesca carte settore perch� ha usato sedativi.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 1, "0");
				}
				else if(partita.getPartita().getControllerMappa().settoreSicuro_Pericoloso(settore)=="pericoloso")
				{
//						System.out.println("Pesca la carta settore.");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Pesca la carta settore.\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" pesca la carta settore.\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 1, "1");
						controllerEffettoCarteSettore=new ControllerEffettoCarteSettore(partita);
						try
						{
							controllerEffettoCarteSettore.pescaEdEseguiEffettoCarta();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
				}
				else
				{
//					System.out.println("Non viene pescata nessuna carta.");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non peschi nessuna carta.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Non viene pescata nessuna carta.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 1, "0");
				}
				return;
			}	
		}
//		System.out.println("Input non valido.");
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Input non valido.\n");
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci settore destinazione.\n");
	}
	
	public void scriviMessaggioChat(String messaggio)
	{
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai inviato: "+messaggio+"\n");
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" scrive: "+messaggio+"\n");
	}
	
	public void inserisciCartaOggetto(String oggetto)
	{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String comando=br.readLine();
		
//		System.out.println("Controllo che tu abbia davvero quella carta.");
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Controllo che tu abbia davvero quella carta.\n");
		
		if(partita.giocatoreCorrente().possiedeCartaOggetto(oggetto.toUpperCase())==true)
		{
//			System.out.println("Sembra proprio tu ce l'abbia.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Sembra proprio tu ce l'abbia.\n");
			
//			System.out.println("Controllo che non hai deciso di usare la carta difesa.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Controllo che non hai deciso di usare la carta difesa.\n");
			
			if(oggetto.toLowerCase().equals("difesa"))
			{
//				System.out.println("Attenzione! La carta difesa si attiva solo automaticamente quando vieni attaccato e solo se sei umano.");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione! La carta difesa si attiva solo automaticamente quando vieni attaccato e solo se sei umano.\n");
				
				return;
			}
//			System.out.println("Hai deciso di usare la carta "+comando.toLowerCase());
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai deciso di usare la carta "+oggetto.toLowerCase()+".\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto "+oggetto.toLowerCase()+".\n");
			
//			System.out.println("Aziono gli effetti della carta.");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Aziono gli effetti della carta.\n");
			controllerEffettoCarteOggetto=new ControllerEffettoCarteOggetto(oggetto.toUpperCase(),partita);
			CartaOggetto cOggetto=partita.giocatoreCorrente().getCartaOggetto(oggetto.toUpperCase());
			try
			{
				controllerEffettoCarteOggetto.eseguiEffettoCarta(cOggetto);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void inserisciSettoreLuci(String settore)
	{
		controllerEffettoCarteOggetto.inserisciSettoreLuci(settore);
	}
	
	public void inserisciSettoreRumoreAScelta(String settore)
	{
		controllerEffettoCarteSettore.inserisciSettoreRumoreAScelta(settore);
	}
	
	public void inserisciProprioSettore(String settore)
	{
		controllerEffettoCarteSettore.inserisciProprioSettore(settore);
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
