package one.slope.slip.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

@Table(name = "revision_field")
public class RevisionField {
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;
	
	@Column(name = "field_id")
	public int field_id;

	@Column(name = "type_id")
	public int type_id;

	@Column(name = "packet_id")
	public int packet_id;
	
	@Column(name = "rank")
	public int rank;
	
	@Column(name = "created", insertable = false, updatable = false)
	public Date created;
	
	@Column(name = "modified", insertable = false, updatable = false)
	public Date modified;
	
	public RevisionField() {
		
	}
	
	public RevisionPacket packet(Database db) {
		return db.where("id = ?", this.packet_id).results(RevisionPacket.class).get(0);
	}
	
	public Field field(Database db) {
		return db.where("id = ?", this.field_id).results(Field.class).get(0);
	}
	
	public FieldType type(Database db) {
		return db.where("id = ?", this.type_id).results(FieldType.class).get(0);
	}
}
