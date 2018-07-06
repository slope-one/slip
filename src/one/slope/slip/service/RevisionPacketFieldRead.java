package one.slope.slip.service;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

import one.slope.slip.io.DataEndian;
import one.slope.slip.io.DataRange;
import one.slope.slip.io.DataTransformation;
import one.slope.slip.io.DataType;
import one.slope.slip.io.packet.PacketType;

@Table(name = "revision_packet_field_read")
public class RevisionPacketFieldRead {
	@Column(name = "event_id", insertable = false, updatable = false)
	public int event_id;
	
	@Column(name = "packet_id", insertable = false, updatable = false)
	public int packet_id;
	
	@Column(name = "revision_id", insertable = false, updatable = false)
	public int revision_id;
	
	@Column(name = "field_id", insertable = false, updatable = false)
	public int field_id;
	
	@Column(name = "revision_packet_id", insertable = false, updatable = false)
	public int revision_packet_id;
	
	@Column(name = "revision_field_id", insertable = false, updatable = false)
	public int revision_field_id;

	@Column(name = "event_type_id", insertable = false, updatable = false)
	public int event_type_id;

	@Column(name = "field_type_id", insertable = false, updatable = false)
	public int field_type_id;

	@Column(name = "revision_field_type_id", insertable = false, updatable = false)
	public int revision_field_type_id;
	
	@Column(name = "opcode", insertable = false, updatable = false)
	public int opcode;
	
	@Column(name = "length", insertable = false, updatable = false)
	public long length;

	@Column(name = "event_name", insertable = false, updatable = false)
	public String event_name;

	@Enumerated(EnumType.STRING)
	@Column(name = "event_type_name", insertable = false, updatable = false)
	public PacketType event_type_name;
	
	@Column(name = "revision", insertable = false, updatable = false)
	public String revision;
	
	@Column(name = "field_name", insertable = false, updatable = false)
	public String field_name;

	@Column(name = "revision_field_rank", insertable = false, updatable = false)
	public int revision_field_rank;

	@Enumerated(EnumType.STRING)
	@Column(name = "field_type", insertable = false, updatable = false)
	public DataType field_type;

	@Enumerated(EnumType.STRING)
	@Column(name = "revision_field_type", insertable = false, updatable = false)
	public DataType revision_field_type;

	@Enumerated(EnumType.STRING)
	@Column(name = "ranged", insertable = false, updatable = false)
	public DataRange range;

	@Enumerated(EnumType.STRING)
	@Column(name = "endian", insertable = false, updatable = false)
	public DataEndian endian;

	@Enumerated(EnumType.STRING)
	@Column(name = "transformation", insertable = false, updatable = false)
	public DataTransformation transformation;
	
	public RevisionPacketFieldRead() {
		
	}

	public Event event(Database db) {
		return db.where("id = ?", this.event_id).results(Event.class).get(0);
	}
	
	public FieldType fieldType(Database db) {
		return db.where("id = ?", this.field_type_id).results(FieldType.class).get(0);
	}

	public RevisionField revisionField(Database db) {
		return db.where("id = ?", this.revision_field_id).results(RevisionField.class).get(0);
	}

	public FieldType revisionFieldType(Database db) {
		return db.where("id = ?", this.revision_field_type_id).results(FieldType.class).get(0);
	}
	
	public Packet packet(Database db) {
		return db.where("id = ?", this.packet_id).results(Packet.class).get(0);
	}
	
	public Field field(Database db) {
		return db.where("id = ?", this.field_id).results(Field.class).get(0);
	}

	public Revision revision(Database db) {
		return db.where("id = ?", this.revision_id).results(Revision.class).get(0);
	}

	public RevisionPacket revisionPacket(Database db) {
		return db.where("id = ?", this.revision_packet_id).results(RevisionPacket.class).get(0);
	}

	public EventType type(Database db) {
		return db.where("id = ?", this.event_type_id).results(EventType.class).get(0);
	}
}
