
public class Inspector {
	public void inspect(Object obj, boolean recursive) {
		
		// print out name of object being inspected during this execution
		System.out.println("Object Inspected: " + obj);
		// print out current status of recursion
		System.out.println("Recursion Status: " + recursive);
		
		// use getClass() to get Class object from obj
		Class classObject = obj.getClass();
		
		// here we add a try-catch block to see if classObject has a declaring class
		// print out the name of the declaring class
		System.out.println("Name of Declaring Class:" + classObject.getDeclaringClass().getName());
		
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
}
