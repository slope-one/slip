package one.slope.slip.io.packet.field;

import one.slope.slip.io.SuperBuffer;

public abstract class FieldCodec<T> {
	public abstract T read(SuperBuffer buffer);
	public abstract void write(SuperBuffer buffer, T value);
}
