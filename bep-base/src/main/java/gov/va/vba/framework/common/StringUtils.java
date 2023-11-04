package gov.va.vba.framework.common;


import org.apache.commons.lang.text.StrBuilder;


/*
 * This class is to add more String utilities that are not included in
 * the Apache.commons.StringUtils class.
 * @since	Dec 9, 2010
 * @version	
 * @author	Joshua Glickman
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{
	/**
	 * Pads the String <code>str</code> with <code>size</code> spaces. Apache's StringUtils
	 * does not truncate the input string if it's larger than the size.
	 * 
	 * @param 	str
	 * @param 	size
	 * @return	String
	 * @since	Dec 9, 2010
	 */
	public static String rightPadSubstr(String str, int size) {
		

    	StringBuilder strBldr = new StringBuilder(str==null? "":str);
    	for (int i=strBldr.length(); i<size;i++) 
    		strBldr.append('\u0020');
    	return strBldr.substring(0, size);
	}
	
	public static boolean isEmpty(String string) 
	{
		boolean empty=string==null || org.apache.commons.lang.StringUtils.isEmpty(string);
		return empty;
	}




    public static String normalizeSpace(String str) {
        str = strip(str);
        if (str != null && str.length() > 2) {
            StrBuilder b = new StrBuilder(str.length());

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (Character.isWhitespace(c)) {
                    if (i > 0 && !Character.isWhitespace(str.charAt(i - 1))) {
                        b.append(' ');
                    }
                } else {
                    b.append(c);
                }
            }

            return b.toString();
        } else {
            return str;
        }
    }

}
