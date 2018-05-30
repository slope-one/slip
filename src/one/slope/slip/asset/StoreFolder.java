package one.slope.slip.asset;

import java.io.IOException;

public interface StoreFolder extends StoreFile {
	public StoreFile file(int hash) throws IOException;
	public StoreFile file(String name) throws IOException;
	public boolean exists(int hash) throws IOException;
	public boolean exists(String name) throws IOException;
	
	// TODO figure out if this is revision-specific
	public static int hash(String name) {
		int hash = 0;
		name = name.toUpperCase();
		
		for (int i = 0; i < name.length(); i++) {
			hash = (hash * 61 + name.charAt(i)) - 32;
		}
		
		return hash;
	}
}
