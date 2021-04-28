package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	int worst=0;
	List <Evento> definitiva=null;
	List <Evento> eventi;
	int maxOre;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Evento> worstCase (Nerc n, int maxAnni, int maxOre) {
		eventi=podao.getEventiPerNerc(n.getId());
		List <Evento> parziale=new ArrayList<>();
		this.maxOre=maxOre;
		if (eventi.size()==0)
			return null;
		while (eventi.get(eventi.size()-1).getInizio().getYear()-eventi.get(0).getInizio().getYear()>maxAnni) {
			eventi.remove(eventi.size()-1);
		}
		calcola(parziale, 0, 0, 0);
		List<Evento> definitiva1=new ArrayList<>(definitiva);
		definitiva.clear();
		return definitiva1;
	}
	
	public int getWorst() {
		int worst1=worst;
		worst=0;
		return worst1;
	}
	
	private void calcola (List <Evento> parziale, int livello, int oreRaggiunte, int personeTotali) {
		if (livello==eventi.size() || maxOre==oreRaggiunte) {
			if (personeTotali>worst) {
				worst=personeTotali;
				definitiva=new ArrayList<>(parziale);
			}
			return ;
		}
		
		if (oreRaggiunte>maxOre) 
			return ;
		
		if (personeTotali>worst) {
			worst=personeTotali;
			definitiva=new ArrayList<>(parziale);
		}
		
		 for (Evento e:eventi) {
			if (!parziale.contains(e)) {
				List<Evento> parziale1=new ArrayList<>(parziale);
				parziale1.add(e);
				int oreRaggiunte1=oreRaggiunte+e.getOre();
				int personeTotali1=personeTotali+e.getPersone();
				calcola(parziale1, livello+1, oreRaggiunte1, personeTotali1);
			}
		}
        
	}

}
