package one.slope.slip.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

@Table(name = "field_type_transform")
public class FieldTypeTransform {
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;
	
	@Column(name = "type_id")
	public int type_id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "value")
	public String value;
	
	@Column(name = "created", insertable = false, updatable = false)
	public Date created;
	
	@Column(name = "modified", insertable = false, updatable = false)
	public Date modified;
	
	public FieldTypeTransform() {
		
	}
	
	public FieldType type(Database db) {
		return db.where("id = ?", this.type_id).results(FieldType.class).get(0);
	}
}
