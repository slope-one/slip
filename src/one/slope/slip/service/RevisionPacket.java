package one.slope.slip.service;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dieselpoint.norm.Database;

@Table(name = "revision_packet")
public class RevisionPacket {
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;
	
	@Column(name = "packet_id")
	public int packet_id;
	
	@Column(name = "revision_id")
	public int revision_id;
	
	@Column(name = "opcode")
	public int opcode;
	
	@Column(name = "length")
	public int length;
	
	@Column(name = "created", insertable = false, updatable = false)
	public Date created;
	
	@Column(name = "modified", insertable = false, updatable = false)
	public Date modified;
	
	public RevisionPacket() {
		
	}
	
	public Packet packet(Database db) {
		return db.where("id = ?", this.packet_id).results(Packet.class).get(0);
	}

	public Revision revision(Database db) {
		return db.where("id = ?", this.revision_id).results(Revision.class).get(0);
	}
}
