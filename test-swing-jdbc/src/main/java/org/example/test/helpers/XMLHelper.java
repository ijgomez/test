package org.example.test.helpers;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public final class XMLHelper {

	public static void marshal(Object object, File file) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(object, file);
	}

	public static <T> T unmarshal(File file, Class<T> clazz) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Object object = jaxbUnmarshaller.unmarshal(file);

		return clazz.cast(object);
	}
}
