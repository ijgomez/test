package org.example.test.helper;

public class ByteArrayUtil {

	public static String toASCII(byte[] data) {

    	StringBuffer str = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
			byte b = data[i];
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

	 private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	 
	 public static String toHexadecimal(byte[] data) {
		 char[] hexChars = new char[data.length * 2];
		    int v;
		    for (int j = 0; j < data.length; j++) {
		      v = data[j] & 0xFF;
		      hexChars[j * 2] = HEX_CHARS[v >>> 4];
		      hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F];
		    }
		    return new String(hexChars);
		}
	 
}
