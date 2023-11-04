package gov.va.vba.framework.css.cssiam.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MissingHeaderExceptionTest {
	
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
		expectedEx.expect(MissingHeaderException.class);
		expectedEx.expectMessage(message);
		throw new MissingHeaderException(message);
	}
	
}
