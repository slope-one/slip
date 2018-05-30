package one.slope.slip.io;

/**
 * Base class which allows support for decompression of a SuperBuffer
 */
public abstract class Compression {
	private SuperBuffer input;
	private SuperBuffer output;
	
	// TODO this class implements two different ways of achieving the same goal, figure out best one and stick to it
	// one is by specifying the input buffer in the constructor and calling output() or decompress()
	// the second is by using an empty constructor and calling decompress() with no storing to input/output vars
	
	public Compression(SuperBuffer input) {
		this.input = input;
	}
	
	public Compression() {
		// empty constructor
	}
	
	public abstract SuperBuffer decompress(SuperBuffer buffer);

	public SuperBuffer decompress() {
		return this.decompress(this.input);
	}
	
	public int compressedSize() {
		return this.input.capacity();
	}
	
	public int decompressedSize() {
		return this.output.capacity();
	}
	
	public SuperBuffer output() {
		if (output == null) {
			this.output = decompress();
		}
		
		return this.output;
	}
	
	public SuperBuffer input() {
		return this.input;
	}
}
