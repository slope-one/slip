package one.slope.slip.io.packet;

import java.util.HashMap;
import java.util.Map;

import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.packet.field.PacketField;

public class Packet {
	public static final int DEFAULT_ALLOCATION_SIZE = 4096;
	
	private final Map<String, Object> values = new HashMap<String, Object>();
	private final PacketDefinition definition;
	private final SuperBuffer buffer; // TODO maybe move out of this class?
	
	public Packet(PacketDefinition definition) {
		this(definition, new SuperBuffer(definition.length() >= 0 ? definition.length() : DEFAULT_ALLOCATION_SIZE));
	}
	
	public Packet(PacketDefinition definition, SuperBuffer buffer) {
		this.definition = definition;
		this.buffer = buffer;
	}

	@SuppressWarnings({ "rawtypes" })
	public void decode() {
		buffer.reset(); // go back to the start of the buffer
		
		PacketField<?>[] fields = definition.fields();
		
		for (PacketField field : fields) {
			values.put(field.name(), field.read(buffer));
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void encode() {
		buffer.clear(); // go back to the start of the buffer
		
		PacketField<?>[] fields = definition.fields();
		
		for (PacketField field : fields) {
			field.write(buffer, values.get(field.name()));
		}
		
		buffer.flip();
	}
	
	public <T> Packet set(String name, T value) {
		if (!definition.hasField(name)) {
			throw new IllegalArgumentException();
		}
		
		values.put(name, value);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		if (!definition.hasField(name)) {
			throw new IllegalArgumentException();
		}
		
		return (T)values.get(name);
	}
	
	public PacketDefinition definition() {
		return definition;
	}
	
	public SuperBuffer buffer() {
		return buffer;
	}
}
