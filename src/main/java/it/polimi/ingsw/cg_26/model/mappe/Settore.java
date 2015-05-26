package it.polimi.ingsw.cg_26.model.mappe;

//import java.util.*;

//import model.mappe.Settore;

public abstract class Settore
{
	private String nome;
	private String nomeSupplementare;
	//private String coordinata;
	//private enum tipoDiSettore{VUOTO,PERICOLOSO,SICURO,PARTENZA_ALIENI,PARTENZA_UMANI,SCIALUPPA1,SCIALUPPA2,SCIALUPPA3,SCIALUPPA4}
	//private List<Settore> settoriAdiacenti;
	
	
	public Settore(String nome)
	{
		this.nome=nome;
	}
	
	public Settore(String nome, String nomeSupplementare)
	{
		this.nome=nome;
		this.nomeSupplementare=nomeSupplementare;
	}
	
	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}
	
	public String getNomeSupplementare() {
		// TODO Auto-generated method stub
		return nomeSupplementare;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Settore other = (Settore) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
