package one.slope.slip.asset.url;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.Map;

import one.slope.slip.asset.Store;
import one.slope.slip.asset.StoreFactory;

public class StorageURLStreamHandler extends URLStreamHandler {
	private final Map<URL, Store> loadedStores = new HashMap<>();
	private final StoreFactory<?>[] factories;
	
	public StorageURLStreamHandler(StoreFactory<?>[] factories) {
		this.factories = factories;
	}
	
	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		URL storeDirectory = new URL("file:" + url.getPath());
		Store store = null;
		
		try {
			if (loadedStores.containsKey(storeDirectory)) {
				store = loadedStores.get(storeDirectory);
			}
			else {
				for (StoreFactory<?> factory : factories) {
					Store loaded = factory.get(storeDirectory);
					
					if (loaded != null) {
						store = loaded;
						loadedStores.put(storeDirectory, loaded);
					}
				}
			}
		}
		catch (Exception ex) {
			// TODO something better, although error still gets passed up to the caller via throw below
			ex.printStackTrace();
		}
		
		if (store == null) {
			throw new FileNotFoundException("Could not load asset store for given URL: " + storeDirectory);
		}
		
		URL storeURL = new URL("file:" + url.getRef());
		return new StorageURLConnection(store, storeURL);
	}
}
