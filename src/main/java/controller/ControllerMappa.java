package controller;

import java.util.ArrayList;

import model.Giocatore.Personaggio;
import model.mappa.Mappa;
import model.mappa.Settore;

public class ControllerMappa
{
	private Mappa mappa;
	
	public ControllerMappa(Mappa mappa)
	{
		this.mappa=mappa;
	}
	
//	--------------------------------------------------------------------------------------------------

	public String getPartenzaAlieni()
	{
		for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
		{
			if(mappa.getListaSettoriTotali().get(i).getNomeSupplementare()=="Partenza Alieni")
				return mappa.getListaSettoriTotali().get(i).getNome();
		}
		return null;
	}

	public String getPartenzaUmani()
	{
		for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
		{
			if(mappa.getListaSettoriTotali().get(i).getNomeSupplementare()=="Partenza Umani")
				return mappa.getListaSettoriTotali().get(i).getNome();
		}
		return null;
	}
	
	public ArrayList<Settore> getSettoriScialuppa()
	{
		ArrayList<Settore> listaScialuppe=new ArrayList<Settore>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Scialuppa 1" ||
					settore.getNomeSupplementare()=="Scialuppa 2" ||
					settore.getNomeSupplementare()=="Scialuppa 3" ||
					settore.getNomeSupplementare()=="Scialuppa 4")
				listaScialuppe.add(settore);
		}
		return listaScialuppe;
	}
	
	public ArrayList<Settore> getSettoriVuoti()
	{
		ArrayList<Settore> listaSettoriVuoti=new ArrayList<>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore vuoto")
				listaSettoriVuoti.add(settore);
		}
		return listaSettoriVuoti;
	}
	
	public Mappa getMappa()
	{
		return this.mappa;
	}
	
	public ArrayList<Settore> getSettoriSicuri()
	{
		ArrayList<Settore> listaSettoriSicuri=new ArrayList<>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore sicuro")
				listaSettoriSicuri.add(settore);
		}
		return listaSettoriSicuri;
	}
	
	public ArrayList<Settore> getSettoriPericolosi()
	{
		ArrayList<Settore> listaSettoriPericolosi=new ArrayList<>();
		for(Settore settore : mappa.getListaSettoriTotali())
		{
			if(settore.getNomeSupplementare()=="Settore pericoloso")
				listaSettoriPericolosi.add(settore);
		}
		return listaSettoriPericolosi;
	}
	
//	--------------------------------------------------------------------------------------------------
	
	public int numeroScialuppeBloccate()
	{
		int contatoreScialuppeBloccate=0;
		for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
		{
			if(mappa.getListaSettoriTotali().get(i).getBloccata()==true)
				contatoreScialuppeBloccate++;
		}
		return contatoreScialuppeBloccate;
	}
	
	public boolean verificaEsistenzaSettore(String settore)
	{
		if(settore.length()!=3)
		{
			for(int i=0;i<mappa.getListaSettoriTotali().size();i++)
			{
				if(settore==mappa.getListaSettoriTotali().get(i).getNomeSupplementare())
					return true;
			}
		}
		else
		{
			if(settore.charAt(0)>='A' && settore.charAt(0)<='W')
			{
				if(settore.charAt(1)=='0')
					if(settore.charAt(2)>='1' && settore.charAt(2)<='9')
						return true;
				if(settore.charAt(1)=='1')
					if(settore.charAt(2)>='0' && settore.charAt(2)<='4')
						return true;
			}
		}
		System.out.println("Settore inesistente.");
		return false;
	}
	
	public boolean verificaMossa(String settoreDestinazione, String settorePartenza, int portata, boolean scialuppa)
	{
		if(settoreDestinazione.equals(settorePartenza))
			return false;
		int indiceSettoreDestinazione=this.convertitoreStringa_Indice(settoreDestinazione);
		int indiceSettorePartenza=this.convertitoreStringa_Indice(settorePartenza);
		
		ArrayList<Settore> settoriRaggiungibili=new ArrayList<>();
		ArrayList<Settore> settoriNonTransitabili=new ArrayList<>();
		
		settoriNonTransitabili.add(mappa.getListaSettoriTotali().get(this.convertitoreStringa_Indice(this.getPartenzaAlieni())));
		settoriNonTransitabili.add(mappa.getListaSettoriTotali().get(this.convertitoreStringa_Indice(this.getPartenzaUmani())));
		settoriNonTransitabili.addAll(this.getSettoriVuoti());
		settoriNonTransitabili.addAll(this.getSettoriScialuppa());
		
		//salvo in settoriRaggiungibili tutti i settori raggiungibili dal settore partenza con una specifica portata
		settoriRaggiungibili.addAll(this.settoriAdiacenti(settorePartenza));
		settoriRaggiungibili.removeAll(settoriNonTransitabili);
		portata--;
		while(portata>0)
		{
			int lunghezzaSettoriRaggiungibili=settoriRaggiungibili.size();
			for(int i=0;i<lunghezzaSettoriRaggiungibili;i++)
				settoriRaggiungibili.addAll(this.settoriAdiacenti(settoriRaggiungibili.get(i).getNome()));
			settoriRaggiungibili.removeAll(settoriNonTransitabili);
			portata--;
		}
		if(scialuppa)
			settoriRaggiungibili.addAll(getSettoriScialuppa());
		if(settoriRaggiungibili.contains(mappa.getListaSettoriTotali().get(indiceSettoreDestinazione)))
			return true;
		
		//stessa cosa della riga qui sopra: (penso)
//		for(int i=0;i<settoriRaggiungibili.size();i++)
//		{
//			if(settoriRaggiungibili.get(i).getNome()==settoreDestinazione || settoriRaggiungibili.get(i).getNomeSupplementare()==settoreDestinazione)
//				return true;
//		}
		
		return false;
	}
	
	public ArrayList<Settore> settoriAdiacenti(String settore)
	{
		if(this.verificaEsistenzaSettore(settore))
		{
			int indiceSettore=this.convertitoreStringa_Indice(settore);
			return mappa.getMappa().get(mappa.getListaSettoriTotali().get(indiceSettore));
		}
		return null;
	}
	
	public int convertitoreStringa_Indice(String nome)
	{
		int lettera=nome.charAt(0)-65;
		int numero=((nome.charAt(1)-48)*10+nome.charAt(2)-48)-1;
		int indiceSettore=lettera+(numero*23);
		return indiceSettore;
	}
	
	public boolean verificaVittoria(String settoreDestinazione, String settorePartenza, int portata, Personaggio personaggio)
	{
		if(personaggio==Personaggio.ALIENO)
			return false;
		if(verificaMossa(settoreDestinazione, settorePartenza, portata, true)==true)
			return true;
		return false;
	}
	
	public boolean isScialuppa(String settore)
	{
		if(this.getSettoriScialuppa().contains(mappa.getListaSettoriTotali().get(this.convertitoreStringa_Indice(settore))))
			return true;
		return false;
	}
	
	public String settoreSicuro_Pericoloso(String nomeSettore)
	{
		int indiceNomeSettore=this.convertitoreStringa_Indice(nomeSettore);
		if(this.mappa.getListaSettoriTotali().get(indiceNomeSettore).getNomeSupplementare()=="Settore pericoloso")
			return "pericoloso";
		return "sicuro";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
