package one.slope.slip.io.packet.field;

import one.slope.slip.io.SuperBuffer;

public class PacketField<T> extends FieldCodec<T> {
	protected final int index;
	protected final String name;
	protected transient final FieldCodec<T> codec;
	
	public PacketField(String name, int index, FieldCodec<T> codec) {
		this.index = index;
		this.name = name;
		this.codec = codec;
	}
	
	public String name() {
		return name;
	}
	
	public int index() {
		return index;
	}
	
	public FieldCodec<T> codec() {
		return codec;
	}

	@Override
	public T read(SuperBuffer buffer) {
		return codec.read(buffer);
	}

	@Override
	public void write(SuperBuffer buffer, T value) {
		codec.write(buffer, value);
	}
}
