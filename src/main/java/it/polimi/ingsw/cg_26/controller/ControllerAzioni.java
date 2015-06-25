package it.polimi.ingsw.cg_26.controller;

import it.polimi.ingsw.cg_26.model.Giocatore;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.ModelPartita.StatoPescaOggetto;
import it.polimi.ingsw.cg_26.model.StatoAvanzamentoTurno;
import it.polimi.ingsw.cg_26.model.carte.CartaOggetto;
import it.polimi.ingsw.cg_26.model.carte.CartaScialuppa.Colore;

import java.io.IOException;
import java.util.ArrayList;

/**
 * La classe ControllerAzioni gestisce le azioni che un giocatore può effettuare:
 * si occupa infatti di verificare se le condizioni per eseguire l'azione
 * sono verificate e, in caso affermativo, le esegue.
 * 
 * Le possibili azioni sono:
 * - attacco (per attaccare nel proprio settore), 
 * - mossa (per spostarsi di uno o più settori, in base alla portata del personaggio), 
 * - carta (per usare una carta oggetto posseduta), 
 * - passo (per passare il turno al giocatore successivo), 
 * - pesco oggetto (per pescare una carta oggetto -obbligatorio prima di passare nel caso 
 * in cui la carta settore eventualmente pescata contenga l'oggetto-). 
 * 
 * @author Claudio e Patrizia
 *
 */
public class ControllerAzioni
{
	private enum Azione{ATTACCO, MOSSA, CARTA, PASSO, OGGETTO;}
	private Azione azione=null;
	private ControllerPartita partita;
	private ControllerEffettoCarteOggetto controllerEffettoCarteOggetto;
	private ControllerEffettoCarteSettore controllerEffettoCarteSettore;

	/**
	 * Costruisce il controller dell'azione desiderata assegnando un oggetto di
	 * tipo ControllerPartita alla variabile partita e, dopo aver modificato la
	 * stringa contenente il nome dell'azione da eseguire in modo tale da rendere 
	 * tutte le lettere minuscole, assegna alla variabile this.azione il tipo di 
	 * azione passato in ingresso.
	 * Nel caso in cui l'azione passata in ingresso non sia valida, inserisce nel log
	 * la stringa "Non hai inserito un comando valido.".
	 * 
	 * @param azione è il tipo di azione che deve essere eseguita
	 * @param partita è il controller della partita
	 */
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
			case "pesco oggetto":
				this.azione=Azione.OGGETTO;
				break;
			default:
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), 
						this.partita.getPartita().getNumeroTurno(),5, "Non hai inserito un comando valido.\n");
				break;
		}
	}

	/**
	 * Se l'azione non è valida, e quindi il valore della variabile azione è null, il metodo termina.
	 * In caso contrario, il metodo controlla le condizioni ed eventualmente esegue gli 
	 * effetti dell'azione:
	 * - attacco: controlla che il personaggio possa attaccare e che non abbia già attaccato, nel caso
	 * il personaggio sia alieno verifica anche che abbia già mosso. Se queste condizioni non sono 
	 * verificate il metodo termina, altrimenti uccide tutti i personaggi presenti in quel settore
	 * (ad eccezione di se stesso e dell'eventuale umano che possiede la carta oggetto difesa).
	 * - mossa: controlla che il giocatore non abbia già mosso e cambia la variabile statoAvanzamentoTurno
	 * della partita in ATTESA_SETTORE_DESTINAZIONE.
	 * - carta: controlla che il personaggio non sia alieno. Se il personaggio è umano cambia la 
	 * variabile statoAvanzamentoTurno della partita in ATTESA_CARTA.
	 * - passo: controlla che la variabile statoPescaOggetto della partita non sia DEVE_PESCARE e che il
	 * giocatore possa passare (questo è possibile solo se ha già effettuato una mossa), dopodiché
	 * aggiorna le variabili del giocatore:
	 * puoAttaccare, haMosso, haPassato, haAttaccato (rispettivamente in false, false, true, false) e 
	 * se umano portata, puoAttaccare, sedativi (rispettivamente in 1,false,false). Cambia lo
	 * statoAvanzamentoTurno della partita in ATTESA_COMANDO.
	 * - oggetto: chiama il metodo pescaOggetto() presente in questa classe.
	 *
	 * @throws IOException
	 */
	public void agisci() throws IOException
	{
		if(this.azione==null)
			return;
		switch(azione)
		{
		case ATTACCO:
			if(!partita.giocatoreCorrente().getPuoAttaccare())
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione sei un umano, puoi attaccare solo con la carta ATTACCO.\n");
				return;
			}
			if(partita.giocatoreCorrente().getHaAttaccato())
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi attaccare! Lo hai gia fatto in questo turno.\n");
				return;
			}
			if(partita.giocatoreCorrente().getPersonaggio().equals(Personaggio.ALIENO))
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Verifico se hai gia mosso e quindi puoi attaccare.\n");
				if(partita.giocatoreCorrente().getHaMosso()==false)
				{
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi attaccare! Devi muovere prima di farlo.\n");
					return;
				}
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "OK puoi attaccare.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha attaccato in settore "+this.partita.giocatoreCorrente().getPosizione()+".\n");
			}
			partita.giocatoreCorrente().setHaAttaccato(true);
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Verifico se hai ucciso qualcuno.\n");
			String settoreDiAttacco=partita.giocatoreCorrente().getPosizione();
			ArrayList<Giocatore> listaGiocatoriAttaccati=new ArrayList<>();
			Giocatore giocatoreSalvatoConDifesa=null;
			listaGiocatoriAttaccati.addAll(partita.getGiocatoriInSettore(settoreDiAttacco));
			listaGiocatoriAttaccati.remove(partita.giocatoreCorrente());
			if(listaGiocatoriAttaccati.size()>0)
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai ucciso qualcuno.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha ucciso qualcuno.\n");
				partita.giocatoreCorrente().setHaUcciso(true);
				for(Giocatore giocatore : listaGiocatoriAttaccati)
				{
					if(giocatore.possiedeCartaOggetto("DIFESA")==true && giocatore.getPersonaggio().equals(Personaggio.UMANO)==true)
					{
						giocatoreSalvatoConDifesa=giocatore;
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, giocatore.getNomeUtente()+" si � salvato con la carta difesa, quindi � UMANO.\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, giocatore.getNomeUtente()+" si � salvato con la carta difesa, quindi � UMANO.\n");
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
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai ucciso:");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Sono stati uccisi:");
				for(Giocatore giocatore : listaGiocatoriAttaccati)
				{
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, " "+giocatore.getNomeUtente()+"("+giocatore.getPersonaggio().name()+")");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, " "+giocatore.getNomeUtente()+"("+giocatore.getPersonaggio().name()+")");
				}
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, ".\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, ".\n");
			}
			else
			{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non hai ucciso nessuno.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Non � stato ucciso nessuno.\n");
			}
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
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi muovere! Hai gia mosso in questo turno.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "E' il tuo turno. Inserisci il tuo comando.\n");

				return;
			}
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_SETTORE_DESTINAZIONE);
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci settore destinazione.\n");
			break;
		case CARTA:
			if(partita.giocatoreCorrente().getPersonaggio()==Personaggio.ALIENO)
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione! Sei un alieno! Solo gli umani possono usare le carte oggetto.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ti mostro comunque le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto()+".\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "E' il tuo turno. Inserisci il tuo comando.\n");
				break;
			}
			else
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ti mostro le carte che possiedi: "+this.partita.giocatoreCorrente().stampaCarteOggetto()+".\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Scrivi il nome dell'oggetto che vuoi usare. Non puoi usare carta difesa.\n");
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_CARTA);
			}
			break;
		case PASSO:
			if(this.partita.getPartita().getStatoPescaOggetto().equals(StatoPescaOggetto.DEVE_PESCARE))
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Devi prima pescare un oggetto.\n");
				return;
			}
			if(partita.giocatoreCorrente().getPuoPassare()==false)
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non puoi passare! Devi prima muovere.\n");
				return;
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
			return;
		case OGGETTO:
			this.pescaOggetto();
			break;
		}
		return;
	}
	/**
	 * Modifica la stringa passata in ingresso contenente il nome del settore
	 * scrivendola tutta in lettere mauscole e verifica l'esistenza di tale settore.
	 * Se il settore esiste: 
	 *  - se è un settore scialuppa bloccato, il metodo termina. Se è un settore scialuppa
	 *  ma non è bloccato: controlla che il giocatore sia umano e che la mossa sia valida 
	 *  grazie al metodo verificaVittoria del ControllerMappa, in caso positivo pesca 
	 *  pesca una carta settore, che può essere verde (in tal caso il personaggio ha vinto
	 *  e la scialuppa viene bloccata) oppure rossa (in tal caso la scialuppa è bloccata 
	 *  ed il personaggio non può fuggire).
	 *  - se il settore rappresenta un settore di destinazione valido (è un settore su cui
	 *  il personaggio può fermarsi ed è raggiungibile dalla posizione in cui si trova inizialmente)
	 *  aggiorna la posizione e i parametri haMosso e puoPassare. Controlla se il giocatore ha
	 *  precedentemente deciso di usare la carta oggetto sedativi (attraverso il valore della 
	 *  variabile sedativi del personaggio): in caso positivo non fa pescare la carta settore,
	 *  altrimenti se il settore destinazione è "pericoloso" il giocatore pesca la carta settore
	 *  e ne risolve gli effetti (se la carta possiede un oggetto, aggiorna la variabile
	 *  statoPescaOggetto in DEVE_PESCARE). Se il settore destinazione non è "pericoloso"
	 *  il personaggio non pesca alcuna carta.
	 *  
	 * @param settore è il settore in cui il giocatore vuole spostarsi
	 */
	public void inserisciSettoreDestinazione(String settore)
	{
		settore=settore.toUpperCase();
		if(partita.getPartita().getControllerMappa().verificaEsistenzaSettore(settore)==true)
		{
			if(partita.getPartita().getControllerMappa().isScialuppa(settore)==true)
			{
				if(partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).getBloccata()==true)
				{
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La scialuppa � bloccata.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "E' il tuo turno. Inserisci il tuo comando.\n");
					this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
					return;
				}
				if(partita.getPartita().getControllerMappa().verificaVittoria(settore, partita.giocatoreCorrente().getPosizione(), partita.giocatoreCorrente().getPortata(), partita.giocatoreCorrente().getPersonaggio())==true)
				{
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Ora pesca la carta scialuppa. Se � verde allora riesci a fuggire, altrimenti devi andare verso una nuova scialuppa.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" � arrivato alla scialuppa "+settore+". E' un UMANO!\n");
					if(partita.getPartita().getControllerMazzoCarteScialuppa().pesca().getColore()==Colore.VERDE)
					{
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai pescato verde. HAI VINTO!\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Ha pescato verde. Ha vinto.\n");
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
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "E' il tuo turno. Inserisci il tuo comando.\n");
						this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
						return;
					}
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La carta � rossa, non puoi fuggire da qui.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Ha pescato carta rossa. Non pu� fuggire e la sciluppa risulta bloccata per tutti.\n");
					partita.getPartita().getControllerMappa().getMappa().getListaSettoriTotali().get(partita.getPartita().getControllerMappa().convertitoreStringa_Indice(settore)).setBloccata(true);
					partita.giocatoreCorrente().setPosizione(settore);
					partita.giocatoreCorrente().setHaMosso(true);
					partita.giocatoreCorrente().setPuoPassare(true);
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "E' il tuo turno. Inserisci il tuo comando.\n");
					this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
					return;
				}
			}
			else if(partita.getPartita().getControllerMappa().verificaMossa(settore,  partita.giocatoreCorrente().getPosizione(), partita.giocatoreCorrente().getPortata(),false)==true)
			{
				partita.giocatoreCorrente().setPosizione(settore);
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Posizione aggiornata.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha fatto la sua mossa.\n");
				partita.giocatoreCorrente().setHaMosso(true);
				partita.giocatoreCorrente().setPuoPassare(true);
				if(partita.giocatoreCorrente().getSedativi())
				{
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non peschi carte settore perch� hai usato sedativi.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" non pesca carte settore perch� ha usato sedativi.\n");
				}
				else if(partita.getPartita().getControllerMappa().settoreSicuro_Pericoloso(settore)=="pericoloso")
				{
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Pesca la carta settore.\n");
						this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" pesca la carta settore.\n");
						controllerEffettoCarteSettore=new ControllerEffettoCarteSettore(partita);
						try
						{
							if(controllerEffettoCarteSettore.pescaEdEseguiEffettoCarta())
							{
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "La carta settore conteneva un oggetto. Ricordati di pescarlo prima di passare.\n");
								this.partita.getPartita().setStatoPescaOggetto(StatoPescaOggetto.DEVE_PESCARE);
								return;
							}
							else
							{
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta senza oggetto.\n");
								this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Carta senza oggetto.\n");
							}
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
				}
				else
				{
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non peschi nessuna carta.\n");
					this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Non viene pescata nessuna carta.\n");
				}
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "E' il tuo turno. Inserisci il tuo comando.\n");
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
				return;
			}	
		}
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Input non valido.\n");
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Inserisci settore destinazione.\n");
	}
	
	/**
	 * Controlla che il giocatore possieda una carta del tipo passato in ingresso e che non sia "difesa"
	 * ed eventualmente crea un ControllerCartaOggetto, che esegue gli effetti della carta.
	 * @param oggetto è la stringa contenente il tipo di oggetto che il giocatore vorrebbe usare
	 */
	public void inserisciCartaOggetto(String oggetto)
	{
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Controllo che tu abbia davvero quella carta.\n");
		if(partita.giocatoreCorrente().possiedeCartaOggetto(oggetto.toUpperCase())==true)
		{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Sembra proprio tu ce l'abbia.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Controllo che non hai deciso di usare la carta difesa.\n");
			if(oggetto.toLowerCase().equals("difesa"))
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione! La carta difesa si attiva solo automaticamente quando vieni attaccato e solo se sei umano.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "E' il tuo turno. Inserisci il tuo comando.\n");
				this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
				return;
			}
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Hai deciso di usare la carta "+oggetto.toLowerCase()+".\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" usa la carta oggetto "+oggetto.toLowerCase()+". E' un UMANO!\n");
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
		else
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Non hai o non esiste quella carta.\n");
		this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_COMANDO);
	}
	
	/**
	 * Permette di pescare una carta oggetto.
	 * Se il mazzo di carte oggetto è vuoto, chiama il metodo rigeneraMazzo() del ControllerMazzocarteOggetto,
	 * che rigenera il mazzo prendendo le carte dal mazzo degli scarti. Nel caso in cui anche il mazzo 
	 * degli scarti sia vuoto (ciò significa che tutte le carte oggetto sono in possesso dei giocatori), 
	 * il giocatore non pesca la carta oggetto.
	 * 
	 * Se il mazzo non è vuoto o è stato rigenerato, pesca una carta oggetto e pone lo statoPescaOggetto della
	 * partita a NON_DEVE_PESCARE. Nel caso in cui il giocatore possieda già 3 carte (quella pescata
	 * risulta essere la quarta), pone lo statoAvanzamentoTurno della partita a ATTESA_USA_O_SCARTA).
	 */
	public void pescaOggetto()
	{
	if(partita.getPartita().getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo()==0)
		{
			partita.getPartita().getControllerMazzoCarteOggetto().rigeneraMazzo();
			if(partita.getPartita().getControllerMazzoCarteOggetto().numeroCarteOggettoNelMazzo()==0)
			{
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Mazzo oggetti vuoto: nessuna carta pescata.\n");
				this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Mazzo oggetti vuoto: nessuna carta pescata.\n");
				this.partita.getPartita().setStatoPescaOggetto(StatoPescaOggetto.NON_DEVE_PESCARE);
				return;
			}
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Mazzo oggetti rigenerato dagli scarti.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, "Mazzo oggetti rigenerato dagli scarti.\n");
		}
		CartaOggetto cartaOggetto=partita.getPartita().getControllerMazzoCarteOggetto().pescaCartaOggetto();
		partita.giocatoreCorrente().setCartaOggetto(cartaOggetto);
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Carta pescata: "+cartaOggetto.getTipoOggetto().name()+".\n");
		this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 4, this.partita.giocatoreCorrente().getNomeUtente()+" ha pescato una carta oggetto.\n");
		this.partita.getPartita().setStatoPescaOggetto(StatoPescaOggetto.NON_DEVE_PESCARE);
		if(partita.giocatoreCorrente().getCarteOggetto().size()==4)
		{
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "Attenzione! Hai 4 oggetti, usane o scartane uno.\n");
			this.partita.getLog().setLOG(this.partita.getPartita().getNumeroGiocatoreCorrente(), this.partita.getPartita().getNumeroTurno(), 5, "\"uso\" per usare; \"scarta\" per scartare\n");
			this.partita.getPartita().setStatoAvanzamentoTurno(StatoAvanzamentoTurno.ATTESA_USA_O_SCARTA);
		}
	}
	
	/**
	 * Chiama il metodo inserisciSettoreLuci() del controllerEffettoCarteOggetto,
	 * che si occuperà di illuminare il settore passatogli.
	 * 
	 * @param settore è il settore che si vuole illuminare.
	 */
	public void inserisciSettoreLuci(String settore)
	{
		controllerEffettoCarteOggetto.inserisciSettoreLuci(settore);
	}
	
	/**
	 * Chiama il metodo inserisciSettoreRumoreAScelta() del controllerEffettoCarteSettore,
	 * che si occuperà di verificare l'esistenza del settore ed aggiornare il Log del ControllerPartita,
	 * per poter successivamente eseguire gli effetti della carta RUMOREaSCELTA.
	 * 
	 * @param settore è il settore in cui il giocatore dichiara di essere
	 */
	public void inserisciSettoreRumoreAScelta(String settore)
	{
		controllerEffettoCarteSettore.inserisciSettoreRumoreAScelta(settore);
	}
	
	/**
	 * Chiama il metodo inserisciProprioSettore() del controllerEffettoCarteSettore,
	 * che si occuperà di verificare che il personaggio si trovi effettivamente nel
	 * settore in cui dichiara di essere e di aggiornare il Log del ControllerPartita,
	 * per poter successivamente eseguire gli effetti della carta RUMOREproprioSETTORE.
	 * 
	 * @param settore è il settore in cui il giocatore si trova
	 */
	public void inserisciProprioSettore(String settore)
	{
		controllerEffettoCarteSettore.inserisciProprioSettore(settore);
	}
	
	/**
	 * Chiama il metodo usaOscarta() del ControllerEffettoCarteSettore, 
	 * che si occuperà di eseguire gli effetti del comando passatogli in
	 * ingresso.
	 * Questo metodo viene chiamato quando il giocatore possiede già tre carte oggetto 
	 * e ne ha pescata una quarta: deve quindi decidere se usarne una oppure 
	 * scartare.
	 * 
	 * @param comando è la stringa contenente la scelta del giocatore: "uso" o "scarto"
	 */
	public void usaOscarta(String comando)
	{
		this.controllerEffettoCarteSettore.usaOscarta(comando);
	}
	
	/**
	 * Chiama il metodo scartaOggett() del ControllerEffettoCarteSettore, 
	 * che si occuperà di scartare l'oggetto del tipo specificato in ingresso
	 * 
	 * @param comando è il tipo della carta oggetto che deve essere scartata
	 */
	public void scartaOggetto(String comando)
	{
		this.controllerEffettoCarteSettore.scartaOggetto(comando);
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
