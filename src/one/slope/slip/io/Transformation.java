package one.slope.slip.io;

public interface Transformation {
	public long apply(long value, int byteIndex);
	public long reverse(long value, int byteIndex);
}
