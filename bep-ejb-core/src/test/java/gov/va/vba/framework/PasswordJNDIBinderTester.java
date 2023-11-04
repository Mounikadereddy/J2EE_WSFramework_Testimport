package gov.va.vba.framework;

import gov.va.vba.framework.common.AuthenticationHelper;

public class PasswordJNDIBinderTester extends BaseTestCase {
	
	public void testLDAPPassword()
	{
		String username=AuthenticationHelper.getUsername("LDAP");
		String password=AuthenticationHelper.getPassword("LDAP");

	}

}
