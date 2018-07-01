package one.slope.slip.io.packet.field;

import one.slope.slip.io.DataEndian;
import one.slope.slip.io.DataType;
import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.DataTransformation;

// TODO implement "smart" as subclass
public class NumericFieldCodec<T> extends FieldCodec<T> {
	protected final DataTransformation transformation;
	protected final DataEndian order;
	protected final DataType size;
	
	public NumericFieldCodec(DataType size, DataEndian order, DataTransformation transformation) {
		this.size = size;
		this.order = order;
		this.transformation = transformation;
	}
	
	public NumericFieldCodec(DataType size, DataTransformation transformation) {
		this(size, DataEndian.BIG, transformation);
	}
	
	public NumericFieldCodec(DataType size) {
		this(size, DataEndian.BIG, DataTransformation.NONE);
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
