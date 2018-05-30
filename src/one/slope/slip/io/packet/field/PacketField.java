package one.slope.slip.io.packet.field;

import one.slope.slip.io.SuperBuffer;

// TODO implement ArrayPacketField as subclass
public abstract class PacketField<T> {
	protected final int index;
	protected final String name;
	
	public PacketField(String name, int index) {
		this.index = index;
		this.name = name;
	}
	
	public abstract T read(SuperBuffer buffer);
	public abstract void write(SuperBuffer buffer, T value);
	
	public String name() {
		return name;
	}
	
	public int index() {
		return index;
	}
}
