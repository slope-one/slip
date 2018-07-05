package one.slope.slip.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

@Table(name = "event")
public class Event {
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;
	
	@Column(name = "type_id")
	public int type_id;
	
	@Column(name = "name", unique = true)
	public String name;
	
	@Column(name = "created", insertable = false, updatable = false)
	public Date created;
	
	@Column(name = "modified", insertable = false, updatable = false)
	public Date modified;
	
	public Event() {
		
	}
	
	public EventType type(Database db) {
		return db.where("id = ?", this.type_id).results(EventType.class).get(0);
	}
}
