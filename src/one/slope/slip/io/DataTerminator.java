package one.slope.slip.io;

public enum DataTerminator {
	NULL(0),
	LINE_FEED(10);
	
	private final int value;
	
	private DataTerminator(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
}