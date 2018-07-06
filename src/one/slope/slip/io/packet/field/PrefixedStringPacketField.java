package one.slope.slip.io.packet.field;

import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.DataType;

public class PrefixedStringPacketField extends FieldCodec<String> {
	private DataType prefix;
	
	public PrefixedStringPacketField(DataType prefix) {
		this.prefix = prefix;
	}

	@Override
	public String read(SuperBuffer buffer) {
		byte[] bytes = buffer.get(prefix.width());
		return new String(bytes);
	}

	@Override
	public void write(SuperBuffer buffer, String value) {
		byte[] bytes = value.getBytes();
		buffer.put(bytes.length, this.prefix);
		buffer.put(bytes);
	}
}
