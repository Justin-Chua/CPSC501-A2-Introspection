import java.lang.reflect.*;

public class Inspector {
	public void inspect(Object obj, boolean recursive) {
		
		// print out name of object being inspected during this execution
		System.out.println("Object Inspected: " + obj);
		// print out current status of recursion
		System.out.println("Recursion Status: " + recursive);
		
		// use getClass() to get Class object from obj
		Class classObject = obj.getClass();
		
		// initialize String used to hold name of declaring class of obj
		String nameOfDeclaringClass = "";
		// here we try to fetch the name of the declaring class of obj if it exists
		try {
			nameOfDeclaringClass = classObject.getDeclaringClass().getName();
		} catch (SecurityException | NullPointerException e) { }
		// print out the name of the declaring class
		System.out.println("Name of Declaring Class:" + nameOfDeclaringClass);
		
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
				for (Class classMethodParameterType : classMethodParameterTypes) {
					// print out the name of each parameter type for classMethod
					System.out.println("\t\t" + classMethodParameterType.getName());
				}
				
				// use getModifiers() to get the int representation of modifiers applied to classConstructor
				int classConstructorModifiers = classConstructor.getModifiers();
				// print out the modifiers applied to classConstructor, using toString from Modifier class
				System.out.println("\t Modifiers: " + Modifier.toString(classConstructorModifiers));
			}
		}
		
	}
}
