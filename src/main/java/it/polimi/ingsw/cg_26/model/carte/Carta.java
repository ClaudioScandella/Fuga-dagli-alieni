package it.polimi.ingsw.cg_26.model.carte;

public abstract class Carta
{
	private String tipo;
	
	public Carta(String tipo){
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return tipo;
	}
	
}
