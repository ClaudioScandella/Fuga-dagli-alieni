package it.polimi.ingsw.cg_26;

import java.util.*;

public abstract class Settore
{
	private String nome;
	//private String coordinata;
	//private enum tipoDiSettore{VUOTO,PERICOLOSO,SICURO,PARTENZA_ALIENI,PARTENZA_UMANI,SCIALUPPA1,SCIALUPPA2,SCIALUPPA3,SCIALUPPA4}
	//private List<Settore> settoriAdiacenti;
	
	
	public Settore(String nome)
	{
		this.nome=nome;
	}
	
	public String getNome() {
		// TODO Auto-generated method stub
		return nome;
	}
	
	
	
	public ArrayList trovaSettoriAdiacenti(String nome)
	{
		//traduttore nome-->indice
		return null;
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
