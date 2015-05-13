package it.polimi.ingsw.cg_26;

import java.util.*;
import java.io.*;

public class MappaGalilei {
	
	public MappaGalilei()
	{
		File f;
		f=new File("C:\\Users\\Claudio\\Desktop\\provaaaa.txt");
		
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<6; i++)
		{
			int a;
			try {
				a = fr.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(a);
		}
		
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
