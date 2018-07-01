package one.slope.slip.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * A wrapper for {@link ByteBuffer} that supports reading and writing data in different endians and data transformations.
 */
public final class SuperBuffer {
	private ByteBuffer buffer;
	private byte[] readStreamBuffer = null;
	
	public SuperBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}
	
	public SuperBuffer(int size) {
		this.buffer = ByteBuffer.allocateDirect(size);
	}
	
	public SuperBuffer put(String value) {
		return put(value, DataTerminator.LINE_FEED);
	}
	
	public SuperBuffer put(String value, DataTerminator terminator) {
		return put(value, terminator.value());
	}
	
	public SuperBuffer put(String value, int terminator) {
		for (char b : value.toCharArray()) {
			put(b);
		}
		
		return put(terminator);
	}
	
	public SuperBuffer put(int value) {
		buffer.put((byte)value);
		return this;
	}

	public SuperBuffer put(ByteBuffer inboundTemp) {
		buffer.put(inboundTemp);
		return this;
	}
	
	public SuperBuffer put(byte[] data) {
		buffer.put(data);
		return this;
	}
	
	public SuperBuffer put(byte[] data, int offset, int length) {
		buffer.put(data, offset, length);
		return this;
	}
	
	public SuperBuffer put(SuperBuffer buffer) {
		return this.put(buffer, this.limit());
	}
	
	public SuperBuffer put(SuperBuffer buffer, int amount) {
		return this.put(buffer.get(amount));
	}
	
	public int putAvailable(InputStream stream) throws IOException {
		int available = stream.available();
		
		if (available > 0) {
			if (readStreamBuffer == null || readStreamBuffer.length < available) {
				readStreamBuffer = new byte[available];
			}
			
			int read = stream.readNBytes(readStreamBuffer, 0, available);
			put(readStreamBuffer, 0, read);
			return read;
		}
		
		return 0;
	}
	
	public boolean hasRemaining() {
		return buffer.hasRemaining();
	}
	
	/**
	 * @see ByteBuffer#get(byte[])
	 */
	public SuperBuffer get(byte[] dst) {
		buffer.get(dst);
		return this;
	}
	
	public byte get() {
		return buffer.get();
	}
	
	public int getUnsigned() {
		return buffer.get() & 0xFF;
	}
	
	public byte[] get(int amount) {
		byte[] dest = new byte[amount];
		buffer.get(dest);
		return dest;
	}
	
	public int get(DataType size) {
		return (int)getLong(size);
	}
	
	public long getLong(DataType size) {
		return get(size, DataEndian.BIG, DataTransformation.NONE);
	}
	
	public long getLongUnsigned(DataType size) {
		return getUnsigned(size, DataEndian.BIG, DataTransformation.NONE);
	}
	
	public int getUnsigned(DataType size) {
		return (int)getLongUnsigned(size);
	}
	
	// TODO?: unsigned for long
	public long getUnsigned(DataType size, DataEndian order, DataTransformation transformation) {
		long value = get(size, order, transformation);
		long clear = 0;
		
		for (int i = 0; i < size.width(); i++) {
			clear |= 0xFFL << i * 8;
		}
		
		return value & clear;
	}
	
	public long get(DataType size, DataEndian order, DataTransformation transformation) {
		int dataLength = size.width();
		long value = 0;
		long clear = 0;
		
		switch(order) {
			case BIG:
				for (int i = dataLength - 1; i >= 0; i--) {
					value |= (get() & 0xFFL) << i * 8;
					clear |= 0xFFL << i * 8;
				}
				break;
			case LITTLE:
				for (int i = 0; i < dataLength; i++) {
					value |= (get() & 0xFFL) << i * 8;
					clear |= 0xFFL << i * 8;
				}
				break;
			case MIDDLE_BIG:
				value |= (get() & 0xFF) << 8;
				value |= (get() & 0xFF);
				value |= (get() & 0xFF) << 24;
				value |= (get() & 0xFF) << 16;
				clear |= 0xFFFFFFFF;
				break;
			case MIDDLE_LITTLE:
				value |= (get() & 0xFF) << 16;
				value |= (get() & 0xFF) << 24;
				value |= (get() & 0xFF);
				value |= (get() & 0xFF) << 8;
				clear |= 0xFFFFFFFF;
				break;
		}

		// apply specified transformation
		value = transformation.operator().reverse(value, 0);
		
		// if sign bit is present at the least most bit according to data length
		if (((value >> dataLength * 8 - 1) & 1) == 1) {
			// set all values to 1 to bits outside our data size
			// or them to the sign bits
			value = (~clear) | value;
		}
		
		return value;
	}
	
	public SuperBuffer put(long value, DataType size, DataEndian order, DataTransformation transformation) {
		value = transformation.operator().apply(value, 0);
		int dataLength = size.width();

		switch(order) {
			case BIG:
				for (int i = dataLength - 1; i >= 0; i--) {
					put((byte)(value >> i * 8));
				}
				break;
			case LITTLE:
				for (int i = 0; i < dataLength; i++) {
					put((byte)(value >> i * 8));
				}
				break;
			case MIDDLE_BIG:
				put((byte)(value >> 8));
				put((byte)(value));
				put((byte)(value >> 24));
				put((byte)(value >> 16));
				break;
			case MIDDLE_LITTLE:
				put((byte)(value >> 16));
				put((byte)(value >> 24));
				put((byte)(value));
				put((byte)(value >> 8));
				break;
		}
	
		return this;
	}
	
	public String getString() {
		return getString(DataTerminator.LINE_FEED);
	}
	
	public String getString(DataTerminator terminator) {
		return getString(terminator.value());
	}
	
	public String getString(int terminator) {
		StringBuilder builder = new StringBuilder();
		int b = 0;
		
		while ((b = get()) != terminator) {
			builder.append((char)b);
		}
		
		return builder.toString();
	}

	/**
	 * @see ByteBuffer#limit()
	 */
	public int limit() {
		return this.buffer.limit();
	}

	/**
	 * @see ByteBuffer#capacity()
	 */
	public int capacity() {
		return this.buffer.capacity();
	}

	/**
	 * Returns the backing {@link ByteBuffer}
	 */
	public ByteBuffer backingBuffer() {
		return buffer;
	}

	/**
	 * @see ByteBuffer#flip()
	 * @return Self-reference for chaining together many calls.
	 */
	public SuperBuffer flip() {
		buffer.flip();
		return this;
	}

	/**
	 * @see ByteBuffer#mark()
	 * @return Self-reference for chaining together many calls.
	 */
	public SuperBuffer mark() {
		buffer.mark();
		return this;
	}

	/**
	 * @see ByteBuffer#reset()
	 * @return Self-reference for chaining together many calls.
	 */
	public SuperBuffer reset() {
		buffer.reset();
		return this;
	}

	/**
	 * @see ByteBuffer#clear()
	 * @return Self-reference for chaining together many calls.
	 */
	public SuperBuffer clear() {
		buffer.clear();
		return this;
	}

	/**
	 * @see ByteBuffer#compact()
	 * @return Self-reference for chaining together many calls.
	 */
	public SuperBuffer compact() {
		buffer.compact();
		return this;
	}

	/**
	 * @see ByteBuffer#position()
	 */
	public int position() {
		return buffer.position();
	}

	/**
	 * @see ByteBuffer#remaining()
	 */
	public int remaining() {
		return buffer.remaining();
	}

	/**
	 * @see ByteBuffer#duplicate()
	 */
	public SuperBuffer duplicate() {
		return new SuperBuffer(buffer.duplicate());
	}
	
	/**
	 * Gets the remaining bytes in this buffer as an array.
	 * @return The remaining bytes
	 */
	public byte[] array() {
		if (buffer.hasArray()) {
			byte[] buff = buffer.array();
			
			if (buff.length - buffer.remaining() != buff.length) {
				byte[] buff2 = new byte[buffer.remaining()];
				System.arraycopy(buff, (buff.length - buffer.remaining()), buff2, 0, buff2.length);
				return buff2;
			}
			
			return buff;
		}
		
		return get(remaining());
	}
}
