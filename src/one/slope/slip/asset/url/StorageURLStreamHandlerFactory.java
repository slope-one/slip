package one.slope.slip.asset.url;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import one.slope.slip.asset.StoreFactory;

public class StorageURLStreamHandlerFactory implements URLStreamHandlerFactory {
	private final StoreFactory<?>[] factories;
	
	public StorageURLStreamHandlerFactory(StoreFactory<?>[] factories) {
		this.factories = factories;
	}
	
	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		if ("storage".equals(protocol) || "cache".equals(protocol)) {
            return new StorageURLStreamHandler(factories);
        }

        return null;
	}
}
