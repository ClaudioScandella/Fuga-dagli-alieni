package it.polimi.ingsw.cg_26.model;

import it.polimi.ingsw.cg_26.controller.controllerMazzo.ControllerMazzoOggetti;
import it.polimi.ingsw.cg_26.controller.controllerMazzo.ControllerMazzoScialuppa;
import it.polimi.ingsw.cg_26.controller.controllerMazzo.ControllerMazzoSettori;
import it.polimi.ingsw.cg_26.model.Giocatore.Personaggio;
import it.polimi.ingsw.cg_26.model.mappe.Mappa;

import java.util.ArrayList;

public class Partita {
	
	private int idPartita;
	private int numeroGiocatori;
	private int giro=0; //indica il giocatore 
	private int turno = 0; //numero turni di ogni giocatore
						  //quando tutti i giocatori hanno giocato: giro++
	private String tipoMappa;
	private Mappa mappa;
	private ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	private Object[][][] log = new Object [39][8][3]; //[turno][giocatore][0] = mosse
													  //[turno][giocatore][1] = posizioneDichiarata
													  //[turno][giocatore][2] = haPescatoCarta
	private int numeroUmaniMorti = 0;
	private int numeroAlieniMorti = 0;
	private int numeroUmaniScappati = 0;

	
	
	public Partita(int idPartita, String mappa ){
		this.idPartita = idPartita;
		this.tipoMappa = mappa;
		
		this.mappa=new Mappa(mappa);
		
		ControllerMazzoOggetti controllerMazzoOggetti=new ControllerMazzoOggetti(this, this.mappa);
		ControllerMazzoScialuppa controllerMazzoScialuppa=new ControllerMazzoScialuppa(this);
		ControllerMazzoSettori controllerMazzoSettore=new ControllerMazzoSettori(this);
	}
	
	public int getIdPartita(){
		return idPartita;
	}
	
	public int getNumeroGiocatori(){
		return numeroGiocatori;
	}
	
	public int getTurno(){
		return turno;
	}
	
	public void setTurno(int idGiocatore){
		turno = idGiocatore;
	}
	
	public String getTipoMappa(){
		return tipoMappa;
	}
	
	public void setGiocatori(Giocatore nuovoGiocatore){
		giocatori.add(nuovoGiocatore);
	}
	
	public ArrayList<Giocatore> getGiocatori(){
		return giocatori;
	}
	
	public int getGiro(){
		return giro;
	}
	
	public void setGiro(int giro){
		this.giro = giro;
	}
	
	//setter log
	public void setLog(int turno, int idGiocatore, String spostamento){
		log[turno][idGiocatore][0] = spostamento;
	}
	
	public void setLogPosizioneDichiarata(int turno, int idGiocatore, String posizioneDichiarata){
		log[turno][idGiocatore][1] = posizioneDichiarata;
	}
	
	//pescaCarta è true se il giocatore ha pescato una carta
	public void setLogPescato(int turno, int idGiocatore, String pescaCarta){
		log[turno][idGiocatore][2] = pescaCarta;
	}
	
	public Object getLog(int x, int y, int z){
		return log [x][y][z];
	}
	
	//public Object posizioneDichiarata(int turno, int idGiocatore){
		//return log[turno][idGiocatore][1];
	//}
	
//	public Object haPescato(int turno, int idGiocatore){
//		return log[turno][idGiocatore][2];
//	}
	
	public int getNumeroUmaniMorti(){
		return numeroUmaniMorti;
	}
	
	public void setNumeroUmaniMorti(int umaniUccisi){
		numeroUmaniMorti += umaniUccisi;
	}
	
	public int getNumeroAlieniMorti(){
		return numeroAlieniMorti;
	}
	
	public void setNumeroAlieniMorti(int alieniUccisi){
		numeroAlieniMorti += alieniUccisi;
	}
	
	public int getNumeroUmaniScappati(){
		return numeroUmaniScappati;
	}
	
	public void scappa(){
		numeroUmaniScappati++;
	}
	
	public void start()
	{
		//ruoli e posizioni assegnate
		for(int i=0;i<giocatori.size();i++)
		{
			if(giocatori.get(i).getIdGiocatore()%2==0)
			{
				giocatori.get(i).setPersonaggio(Personaggio.ALIENO);
				giocatori.get(i).setPosizione(mappa.getPartenzaAlieni());
			}
			else
			{
				giocatori.get(i).setPersonaggio(Personaggio.UMANO);
				giocatori.get(i).setPosizione(mappa.getPartenzaUmani());
			}
		}
	}
	
	public void finePartita()
	{
		
	}

	public Mappa getMappa() {
		return this.mappa;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
