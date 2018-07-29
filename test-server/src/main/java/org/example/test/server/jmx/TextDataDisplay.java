package org.example.test.server.jmx;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;

public class TextDataDisplay {

	public TextDataDisplay() {
	}

	public String display(String name, Object data) {
		return display("", name, data);
	}

	public String display(String prefix, String name, Object data) {
		if (name == null)
			throw new IllegalArgumentException("name must not be null");
		final StringBuffer buffer = new StringBuffer();
		return write(buffer, name, data).toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StringBuffer write(StringBuffer buffer, String name, Object attribute) {
		if (attribute == null) {
			return writeSimple(buffer, null, name, null, true);

		} else if (attribute.getClass().isArray()) {
			return writeArray(buffer, null, name, attribute);

		} else if (attribute instanceof CompositeData) {
			return writeCompositeData(buffer, null, name, (CompositeData) attribute);

		} else if (attribute instanceof TabularData) {
			return writeTabularData(buffer, null, name, (TabularData) attribute);

		} else if (attribute instanceof Map) {
			return writeMap(buffer, null, name, (Map) attribute);

		} else if (attribute instanceof Collection) {
			return writeArray(buffer, null, name, ((Collection) attribute).toArray());

		} else {
			return writeSimple(buffer, null, name, attribute, true);
		}
	}

	public String toString(Object data) {
		if (data == null)
			return "null";
		else
			return data.toString();
	}

	public StringBuffer writeSimple(StringBuffer buffer, String prefix, String name, Object data, boolean writeline) {
		if (name == null) {
			buffer.append("'").append(toString(data)).append("'");
		} else {
			buffer.append("'").append(name).append("': '").append(toString(data)).append("'");
		}
		// buffer.append(", ");
		return buffer;
	}

	public StringBuffer writeArray(StringBuffer buffer, String prefix, String name, Object array) {
		if (array == null)
			return writeSimple(buffer, prefix, name, null, true);

		buffer.append("'").append(name).append("': [");
		final int length = Array.getLength(array);
		for (int i = 0; i < length; i++) {
			final Object data = Array.get(array, i);
			write(buffer, null, data);
			if (i < length - 1) {
				buffer.append(", ");
			}
		}
		buffer.append("]");
		return buffer;
	}

	public StringBuffer writeCompositeData(StringBuffer buffer, String prefix, String name, CompositeData data) {
		if (data == null)
			return writeSimple(buffer, null, name, null, true);

		if (name != null) {
			buffer.append("'").append(name).append("': ");
		}
		buffer.append(" {");

		// writeSimple(buffer,prefix,name,"CompositeData("+
		// data.getCompositeType().getTypeName()+")",true);
		// buffer.append(prefix).append("{").append("\\n");
		// final String fieldprefix = prefix + " ";
		Set<String> keySet = data.getCompositeType().keySet();
		int count = 0;
		for (String key : keySet) {
			write(buffer, key, data.get(key));
			count++;
			if (count < keySet.size()) {
				buffer.append(", ");
			}
		}
		// buffer.append(prefix).append("}").append("\\n");
		buffer.append("}");
		return buffer;
	}

	public StringBuffer writeTabularData(StringBuffer buffer, String prefix, String name, TabularData data) {
		if (data == null)
			return writeSimple(buffer, null, name, null, true);

		buffer.append("'").append(name).append("': {");
		// writeSimple(buffer,prefix,name,"TabularData("+
		// data.getTabularType().getTypeName()+")",true);

		final List<String> keyNames = data.getTabularType().getIndexNames();
		final int indexCount = keyNames.size();
//		int count = 0;
		for (Object keys : data.keySet()) {
			final Object[] keyValues = ((List<?>) keys).toArray();
			final StringBuilder b = new StringBuilder(name);
			b.append("[");
			for (int i = 0; i < indexCount; i++) {
				if (i > 0)
					b.append(", ");
				b.append(keyNames.get(i) + "=" + keyValues[i]);
			}
			b.append("]");
			writeCompositeData(buffer, null, b.toString(), data.get(keyValues));
			buffer.append(",");
			// count++;
			// if (count < data.keySet().size()) {
			// buffer.append(",");
			// }
		}
		buffer.append("}");
		return buffer;
	}

	public StringBuffer writeMap(StringBuffer buffer, String prefix, String name, Map<Object, Object> data) {
		if (data == null) {
			return writeSimple(buffer, prefix, name, null, true);
		}
		buffer.append("'").append(name).append("': {");

		// writeSimple(buffer,prefix,name,"java.util.Map",true);
		for (Entry<Object, Object> e : data.entrySet()) {
			// write(buffer,name+"["+e.getKey()+"]",e.getValue());

			write(buffer, "" + e.getKey(), e.getValue());

		}
		buffer.append("}");

		return buffer;
	}
}
