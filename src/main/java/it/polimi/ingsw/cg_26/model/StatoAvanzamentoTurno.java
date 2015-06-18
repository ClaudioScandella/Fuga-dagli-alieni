package it.polimi.ingsw.cg_26.model;

public enum StatoAvanzamentoTurno
{
	ATTESA_COMANDO, ATTESA_SETTORE_DESTINAZIONE, ATTESA_CARTA, ATTESA_MESSAGGIO, ATTESA_SETTORE_LUCI, ATTESA_SETTORE_RUMORE, ATTESA_PROPRIO_SETTORE;
	
	public StatoAvanzamentoTurno nextState(String azione)
	{
		if(azione.equals("mossa"))
		{
			return this.ATTESA_SETTORE_DESTINAZIONE;
		}
		if(azione.equals("carta"))
		{
			return this.ATTESA_CARTA;
		}
		if(azione.equals("chat"))
		{
			return this.ATTESA_MESSAGGIO;
		}
		return this.ATTESA_COMANDO;
	}
}
