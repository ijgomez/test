package org.example.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ExportacionService {

	public static final ExportacionService INSTANCIA = new ExportacionService();
	
	private ExportacionService() { }
	
	public void exportar(List<?> datos) {
		boolean primeraFila = true;
		
		
		
		for (Object o : datos) {
			if (primeraFila) {
				valid(o);
				//System.out.println("Imprimir Header");
				primeraFila = false;
			} 
				
			//System.out.println("Imprimir Data");
			
			
		}
		
		
//
//        ParameterizedType parameterizedType = (ParameterizedType) datos.getClass().getGenericSuperclass();
//        
//        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
//        
//        
//
//
//		
//		
//		System.out.println("getClass() : " + datos.getClass());
//		
//		System.out.println("getGenericSuperclass() : " + datos.getClass().getGenericSuperclass());
//		
//
////		
//		ParameterizedType superclass = (ParameterizedType) datos.getClass().getGenericSuperclass();
//		
//		Type rawType = superclass.getRawType();
//		
////        Type[] types = superclass.getActualTypeArguments();  
////        Class<?> actualdataType = null;  
////        if(types != null && types.length >0 && (types[0] instanceof Class<?>) ) {  
////            actualdataType = (Class<?>) (Class<?>) types[0];  
////        }  
////        System.out.println("actualdataType = " + actualdataType);  
//		
	}

	private boolean valid(Object o) {
		
		if (o != null) {
			
			Class<? extends Object> clazz = o.getClass();
			
			System.out.println(clazz);
			
			ExportacionCSV annotationClass = clazz.getAnnotation(ExportacionCSV.class);
			 
			System.out.println("Nombre del Fichero: " + annotationClass.name()); 
			
			Field[] fields = clazz.getDeclaredFields();
			
			for (Field field : fields) {
				
				System.out.println(field.getName());
				
				ExportacionColumn annotationField = field.getAnnotation(ExportacionColumn.class);
				
				System.out.println(annotationField);
				
				try {
					Method method = clazz.getMethod("get" + StringUtils.capitalize(field.getName()));
					
					
					
					System.out.println(method.invoke(o));
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			
			// TODO Auto-generated method stub			
		
			return true;
		}
		throw new RuntimeException("Collection not valid...");

		
	}


	
}


