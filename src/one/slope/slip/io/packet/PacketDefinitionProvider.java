package one.slope.slip.io.packet;

import java.util.ArrayList;
import java.util.List;

public interface PacketDefinitionProvider {
	public default PacketDefinition get(PacketType type, int id) {
		PacketDefinition[] packets = get();
		
		for (PacketDefinition def : packets) {
			if (def.type() == type && def.id() == id) {
				return def;
			}
		}
		
		return null;
	}
	
	public default PacketDefinition[] get(PacketType type) {
		PacketDefinition[] packets = get();
		List<PacketDefinition> defs = new ArrayList<>();
		
		for (PacketDefinition def : packets) {
			if (def.type() == type) {
				defs.add(def);
			}
		}
		
		return defs.toArray(new PacketDefinition[0]);
	}
	
	public PacketDefinition[] get();
}
