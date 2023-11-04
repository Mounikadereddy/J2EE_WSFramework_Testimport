package gov.va.vba.framework.serverconfig;


public class LoggerStartup {

public static void main(String[] args)
{
	/*
	for (int i=0;i<20;i++)
	{
		java.awt.Toolkit.getDefaultToolkit().beep();
	}
	*/
	System.out.println("in LoggerStartup");
	LoggerConfigurator.firstTimeSetup();
}

}
