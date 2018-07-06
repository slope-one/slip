package one.slope.slip.io.packet;

import java.util.Arrays;
import java.util.Comparator;

import one.slope.slip.io.packet.field.PacketField;

public class PacketDefinition {
	protected final PacketField<?>[] fields;
	protected final PacketSize size;
	protected final PacketType type;
	protected final String name;
	protected final int length;
	protected final int id;
	
	public PacketDefinition(PacketType type, int id, String name, PacketField<?>[] fields, int length) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.fields = fields;
		this.length = length;
		this.size = length >= 0 ? PacketSize.FIXED : PacketSize.VARIABLE;
		
		Arrays.sort(this.fields, new Comparator<PacketField<?>>() {
			public int compare(PacketField<?> a, PacketField<?> b) {
				return a.index() - b.index();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public <T> PacketField<T> field(int index) {
		for (PacketField<?> field : fields) {
			if (field.index() == index) {
				return (PacketField<T>)field;
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> PacketField<T> field(String name) {
		for (PacketField<?> field : fields) {
			if (field.name().equalsIgnoreCase(name)) {
				return (PacketField<T>)field;
			}
		}
		
		return null;
	}
	
	public boolean hasField(String name) {
		return field(name) != null;
	}
	
	public int length() {
		return length;
	}
	
	public PacketSize size() {
		return size;
	}
	
	public PacketField<?>[] fields() {
		return fields;
	}
	
	public PacketType type() {
		return type;
	}
	
	public int id() {
		return id;
	}
	
	public String name() {
		return name;
	}
}
