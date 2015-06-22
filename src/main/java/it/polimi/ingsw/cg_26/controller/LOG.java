package it.polimi.ingsw.cg_26.controller;

public class LOG {
	
	private String[][][] log=new String[8][40][6];
	
	public LOG()
	{
		for(int i=0;i<8;i++)
			for(int j=0;j<40;j++)
				for(int k=0;k<6;k++)
					this.log[i][j][k]="";
	}
	
	public void setLOG(int giocatore, int turno, int livello, String informazione)
	{
		log[giocatore][turno-1][livello]+=informazione;	
	}
	
	public String getLOG(int giocatore, int turno, int livello)
	{
		return log[giocatore][turno-1][livello];
	}
	
	public void azzeraLog(int giocatore, int turno)
	{
		for(int k=0;k<6;k++)
			log[giocatore][turno-1][k]="";
	}
}
