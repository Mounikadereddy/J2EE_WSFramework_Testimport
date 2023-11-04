package gov.va.vba.framework.css.cssiam.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UnrecognizedApplicationExceptionTest {
	
	String message = "Testing message"; 
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
		
		
	}
	
	@Test
	public void testException() throws Exception {
		expectedEx.expect(UnrecognizedApplicationException.class);
		expectedEx.expectMessage(message);
		throw new UnrecognizedApplicationException(message);
	}
}
