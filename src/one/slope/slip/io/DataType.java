package one.slope.slip.io;

public enum DataType {
		BYTE(1),
		SHORT(2),
		TRIPLE(3),
		INTEGER(4),
		LONG(8),
		LB_STRING(DataType.BYTE), // byte length prefixed string
		LS_STRING(DataType.SHORT), // short length prefixed string
		NT_STRING(DataTerminator.NULL), // null terminated string
		LT_STRING(DataTerminator.LINE_FEED) // line feed terminated string
	;

	private final DataType length;
	private final DataTerminator terminator;
	private final int width;
	
	private DataType(int width) {
		this.width = width;
		this.terminator = null;
		this.length = null;
	}
	
	private DataType(DataTerminator terminator) {
		this.width = 0;
		this.terminator = terminator;
		this.length = null;
	}
	
	private DataType(DataType lengthType) {
		this.width = 0;
		this.terminator = null;
		this.length = lengthType;
	}
	
	public boolean hasLength() {
		return length != null;
	}
	
	public boolean hasTerminator() {
		return terminator != null;
	}
	
	public DataTerminator terminator() {
		return terminator;
	}
	
	public DataType length() {
		return length;
	}
	
	public int width() {
		return width;
	}
}