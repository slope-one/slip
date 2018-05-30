package one.slope.slip.asset.container.flat;

import java.net.URL;

import one.slope.slip.asset.StoreFactory;

public class FlatStoreFactory implements StoreFactory<FlatStore> {
	@Override
	public FlatStore get(URL directory) throws Exception {
		return new FlatStore(directory.toURI());
	}
}
