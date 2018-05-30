package one.slope.slip.asset;

import java.net.URL;

public interface StoreFactory<S extends Store> {
	public S get(URL directory) throws Exception;
}
