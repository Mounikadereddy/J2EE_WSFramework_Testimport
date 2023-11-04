package gov.va.vba.framework;

import gov.va.vba.framework.services.CorrespondenceServiceV2Test;

public class LoadTester extends BaseTestCase{
	private static final int MAX_THREADS=50;
	static int i;
	public void testLoad()
	{
		for (i=0;i<MAX_THREADS;i++)
		{
			System.out.println("************** "+i);
			//Runnable thread=new InnerTest();
		 new Thread(new InnerThread()).start();
		}
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class InnerThread implements Runnable{

		@Override
		public void run() {
			new CorrespondenceServiceV2Test("").testGeneratePDF();
			
		}
		
	}
	
	class InnerTest extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int ii=1;ii<1000;ii++)
			{
				System.out.println("i="+i+" ii="+ii);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}


