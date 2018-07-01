package one.slope.slip.io.packet.field;

import one.slope.slip.io.SuperBuffer;

public class BooleanFieldCodec extends FieldCodec<Boolean> {
	@Override
	public Boolean read(SuperBuffer buffer) {
		return buffer.get() != 0;
	}

	@Override
	public void write(SuperBuffer buffer, Boolean value) {
		buffer.put(value ? 1 : 0);
	}
}
