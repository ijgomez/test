package org.example.test.server.jmx;

import java.io.IOException;

import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

public class MBeanDataDisplay {

	protected MBeanServerConnection server;
	protected TextDataDisplay dataDisplay = new TextDataDisplay();

	public MBeanDataDisplay(MBeanServerConnection conn) {
		this.server = conn;
	}

	StringBuffer writeAttribute(StringBuffer buffer, ObjectName mbean, MBeanAttributeInfo info, Object attribute) {
//		
		buffer.append("{");
		buffer.append("'name': '").append(info.getName()).append("', ");
		buffer.append("'description': '").append(info.getDescription()).append("', ");
		//buffer.append("'type': '").append(info.getType()).append("', ");
		
		buffer = dataDisplay.write(buffer, info.getName(), attribute);
		
		buffer.append("}");
		return buffer;
	}
	
	public StringBuffer write(StringBuffer buffer, ObjectName objectName) throws IOException, JMException {
		
		final MBeanInfo info = server.getMBeanInfo(objectName);
		
		buffer.append("{");
		buffer.append("'mBean': '").append(objectName).append("', ");
		buffer.append("'attributes': [");
		
		MBeanAttributeInfo[] attributeInfos = info.getAttributes();
		int count = 0;
		for (MBeanAttributeInfo attributeInfo : attributeInfos) {
			Object attribute = null;
			try {
				attribute = server.getAttribute(objectName, attributeInfo.getName());
				writeAttribute(buffer, objectName, attributeInfo, attribute);
			} catch (Exception e) {
				writeError(buffer, attributeInfo, e);
			}
			count++;
			if (count < attributeInfos.length) {
				buffer.append(", ");		
			}
			
		}

		buffer.append("]");
		buffer.append("}");
		
//		buffer.append(prefix).append("MBean: ").append(mbean).append("\\n");
//		buffer.append(prefix).append("{\\n");
//		final String attrPrefix = prefix + "   ";
//		final MBeanAttributeInfo[] attributes = info.getAttributes();
//		for (MBeanAttributeInfo attr : attributes) {
//			Object toWrite = null;
//			try {
//				toWrite = server.getAttribute(mbean, attr.getName());
//			} catch (Exception x) {
//				toWrite = x;
//			}
//			writeAttribute(buffer, attrPrefix, mbean, attr, toWrite);
//			buffer.append("\\n");
//		}
//		buffer.append(prefix).append("}\\n");
		return buffer;
		
	}

	

	private StringBuffer writeError(StringBuffer buffer, MBeanAttributeInfo info, Exception e) {
		buffer.append("{");
		buffer.append("'name': '").append(info.getName()).append("', ");
		buffer.append("'description': '").append(info.getDescription()).append("', ");
		buffer.append("'type': '").append(info.getType()).append("', ");
		buffer.append("'exception': {");
		
		buffer.append("'class': '").append(e.getClass()).append("', ");
		buffer.append("'message': '").append(e.getMessage()).append("' ");
		buffer.append("}");
		
		
		buffer.append("}");
		
		return buffer;
	}

	public String toString(ObjectName mbean) throws IOException, JMException {
		return write(new StringBuffer(), mbean).toString();
	}
}
