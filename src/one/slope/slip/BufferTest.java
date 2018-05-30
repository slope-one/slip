package one.slope.slip;

import java.nio.ByteBuffer;

import one.slope.slip.io.DataEndian;
import one.slope.slip.io.DataTransformation;
import one.slope.slip.io.DataType;
import one.slope.slip.io.SuperBuffer;

public class BufferTest {
	public static void main(String[] args) {
		DataType[] types = {
			DataType.BYTE,
			DataType.SHORT,
			DataType.TRIPLE,
			DataType.INTEGER,
			DataType.LONG
		};
		DataEndian[] endians = DataEndian.values();
		DataTransformation[] transforms = DataTransformation.values();
		
		for (DataType type : types) {
			long testValue = 255;
			
			switch(type) {
				case BYTE:
					testValue = -110;
					break;
				case SHORT:
					testValue = -2203;
					break;
				case TRIPLE:
					testValue = -1600000;
					break;
				case INTEGER:
					testValue = -2040100;
					break;
				case LONG:
					testValue = -28037546508280L;
					break;
				default:
					break;
			}
			
			for (DataEndian endian : endians) {
				if (type.width() != 4 && (endian == DataEndian.MIDDLE_BIG || endian == DataEndian.MIDDLE_LITTLE)) {
					continue;
				}
				
				for (DataTransformation transform : transforms) {
					SuperBuffer b = new SuperBuffer(ByteBuffer.allocate(type.width()));
					
					b.put(testValue, type, endian, transform);
					b.flip();
					
					byte[] bytes = new byte[type.width()];
					b.mark().get(bytes).reset();
					long retValue = b.get(type, endian, transform);
					
					String f = "";
					
					for (byte z : bytes) {
						f += z + ", ";
					}
					
					System.out.println("Test " + (retValue != testValue ? "failed" : "passed") + " for: [" + type + ", " + endian + ", " + transform + "]\t(" + f.substring(0, f.length() - 2) + ")\tin: " + testValue + "\tout: " + retValue);
				}
			}
		}
		
		String test = "Do not be alarmed. This is a test.";
		SuperBuffer b = new SuperBuffer(ByteBuffer.allocate(64));
		b.put(test);
		b.flip();
		String out = b.getString();
		System.out.println("Test " + (!test.equals(out) ? "failed" : "passed") + " for: String\t\tin: " + test + "\t\tout: " + out);
		
		
	}
}
