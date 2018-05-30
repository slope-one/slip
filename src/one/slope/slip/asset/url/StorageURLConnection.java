package one.slope.slip.asset.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import one.slope.slip.asset.Store;
import one.slope.slip.io.stream.SuperBufferInputStream;

public class StorageURLConnection extends URLConnection {
	private final Store store;
	
	protected StorageURLConnection(Store store, URL url) {
		super(url);
		this.store = store;
	}
	
	@Override
	public Object getContent() throws IOException {
		String path = this.getURL().getPath();
		
		if (path.equalsIgnoreCase("/")) {
			return store;
		}
		
		try {
			return store.data(this.getURL().toURI());
		}
		catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public InputStream getInputStream() {
		try {
			return new SuperBufferInputStream(store.data(this.getURL().toURI()));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void connect() throws IOException {
		if (!store.isOpen()) {
			store.open();
		}
	}
}
