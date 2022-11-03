package app;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Repository <T> {
	public void save(T t) {
		var clazz = t.getClass();
		
		var fields = clazz.getDeclaredFields();
		
		// Procedural Approach
		for (var field: fields) {
			var annotations = field.getAnnotationsByType(Field.class);
			
			if (annotations.length > 0) {
				System.out.println(Arrays.asList(field));				
			}
		}
		
		// Stream API Approach
		// @formatter:on
		
		var fieldList = Arrays
			.stream(clazz.getDeclaredFields())
			.filter(f -> f.getAnnotationsByType(Field.class).length > 0)
			.collect(Collectors.toList());
		
		// @formatter:off
		
		System.out.println(fieldList);
	}
}
