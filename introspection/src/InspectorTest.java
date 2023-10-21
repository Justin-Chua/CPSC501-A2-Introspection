
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InspectorTest {
	
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	Inspector inspector;
	
	@Before
	public void setup() {
		System.setOut(new PrintStream(out, true));
		inspector = new Inspector();
	}
	
	@Test
	public void testInspectDeclaringClass() {
		ClassA foo = new ClassA(10);
		inspector.inspectDeclaringClass(foo.getClass());
		assertEquals("Name of Declaring Class: \r\n", out.toString());
	}
	
	@Test
	public void testInspectSuperclass() {
		ClassA foo = new ClassA(8);
		inspector.inspectSuperclass(foo.getClass());
		assertEquals("Name of Superclass: java.lang.Object\r\n"
				+ "Implemented Interfaces:\r\n"
				+ "\tjava.io.Serializable\r\n"
				+ "\tjava.lang.Runnable\r\n", out.toString());
	}
	
	@Test
	public void testInspectDeclaredMethods() {
		ClassA foo = new ClassA(14);
		inspector.inspectDeclaredMethods(foo.getClass());
		assertEquals("Declared Methods:\r\n"
				+ "\trun\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: void\r\n"
				+ "\t Modifiers: public\r\n"
				+ "\ttoString\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: java.lang.String\r\n"
				+ "\t Modifiers: public\r\n"
				+ "\tsetVal\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t\tjava.lang.Exception\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t\tint\r\n"
				+ "\t Return Type: void\r\n"
				+ "\t Modifiers: public\r\n"
				+ "\tprintSomething\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: void\r\n"
				+ "\t Modifiers: private\r\n"
				+ "\tgetVal\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: int\r\n"
				+ "\t Modifiers: public\r\n"
				, out.toString());
	}
	
	@Test
	public void testDeclaredConstructors() {
		ClassA foo = new ClassA(18);
		inspector.inspectDeclaredMethods(foo.getClass());
		assertEquals("Declared Methods:\r\n"
				+ "\trun\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: void\r\n"
				+ "\t Modifiers: public\r\n"
				+ "\ttoString\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: java.lang.String\r\n"
				+ "\t Modifiers: public\r\n"
				+ "\tsetVal\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t\tjava.lang.Exception\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t\tint\r\n"
				+ "\t Return Type: void\r\n"
				+ "\t Modifiers: public\r\n"
				+ "\tprintSomething\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: void\r\n"
				+ "\t Modifiers: private\r\n"
				+ "\tgetVal\r\n"
				+ "\t Exceptions:\r\n"
				+ "\t Parameter Types:\r\n"
				+ "\t Return Type: int\r\n"
				+ "\t Modifiers: public\r\n"
				, out.toString());
	}
	
	@Test
	public void testInspectObjectNull() {
		List<Object> objectsInspected = new ArrayList<Object>();
		inspector.inspectObject(null, null, objectsInspected, true);
		assertEquals("", out.toString());
	}
	
	// did not have time to implement tests for recursive cases - inspectFields() and inspectObject()
}
