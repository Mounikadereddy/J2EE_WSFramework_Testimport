/*
 * CommonSecurityService.java
 *
 * Copyright 2008 U.S. Dept Of Veterans Affairs.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.services;


import gov.va.vba.framework.domain.vo.ServiceVO;
import gov.va.vba.framework.domain.vo.ValueObject;
import gov.va.vba.framework.esb.transformers.TuxedoSecurityProfile;

import java.util.Map;


/**
 * 
 *
 * @since	Aug 21, 2008
 * @version	
 * @author	Mario Rodrigues
 */
public interface CommonSecurityService {	
	
	TuxedoSecurityProfile getSecurityProfile(ServiceVO vo) throws TuxedoException;
	
	Map<Byte, String> changePassword(ValueObject vo, String[] smMessageFields) 
		throws CommonSecurityException;

	int updateRetryCount(ValueObject vo) throws CommonSecurityException;
		
}
