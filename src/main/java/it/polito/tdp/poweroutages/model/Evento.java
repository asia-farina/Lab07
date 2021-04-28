package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class Evento {
	LocalDateTime inizio;
	LocalDateTime fine;
	int ore;
	int persone;
	
	public Evento(LocalDateTime inizio, LocalDateTime fine, int ore, int persone) {
		super();
		this.inizio = inizio;
		this.fine = fine;
		this.ore = ore;
		this.persone = persone;
	}

	@Override
	public String toString() {
		return inizio +" "+ fine +" "+ ore +" "+ persone;
	}

	public LocalDateTime getInizio() {
		return inizio;
	}

	public LocalDateTime getFine() {
		return fine;
	}

	public int getOre() {
		return ore;
	}

	public int getPersone() {
		return persone;
	}
	
	
	

}
