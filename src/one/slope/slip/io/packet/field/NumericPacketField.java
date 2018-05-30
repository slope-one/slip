package one.slope.slip.io.packet.field;

import one.slope.slip.io.DataEndian;
import one.slope.slip.io.DataType;
import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.DataTransformation;

// TODO implement "smart" as subclass
public class NumericPacketField<T> extends PacketField<T> {
	protected final DataTransformation transformation;
	protected final DataEndian order;
	protected final DataType size;
	
	public NumericPacketField(String name, int index, DataType size, DataEndian order, DataTransformation transformation) {
		super(name, index);
		this.size = size;
		this.order = order;
		this.transformation = transformation;
	}
	
	public NumericPacketField(String name, int index, DataType size, DataTransformation transformation) {
		this(name, index, size, DataEndian.BIG, transformation);
	}
	
	public NumericPacketField(String name, int index, DataType size) {
		this(name, index, size, DataEndian.BIG, DataTransformation.NONE);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T read(SuperBuffer buffer) {
		Long value = buffer.get(size, order, transformation);
		return (T)value;
	}

	@Override
	public void write(SuperBuffer buffer, T value) {
		buffer.put((long)value, size, order, transformation);
	}
}
