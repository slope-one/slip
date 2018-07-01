package one.slope.slip.io.packet.field;

import one.slope.slip.io.DataEndian;
import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.DataTransformation;

/**
 * Handles compressed text
 */
public class CompressedTextFieldCodec extends FieldCodec<String> {
	protected final DataTransformation transformation;
	protected final DataEndian order;
	
	public CompressedTextFieldCodec(DataEndian order, DataTransformation transformation) {
		this.order = order;
		this.transformation = transformation;
	}
	
	public CompressedTextFieldCodec() {
		this(DataEndian.BIG, DataTransformation.NONE);
	}

	@Override
	public String read(SuperBuffer buffer) {
		// TODO implement decompression
		return null;
	}
	
	@Override
	public void write(SuperBuffer buffer, String value) {
		// TODO implement compression
	}
}
