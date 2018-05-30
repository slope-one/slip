package one.slope.slip.io.stream;

import one.slope.slip.io.SuperBuffer;

public class SuperBufferInputStream extends ByteBufferInputStream {
    public SuperBufferInputStream(SuperBuffer buf) {
    	super(buf.backingBuffer());
    }
}