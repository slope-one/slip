package one.slope.slip.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

@Table(name = "packet")
public class Packet {
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;
	
	@Column(name = "length")
	public int length;
	
	@Column(name = "event_id")
	public int event_id;
	
	@Column(name = "created", insertable = false, updatable = false)
	public Date created;
	
	@Column(name = "modified", insertable = false, updatable = false)
	public Date modified;
	
	public Packet() {
		
	}
	
	public Event event(Database db) {
		return db.where("id = ?", this.event_id).results(Event.class).get(0);
	}
}
