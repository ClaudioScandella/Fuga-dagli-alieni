package it.polimi.ingsw.cg_26.model;

/**
 * 
 * Contiene l'enumerazione GameState. 
 * La partita può essere in una di queste tre fasi:
 * - inizializzazione: la partita non è ancora iniziata, ma è già stata inizializzata
 * - running: la partita è iniziata ed è in esecuzione
 * - finegioco: la partita è arrivata alla conclusione e devono essere determinati 
 *   vincitori e perdenti
 *   
 * @author Claudio e Patrizia
 *
 */
public enum GameState
{
	INIZIALIZZAZIONE, RUNNING, FINEGIOCO;
}
