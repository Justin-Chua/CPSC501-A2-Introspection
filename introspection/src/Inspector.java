import java.util.*;
import java.lang.reflect.*;

public class Inspector {
	
	public void inspectDeclaringClass(Class classObject) {
		// initialize String used to hold name of declaring class of obj
		Class declaringClass;
		// here we try to fetch the name of the declaring class of obj if it exists
		try {
			declaringClass = classObject.getDeclaringClass();
			// print out the name of the declaring class
			System.out.println("Name of Declaring Class:" + declaringClass.getName());
		} catch (SecurityException | NullPointerException e) { }
	}
	
	public void inspectSuperclass(Class classObject) {
		// get the immediate superclass of our object
		Class superclass = classObject.getSuperclass();
		// print out the name of the superclass
		System.out.println("Name of Superclass: " + superclass.getName());
		
		// print out header title for the interfaces of the class
		System.out.println("Implemented Interfaces:");
		// get array of all interfaces that are implemented by classObject
		Class[] classInterfaces = classObject.getInterfaces();
		// iterate through each element in classInterfaces
		for (Class classInterface : classInterfaces)  {
			// print out name of interface, with leading indent
			System.out.println("\t" + classInterface.getName());
		}
	}
	
	public void inspectDeclaredMethods(Class classObject) {
		// print out header title for methods declared by class
		System.out.println("Declared Methods:");
		// use getDeclaredMethods() to get all of the methods that the class declares
		Method[] classMethods = classObject.getDeclaredMethods();
		// iterate through each method in classMethods - method being inspected is classMethod
		for (Method classMethod : classMethods) {
			// here we try to set the method to be accessible in case if it is non-public
			try {
				classMethod.setAccessible(true);
			} catch (SecurityException | InaccessibleObjectException e) {
				// print message stating current method is inaccessible
				System.out.println("\t" + classMethod.getName() + " is inaccessible");
				// continue to the next class method
				continue;
			}
			// print out the name of classMethod
			System.out.println("\t" + classMethod.getName());
			// use getExceptionTypes() to get all of the exceptions thrown by classMethod
			Class[] classMethodExceptions = classMethod.getExceptionTypes();
			// print out header title for exceptions of classMethod
			System.out.println("\t Exceptions:");
			// iterate through each element in classMethodExceptions
			for (Class classMethodException : classMethodExceptions) {
				// print out the name of each exception for classMethod
				System.out.println("\t\t" + classMethodException.getName());
			}
			
			// use getParameterTypes() to get all of the parameter types of classMethod
			Class[] classMethodParameterTypes = classMethod.getParameterTypes();
			// print out header title for parameter types of classMethod
			System.out.println("\t Parameter Types:");
			// iterate through each element in classMethodParameterTypes
			for (Class classMethodParameterType : classMethodParameterTypes) {
				// print out the name of each parameter type for classMethod
				System.out.println("\t\t" + classMethodParameterType.getName());
			}
			
			// use getReturnType() to get return type of classMethod
			Class classMethodReturnType = classMethod.getReturnType();
			// print out the return type of classMethod
			System.out.println("\t Return Type: " + classMethodReturnType.getName());
			
			// use getModifiers() to get the int representaiton of modifiers applied to classMethod
			int classMethodModifiers = classMethod.getModifiers();
			// print out the modifiers applied to classMethod, using toString from Modifier class
			System.out.println("\t Modifiers: " + Modifier.toString(classMethodModifiers));
		}
	}
	
	public void inspectDeclaredConstructors(Class classObject) {
		// print out header title for declared constructors of class
		System.out.println("Declared Constructors:");
		// use getDeclaredConstructors() to get all of the constructors the class declares
		Constructor[] classConstructors = classObject.getDeclaredConstructors();
		// iterate through each element in classConstructors
		for (Constructor classConstructor : classConstructors) {
			// print out the name of classConstructor
			System.out.println("\t" + classConstructor.getName());
			
			// use getParameterTypes() to get all of the parameter types of classConstructor
			Class[] classConstructorParameterTypes = classConstructor.getParameterTypes();
			// print out header title for parameter types of classMethod
			System.out.println("\t Parameter Types:");
			// iterate through each element in classMethodParameterTypes
			for (Class classConstructorParameterType : classConstructorParameterTypes) {
				// print out the name of each parameter type for classMethod
				System.out.println("\t\t" + classConstructorParameterType.getName());
			}
			
			// use getModifiers() to get the int representation of modifiers applied to classConstructor
			int classConstructorModifiers = classConstructor.getModifiers();
			// print out the modifiers applied to classConstructor, using toString from Modifier class
			System.out.println("\t Modifiers: " + Modifier.toString(classConstructorModifiers));
		}
	}
	
	public void inspectFields (Object obj, Class classObject) {
		// print out header title for declared fields of class
		System.out.println("Declared Fields:");
		// use getFields() to get all of the fields the class declares
		Field[] classFields = classObject.getDeclaredFields();
		// iterate through each element in classFields
		for (Field classField : classFields) {
			// here we try to set the field to be accessible in case if it is non-public
			try {
				classField.setAccessible(true);
			} catch (SecurityException | InaccessibleObjectException e) {
				// print message stating current method is inaccessible
				System.out.println("\t" + classField.getName() + " is inaccessible");
				// continue to the next class method
				continue;
			}
			// print out the name of classField
			System.out.println("\t" + classField.getName());
			// use getType() to get the type of classField
			Class classFieldType = classField.getType();
			// print out the type of classField
			System.out.println("\t Type: " + classFieldType.getName());
			
			// use getModifiers() to get the int representation of modifiers applied to classField
			int classFieldModifiers = classField.getModifiers();
			// print out the modifiers applied to classField, using toString from Modifier class
			System.out.println("\t Modifiers: " + Modifier.toString(classFieldModifiers));
			
			// check if classField is an array using isArray(), as these are handled differently
			if (classFieldType.isArray()) {
				// get component type of classField using getComponentType()
				Class classFieldComponentType = classFieldType.getComponentType();
				// print out the component type of classField
				System.out.println("\t Component Type: " + classFieldComponentType.getName());
				// try to get length of classField using Array.getLength()
				try {
					int classFieldLength = Array.getLength(classField.get(obj));
					// print out the length of classField
					System.out.println("\t Length: " + classFieldLength);
					// print out components of classField
					List<Object> classFieldArray = new ArrayList<Object>();
					for (int i = 0; i < classFieldLength; i++)
						classFieldArray.add(Array.get(classField.get(obj), i));
					System.out.println("\t Contents: " + classFieldArray);
					
				} catch (IllegalAccessException e) { }
			} else {
				// invoke get() on classField to attempt to fetch value of the field
				try {
					Object classFieldValue = classField.get(obj);
					// print out the value of classField
					System.out.println("\t Value: " + classFieldValue);
				} catch (IllegalAccessException e) { }
			}
		}
	}
	
	public void inspect(Object obj, boolean recursive) {
		
		// print out name of object being inspected during this execution
		System.out.println("Object Inspected: " + obj);
		// print out current status of recursion
		System.out.println("Recursion Status: " + recursive);
		
		// use getClass() to get Class object from obj
		Class classObject = obj.getClass();
		
		inspectDeclaringClass(classObject);
		
		inspectSuperclass(classObject);
		
		inspectDeclaredMethods(classObject);
		
		inspectDeclaredConstructors(classObject);
		
		inspectFields(obj, classObject);
		
	}
}
