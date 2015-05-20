package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.model.mappe.*;

import java.util.*;

public class Main {

	private static ArrayList<Settore> lista=new ArrayList<>();
	
	public static void main(String[] args)
	{
		final MappaGalilei mappa=new MappaGalilei();
		
//		System.out.println(""+MappaGalilei.);
		
		lista=mappa.settoriAdiacenti("C08");
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getNomeSupplementare()!=null)
				System.out.println(""+lista.get(i).getNomeSupplementare());
			else
				System.out.println(""+lista.get(i).getNome());
//		System.out.println(mappa.mossaValida(74,"F04"));
//		MazzoScialuppa mazzoScialuppa=new MazzoScialuppa();
//		mazzoScialuppa.mostraMazzo();
//		mazzoScialuppa.mischia();
//		mazzoScialuppa.mostraMazzo();
//		MazzoOggetti mazzoOggetti=new MazzoOggetti();
//		mazzoOggetti.mischia();
//		mazzoOggetti.mostraMazzo();
//		MazzoSettori mazzoSettori=new MazzoSettori();
//		mazzoSettori.mischia();
//		mazzoSettori.mostraMazzo();
		
		
		
		
		
	}

}
