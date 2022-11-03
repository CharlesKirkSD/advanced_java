package app;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Repository <T> {
	public void save(T t) {
		var clazz = t.getClass();
		var classAnnotations = clazz.getDeclaredAnnotationsByType(Entity.class);
		var tableName = clazz.getSimpleName().toLowerCase(); // Returns the name of the class.
		
		if (classAnnotations.length > 0 && classAnnotations[0].value().length() > 0) {
			tableName = classAnnotations[0].value(); // Returns the name of the database table set in the User class @Entity Annotation.
		}
		
		var fields = clazz.getDeclaredFields();
		
		ArrayList<String> fieldsList = new ArrayList<>();
		
		// Procedural Approach
		for (var field: fields) {
			var annotations = field.getAnnotationsByType(Field.class);
			
			if (annotations.length == 0) {
				continue;			
			}
			
			var annotation = annotations[0];
			var fieldName = annotation.columnName();
			var isKey = annotation.isKey();
			
			if (fieldName.length() == 0) {
				fieldName = field.getName();
			}
			
			if (!isKey) {
				fieldsList.add(fieldName);
			}
		}
		
		String sqlFields = fieldsList.stream().collect(Collectors.joining(",")); // Join the field names in a comma separated String.
		String sqlPlaceholders = fieldsList.stream().map(f -> "?").collect(Collectors.joining(","));
				
		System.out.println(sqlFields);
		System.out.println(sqlPlaceholders);
		
		String sql = String.format("insert into %s (%s) values (%s)", tableName, sqlFields, sqlPlaceholders);
		System.out.println(sql);
	}
}
