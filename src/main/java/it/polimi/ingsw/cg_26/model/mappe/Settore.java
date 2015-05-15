package it.polimi.ingsw.cg_26.model.mappe;

import java.util.ArrayList;

public  class Settore
{
	private int[] coordinate = new int[2];
	ArrayList<Settore> settoriVicini = new ArrayList<Settore>(); 
	
	public Settore(int x,int y){
		coordinate[0] = x;
		coordinate[1] = y;
	}
	
	public int getCoordinataX(){
		return coordinate[0];
	}
	
	public void setCoordinataX(int x){
		coordinate[0] = x;
	}
	
	public int[] getCoordinate(){
		return coordinate;
	}
	
	public int getCoordinataY(){
		return coordinate[1];
	}
	
	public void setCoordinataY(int y){
		coordinate[1] = y;
	}
	
	//metodo per trovare i vicini
	//getter trovaSettoriVicini

}
