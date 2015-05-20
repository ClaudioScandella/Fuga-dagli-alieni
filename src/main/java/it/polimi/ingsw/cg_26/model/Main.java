package it.polimi.ingsw.cg_26.model;


public class Main {
	
	public static void main(String[] args){

		final Giocatore g1 = new Giocatore ("Patrizia",1234);
		final Giocatore g2 = new Giocatore ("Noemi", 4321);

		
		System.out.println("giocatore1:"+g1.getIdGiocatore());
		System.out.println("giocatore2:"+g2.getIdGiocatore());
	}
}
