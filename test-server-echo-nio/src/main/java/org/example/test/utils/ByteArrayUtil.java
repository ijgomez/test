package org.example.test.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Formatter;
import java.util.List;
import java.util.stream.IntStream;

public class ByteArrayUtil {
	
	public static byte[] toByteArray(List<Byte> list) {
		byte[] byteArray = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			byteArray[i] = list.get(i);
		}
		return byteArray;
	}
	
	public static String bytesToHex(final byte[] bytes) {
		return IntStream.range(0, bytes.length).collect(StringBuilder::new, (sb, i) -> new Formatter(sb).format("%02x ", bytes[i] & 0xff), StringBuilder::append).toString();
	}
	
	
	public static String toHexadecimal(byte[] data) {
		return bytesToHex(data);
//		if (data == null) return null;
//		stringbuilder hex = new stringbuilder(data.length * 3);  
//		for (byte b : data) {
//			hex.append(string.format("%02x ", b));
//		}
//		return hex.tostring(); 
//		
////		string s = "";
////		for (byte v : data) {
////			s += string.format("%02x ", v);
////		}
////		return s;
	}
	
	public static int toUnsignedInt (byte[] bytes) {
	     DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
	     try {
	    	if (bytes.length == 1) {
	    		return dataInputStream.readByte() & 0x000000FF;
	    	} else if (bytes.length == 2) {
	    		return dataInputStream.readShort() & 0x0000FFFF;
		    } else if (bytes.length == 3) {
		    	return ((dataInputStream.readByte() & 0x000000FF) << 16) | (dataInputStream.readShort() & 0x0000FFFF);
	    	} else if (bytes.length == 4) {
	    		return dataInputStream.readInt();
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
  }
	
	public static byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte) value};

	}
	public static byte[] shortToByteArray(short value) {
        return new byte[] {
                (byte)(value >>> 8),
                (byte) value};

	}
	
	public static float toFloat (byte[] bytes) {
		return Float.intBitsToFloat(toInt(bytes));
	}
	
	public static short toShort(byte b) {
		byte hi = (byte) 0x00;
		byte lo = b;

		return (short) (((hi & 0xFF) << 8) | (lo & 0xFF));
	}
	
	public static int toInt (byte[] bytes) {
	    DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
	    try {
	    	if (bytes.length == 1) {
	    		return dis.readByte();
	    	} else if (bytes.length == 2) {
	    		return dis.readShort();
	    	} else if (bytes.length == 3) {
	    		return (dis.readByte() << 16) | (dis.readShort() & 0x0000FFFF);
	    	} else if (bytes.length == 4) {
	    		return dis.readInt();
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
   }

	public static byte[] concat(byte[] first, byte[] second) {
		byte[] result = new byte[first.length + second.length];
		System.arraycopy(first, 0, result, 0, first.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	public static ByteBuffer toByteBuffer(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(new byte[data.length]);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public static String toASCII(byte[] data) {

	        final StringBuilder str = new StringBuilder();
	        for (final Byte b : data) {
	            switch (b) {
	                case 0x00:
	                    str.append("<NUL>");
	                    break;
	                case 0x01:
	                    str.append("<SOH>");
	                    break;
	                case 0x02:
	                    str.append("<STX>");
	                    break;
	                case 0x03:
	                    str.append("<ETX>");
	                    break;
	                case 0x04:
	                    str.append("<EOT>");
	                    break;
	                case 0x05:
	                    str.append("<ENQ>");
	                    break;
	                case 0x06:
	                    str.append("<ACK>");
	                    break;
	                case 0x07:
	                    str.append("<BEL>");
	                    break;
	                case 0x08:
	                    str.append("<BS>");
	                    break;
	                case 0x09:
	                    str.append("<HT>");
	                    break;
	                case 0x0A:
	                    str.append("<LF>");
	                    break;
	                case 0x0B:
	                    str.append("<VT>");
	                    break;
	                case 0x0C:
	                    str.append("<FF>");
	                    break;
	                case 0x0D:
	                    str.append("<CR>");
	                    break;
	                case 0x0E:
	                    str.append("<SO>");
	                    break;
	                case 0x0F:
	                    str.append("<SI>");
	                    break;
	                case 0x10:
	                    str.append("<DLE>");
	                    break;
	                case 0x11:
	                    str.append("<DC1>");
	                    break;
	                case 0x12:
	                    str.append("<DC2>");
	                    break;
	                case 0x13:
	                    str.append("<DC3>");
	                    break;
	                case 0x14:
	                    str.append("<DC4>");
	                    break;
	                case 0x15:
	                    str.append("<NAK>");
	                    break;
	                case 0x16:
	                    str.append("<SYN>");
	                    break;
	                case 0x17:
	                    str.append("<ETB>");
	                    break;
	                case 0x18:
	                    str.append("<CAN>");
	                    break;
	                case 0x19:
	                    str.append("<EM>");
	                    break;
	                case 0x1A:
	                    str.append("<SUB>");
	                    break;
	                case 0x1B:
	                    str.append("<ESC>");
	                    break;
	                case 0x1C:
	                    str.append("<FS>");
	                    break;
	                case 0x1D:
	                    str.append("<GS>");
	                    break;
	                case 0x1E:
	                    str.append("<RS>");
	                    break;
	                case 0x1F:
	                    str.append("<US>");
	                    break;

	                default:
	                    str.append(toASCII(b));
	            }
	        }
	        return str.toString();
	    
	}
	
	 private static String toASCII(final byte valor) {
	        String cadena;

	        cadena = "" + (char) valor;
	        return cadena;
	    }
}
