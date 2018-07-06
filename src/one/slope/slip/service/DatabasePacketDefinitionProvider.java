package one.slope.slip.service;

import java.util.ArrayList;
import java.util.List;

import com.dieselpoint.norm.Database;

import one.slope.slip.io.DataType;
import one.slope.slip.io.packet.PacketDefinition;
import one.slope.slip.io.packet.PacketDefinitionProvider;
import one.slope.slip.io.packet.field.BooleanFieldCodec;
import one.slope.slip.io.packet.field.FieldCodec;
import one.slope.slip.io.packet.field.NumericFieldCodec;
import one.slope.slip.io.packet.field.PacketField;
import one.slope.slip.io.packet.field.PrefixedStringPacketField;
import one.slope.slip.io.packet.field.TerminatedStringPacketField;

public class DatabasePacketDefinitionProvider implements PacketDefinitionProvider {
	private final Database database;
	
	private PacketDefinition[] definitions;
	
	public DatabasePacketDefinitionProvider(Database database) {
		this.database = database;
	}
	
	public boolean load(String revision) {
		List<RevisionPacketRead> packets = database.where("revision = ?", revision).results(RevisionPacketRead.class);
		List<PacketDefinition> temp = new ArrayList<>();
		
		packets.forEach((packet) -> {
			List<RevisionPacketFieldRead> fields = database.where("revision_id = ? AND event_id = ?", packet.revision_id, packet.event_id).results(RevisionPacketFieldRead.class);
			List<PacketField<?>> tempFields = new ArrayList<>();
			
			fields.forEach((field) -> {
				FieldCodec<?> codec = null;
				
				switch(field.revision_field_type) {
					case BOOLEAN:
						codec = new BooleanFieldCodec();
						break;
					case BYTE:
					case SHORT:
					case TRIPLE:
					case INTEGER:
					case LONG:
						codec = new NumericFieldCodec<Long>(field.revision_field_type, field.endian, field.transformation, field.range);
						break;
					case LB_STRING:
					case LS_STRING:
						codec = new PrefixedStringPacketField(field.revision_field_type.length());
						break;
					case LT_STRING:
					case NT_STRING:
						codec = new TerminatedStringPacketField(field.revision_field_type.terminator());
						break;
				}
				
				PacketField<?> pfield = new PacketField(field.field_name, field.revision_field_rank, codec);
				tempFields.add(pfield);
			});
			
			PacketDefinition def = new PacketDefinition(packet.event_type_name, packet.opcode, packet.event_name, tempFields.toArray(new PacketField[0]), (int)packet.length);
			temp.add(def);
		});
		
		this.definitions = temp.toArray(new PacketDefinition[0]);
		return this.definitions != null && this.definitions.length > 0;
	}
	
	@Override
	public PacketDefinition[] get() {
		return this.definitions;
	}
	
	public Database database() {
		return this.database;
	}
}
