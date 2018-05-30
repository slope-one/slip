package one.slope.slip.io.stream;

import one.slope.slip.io.SuperBuffer;

public class SuperBufferOutputStream extends ByteBufferOutputStream {
	public SuperBufferOutputStream(SuperBuffer buf) {
		super(buf.backingBuffer());
	}
}
