package one.slope.slip.io;

public enum DataType {
	BYTE(1, Integer.class),
	SHORT(2, Integer.class),
	TRIPLE(3, Integer.class),
	INTEGER(4, Integer.class),
	LONG(8, Long.class),
	LB_STRING(DataType.BYTE, String.class), // byte length prefixed string
	LS_STRING(DataType.SHORT, String.class), // short length prefixed string
	NT_STRING(DataTerminator.NULL, String.class), // null terminated string
	LT_STRING(DataTerminator.LINE_FEED, String.class), // line feed terminated string
	BOOLEAN(1, Boolean.class)
	;

	private final DataType length;
	private final DataTerminator terminator;
	private final int width;
	private final Class<?> clazz;
	
	private DataType(int width, Class<?> clazz) {
		this.width = width;
		this.terminator = null;
		this.length = null;
		this.clazz = clazz;
	}
	
	private DataType(DataTerminator terminator, Class<?> clazz) {
		this.width = 0;
		this.terminator = terminator;
		this.length = null;
		this.clazz = clazz;
	}
	
	private DataType(DataType lengthType, Class<?> clazz) {
		this.width = 0;
		this.terminator = null;
		this.length = lengthType;
		this.clazz = clazz;
	}
	
	public Class<?> clazz() {
		return this.clazz;
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
	
	public static DataType getTypeForWidth(int width) {
		for (DataType type : values()) {
			if (type.width == width) {
				return type;
			}
		}
		
		return null;
	}
}