package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		PowerOutageDAO podao=new PowerOutageDAO();
		System.out.println(podao.getEventiPerNerc(4));

	}

}
