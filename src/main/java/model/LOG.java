package model;

public class LOG {
	
	private String[][][] log=new String[8][39][6];
	
	public void setLOG(Giocatore giocatore, int turno, int livello, String informazione)
	{
		log[giocatore.getIdGiocatore()][turno][livello]=informazione;	
	}
	
	public String getLOG(Giocatore giocatore, int turno, int livello)
	{
		return log[giocatore.getIdGiocatore()][turno][livello];
	}

}
