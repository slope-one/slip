package one.slope.slip.io.packet.field;

import one.slope.slip.io.DataEndian;
import one.slope.slip.io.SuperBuffer;
import one.slope.slip.io.DataTransformation;

/**
 * Handles compressed text
 */
public class CompressedTextPacketField extends PacketField<String> {
	protected final DataTransformation transformation;
	protected final DataEndian order;
	
	public CompressedTextPacketField(String name, int index, DataEndian order, DataTransformation transformation) {
		super(name, index);
		this.order = order;
		this.transformation = transformation;
	}
	
	public CompressedTextPacketField(String name, int index) {
		this(name, index, DataEndian.BIG, DataTransformation.NONE);
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
