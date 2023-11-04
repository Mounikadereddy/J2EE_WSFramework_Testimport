package gov.va.vba.framework.services.ejb;

public enum ContentType {
	TEXT("text/plain"), HTML("text/html"), MULTIPART("multipart/mixed");
	final public String MIME;
	
	private ContentType(String mime){
		MIME = mime;
	}
	
}
