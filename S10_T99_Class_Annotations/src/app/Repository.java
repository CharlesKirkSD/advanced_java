package app;

public class Repository <T> {
	public void save(T t) {
		var clazz = t.getClass();
		var classAnnotations = clazz.getDeclaredAnnotationsByType(Entity.class);
		var tableName = clazz.getSimpleName().toLowerCase(); // Returns the name of the class.
		
		System.out.println(tableName);
		
		if (classAnnotations.length > 0 && classAnnotations[0].value().length() > 0) {
			tableName = classAnnotations[0].value(); // Returns the name of the database table set in the User class @Entity Annotation.
		}
		
		System.out.println(tableName);
		
		var fields = clazz.getDeclaredFields();
		
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
			
			System.out.println(fieldName + " " + isKey);
		}
	}
}
