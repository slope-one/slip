package one.slope.slip.asset;

import java.io.IOException;
import java.net.URI;

import one.slope.slip.io.SuperBuffer;

public abstract class Store {
	private final URI directory;
	
	public Store(URI directory) {
		this.directory = directory;
	}
	
	public abstract StoreFile file(URI uri) throws IOException;
	public abstract StoreFolder folder(URI uri) throws IOException;
	public abstract boolean exists(URI uri) throws IOException;
	public abstract boolean save(StoreFile file) throws IOException;
	public abstract boolean save(StoreFolder folder) throws IOException;
	public abstract boolean open() throws IOException;
	public abstract boolean close() throws IOException;
	public abstract boolean isOpen();
	public abstract boolean isFlattened();
	
	// TODO checksums? iirc it was just a "virtual" asset in index 0?
	
	public SuperBuffer data(URI uri) throws IOException {
		// TODO better reading of URL to determine if it is within an archive
		return file(uri).data();
	}
	
	public URI directory() {
		return this.directory;
	}
}
