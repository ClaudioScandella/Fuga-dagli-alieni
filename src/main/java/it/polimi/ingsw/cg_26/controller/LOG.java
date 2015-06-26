package it.polimi.ingsw.cg_26.controller;

/**
 * 
 * Contiene la classe LOG, che permette di creare oggetti di tipo LOG in cui
 * vengono salvate le informazioni riguardanti lo svolgimento della partita.
 * 
 * @author Claudio e Patrizia
 *
 */
public class LOG {
	
	private String[][][] log=new String[8][40][6];

//COSTRUTTORE
	public LOG()
	{
		for(int i=0;i<8;i++)
			for(int j=0;j<40;j++)
				for(int k=0;k<6;k++)
					this.log[i][j][k]="";
	}

	/**
	 * Permette di inserire le informazioni all'interno del LOG.
	 * 
	 * @param giocatore indica il giocatore a cui si riferisce l'informazione contenuta
	 * @param turno indica il turno a cui si riferisce l'informazione contrnuta
	 * @param livello indica la visibilità dell'infomazione contenuta:
	 * 			livello 4 se l'informazione è pubblica
	 * 			livello 5 se l'informazione è privata
	 * @param informazione : comando, scelta, richiesta, indicazione
	 */
	public void setLOG(int giocatore, int turno, int livello, String informazione)
	{
		log[giocatore][turno-1][livello]+=informazione;	
	}

//GETTER
	public String getLOG(int giocatore, int turno, int livello)
	{
		return log[giocatore][turno-1][livello];
	}
	
	/**
	 * Permette di cancellare tutte le informazioni relative ad un dato
	 * giocatore e un dato turno.
	 * 
	 * @param giocatore
	 * @param turno
	 */
	public void azzeraLog(int giocatore, int turno)
	{
		for(int k=0;k<6;k++)
			log[giocatore][turno-1][k]="";
	}
}
