package gov.va.vba.framework.domain.vo;


import java.io.Serializable;


/**
 * Defines a participant type in the BEP system
 *
 * @since	Nov 13, 2008
 * @version	
 * @author	Mario Rodrigues
 */
public enum ParticipantType implements Serializable {
	
	VETERAN('V'), USER('U');
	private final char participantType;

	ParticipantType(char participantType) {
		this.participantType = participantType;
	}

	public char getType() {
		return this.participantType;
	}
}

