package org.novelveritasPatch.rfc;

public class RFCConnectMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GetSAPData call = new GetSAPData();
		try {
			call.saveSAPData("call");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
