package org.example.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Entidad> elementos = new ArrayList<Entidad>();
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());
		elementos.add(new Entidad());

		new Main().run(elementos);

	}

	private Map<String, Class<?>> objectInfo;

	public void run(List<?> elementos) {
		String method;

		this.objectInfo = null;
		if (elementos != null) {

			try {
				
				Long l = System.currentTimeMillis();
				
				for (Object object : elementos) {
					
					if (objectInfo == null) {
						buildObjectInfo(object);
					}

					for (String atributo : this.objectInfo.keySet()) {
						method = "get" + StringUtils.capitalize(atributo);
						Object value = MethodUtils.invokeExactMethod(object, method, null);
						if (value instanceof Collection<?>) {
							this.run((List<?>) value);
						} else {
							System.out.println(atributo + " => " + value);
						}

					}

				}
				System.out.println("MSeg: " + (System.currentTimeMillis() - l));

				
				
				Workbook wb = new HSSFWorkbook();
			    FileOutputStream fileOut = new FileOutputStream("target/workbook.xls");
			    wb.write(fileOut);
			    fileOut.close();
			    wb.close();
				System.out.println("MSeg: " + (System.currentTimeMillis() - l));

			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void buildObjectInfo(Object object) {
		Field[] declaredFields;

		this.objectInfo = new HashMap<String, Class<?>>();
		declaredFields = object.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if (!field.isSynthetic() && !(Modifier.isFinal(field.getModifiers()) && Modifier.isPrivate(field.getModifiers()))) {
				this.objectInfo.put(field.getName(), field.getType());
			}
		}
	}

}
