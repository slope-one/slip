package one.slope.slip.io.packet.field;

import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.DataTerminator;

public class StringPacketField extends PacketField<String> {
	private DataTerminator terminator;
	
	public StringPacketField(String name, int index, DataTerminator terminator) {
		super(name, index);
		this.terminator = terminator;
	}

	@Override
	public String read(SuperBuffer buffer) {
		return buffer.getString(terminator);
	}

	@Override
	public void write(SuperBuffer buffer, String value) {
		buffer.put(value, terminator);
	}
}
