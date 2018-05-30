package one.slope.slip.io;

public enum DataTransformation {
	ADD(new Transformation() {
		@Override
		public long apply(long value, int byteIndex) {
			// pull the byte to apply transformation out of value
			long v = (value >> byteIndex * 8) & 0xFFL;
			// clear the bits at the proper location
			value = value & ~(0xFFL << byteIndex * 8);
			// apply the transformation, into place of the bits we cleared
			return value | (((v + 128) & 0xFFL) << byteIndex * 8);
		}
		
		@Override
		public long reverse(long value, int byteIndex) {
			return apply(value, byteIndex);
		}
		/*@Override
		public long apply(long value, int byteIndex) {
			byte v = (byte)((value >> byteIndex * 8) & 0xFFL);
			long h = ~(0xFFL << byteIndex * 8);
			String a = Long.toBinaryString(value);
			String b = Long.toBinaryString(v);
			String z = Long.toBinaryString(h);
			byte vr = (byte)((v + 128) & 0xFFL);
			return (value & h) | (vr << byteIndex * 8);
		}
		
		@Override
		public long reverse(long value, int byteIndex) {
			byte v = (byte)((value >> byteIndex * 8) & 0xFFL);
			byte vc = (byte)(v - 128);
			long h = ~(0xFFL << byteIndex * 8);
			String vcb = Long.toBinaryString(vc);
			byte vvzz = (byte)(vc << byteIndex * 8);
			String vccbczbz = Long.toBinaryString(vvzz);
			//longValue |= buffer.readByte() - 128 & 0xFFL;
			return (value & h) | vvzz;
		}*/
	}),
	NEGATE(new Transformation() {
		@Override
		public long apply(long value, int byteIndex) {
			long v = (value >> byteIndex * 8) & 0xFFL;
			value = value & ~(0xFFL << byteIndex * 8);
			return value | ((-v & 0xFFL) << byteIndex * 8);
		}

		@Override
		public long reverse(long value, int byteIndex) {
			return apply(value, byteIndex); // just the same operation applied
		}
	}),
	SUBTRACT(new Transformation() {
		@Override
		public long apply(long value, int byteIndex) {
			long v = (value >> byteIndex * 8) & 0xFFL;
			value = value & ~(0xFFL << byteIndex * 8);
			return value | (((128 - v) & 0xFFL) << byteIndex * 8);
		}
		
		@Override
		public long reverse(long value, int byteIndex) {
			return apply(value, byteIndex);
		}
	}),
	NONE(new Transformation() {
		@Override
		public long apply(long value, int byteIndex) {
			return value;
		}

		@Override
		public long reverse(long value, int byteIndex) {
			return value;
		}
	});
	
	private final Transformation operator;
	
	private DataTransformation(Transformation operator) {
		this.operator = operator;
	}
	
	public Transformation operator() {
		return this.operator;
	}
}