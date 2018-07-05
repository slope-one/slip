package one.slope.slip.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

@Table(name = "revision_field_transform")
public class RevisionFieldTransform {
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;
	
	@Column(name = "field_id")
	public int field_id;
	
	@Column(name = "transform_id")
	public int transform_id;
	
	@Column(name = "name")
	public String name;
	
	@Column(name = "created", insertable = false, updatable = false)
	public Date created;
	
	@Column(name = "modified", insertable = false, updatable = false)
	public Date modified;
	
	public RevisionFieldTransform() {
		
	}
	
	public RevisionField field(Database db) {
		return db.where("id = ?", this.field_id).results(RevisionField.class).get(0);
	}
	
	public FieldTypeTransform transform(Database db) {
		return db.where("id = ?", this.transform_id).results(FieldTypeTransform.class).get(0);
	}
}
