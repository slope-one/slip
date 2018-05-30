package one.slope.slip.asset.container.flat;

import java.net.URI;

import one.slope.slip.asset.StoreFolder;
import one.slope.slip.asset.StoreFile;
import one.slope.slip.asset.Store;

public class FlatStore extends Store {
	public FlatStore(URI directory) {
		super(directory);
	}

	@Override
	public StoreFile file(URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreFolder folder(URI uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(URI uri) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(StoreFile asset) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(StoreFolder archive) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean open() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean close() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFlattened() {
		// TODO Auto-generated method stub
		return false;
	}

}
