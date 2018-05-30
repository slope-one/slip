package one.slope.slip.asset;

import java.io.IOException;
import java.net.URI;

import one.slope.slip.io.SuperBuffer;

public interface StoreFile {
	/**
	 * The data of this Asset. If this Asset was compressed, this will be the uncompressed data.
	 * @return
	 */
	public SuperBuffer data() throws IOException;
	
	/**
	 * Sets the data of this Asset. Depending on implementation, the storage medium may store compressed data.
	 * @param buffer
	 * @return
	 */
	public boolean data(SuperBuffer buffer) throws IOException;
	
	/**
	 * @return The location of this Asset
	 */
	public URI uri();
	
	/**
	 * If the data is compressed, this will be smaller than the value returned by size(), otherwise it will be the same.
	 * @return The size of the file after being compressed.
	 */
	public int compressedSize();
	
	/**
	 * @return The size of the file.
	 */
	public int size() throws IOException;
	
	/**
	 * @return This {@link StoreFile} as an {@link StoreFolder}
	 */
	public StoreFolder folder() throws IOException;
	
	public default boolean isCompressed() throws IOException {
		return this.size() > this.compressedSize();
	}
}
