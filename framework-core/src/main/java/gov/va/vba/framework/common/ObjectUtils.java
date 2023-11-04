package gov.va.vba.framework.common;

import gov.va.vba.framework.exceptions.BaseRuntimeException;

import java.lang.reflect.Field;

public class ObjectUtils {
	public static void defaultFieldsToString(Object object, String defaultValue){
		Field[] fields = object.getClass().getDeclaredFields();
		
		if(fields == null) return;
		try {			
			for (Field field: fields){
				if(!field.getType().equals(String.class)) { continue; }
				boolean changed = false;
				if(!field.isAccessible()) {
					field.setAccessible(true);
					changed = true;
				}
				if(field.get(object) == null) field.set(object, defaultValue);
				if(changed) {
					field.setAccessible(false);
				}
			} 
		} catch (Exception e) {
			throw new BaseRuntimeException("Exception setting up default strings on object", e);
		}
	}
}
