package one.slope.slip.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "revision")
public class Revision {
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;
	
	@Column(name = "name", unique = true)
	public String name;
	
	@Column(name = "created", insertable = false, updatable = false)
	public Date created;
	
	@Column(name = "modified", insertable = false, updatable = false)
	public Date modified;
	
	public Revision() {
		
	}
}
