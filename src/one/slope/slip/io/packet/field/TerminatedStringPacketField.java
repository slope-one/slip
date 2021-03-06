package one.slope.slip.io.packet.field;

import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.DataTerminator;

public class TerminatedStringPacketField extends FieldCodec<String> {
	private DataTerminator terminator;
	
	public TerminatedStringPacketField(DataTerminator terminator) {
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
