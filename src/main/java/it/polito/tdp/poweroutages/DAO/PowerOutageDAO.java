package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Evento;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Evento> getEventiPerNerc (int id) {
		String sql="SELECT date_event_began, date_event_finished, customers_affected "
				+"FROM poweroutages "
				+"WHERE nerc_id=? "
				+"ORDER BY date_event_began ";
		List <Evento> eventi=new ArrayList<> ();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			while (res.next()) {
	            LocalDateTime inizio=res.getTimestamp("date_event_began").toLocalDateTime();
	            LocalDateTime fine=res.getTimestamp("date_event_finished").toLocalDateTime();
	            int ore=Duration.between(inizio, fine).toHoursPart();
				Evento e= new Evento(inizio, fine, ore, res.getInt("customers_affected"));
				eventi.add(e);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return eventi;
	}

}
