package one.slope.slip.service;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

import one.slope.slip.io.packet.PacketType;

@Table(name = "revision_packet_read")
public class RevisionPacketRead {
	@Column(name = "event_id", insertable = false, updatable = false)
	public int event_id;
	
	@Column(name = "packet_id", insertable = false, updatable = false)
	public int packet_id;
	
	@Column(name = "revision_id", insertable = false, updatable = false)
	public int revision_id;
	
	@Column(name = "event_type_id")
	public int event_type_id;
	
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
	
	public RevisionPacketRead() {
		
	}

	public Event event(Database db) {
		return db.where("id = ?", this.event_id).results(Event.class).get(0);
	}
	
	public Packet packet(Database db) {
		return db.where("id = ?", this.packet_id).results(Packet.class).get(0);
	}

	public Revision revision(Database db) {
		return db.where("id = ?", this.revision_id).results(Revision.class).get(0);
	}

	public EventType type(Database db) {
		return db.where("id = ?", this.event_type_id).results(EventType.class).get(0);
	}
}
