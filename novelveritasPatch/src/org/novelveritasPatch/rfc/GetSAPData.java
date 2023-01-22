package org.novelveritasPatch.rfc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/


import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.IRepository;
import com.sap.mw.jco.JCO;

public class GetSAPData {

	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();
	public static int YEAR = 1900;
	private boolean isDispatch=true;
	public StringBuffer mailMessage = new StringBuffer(""); 
/*	private Timestamp p_DateAcct_From;

	public Timestamp getP_DateAcct_From() {
		return p_DateAcct_From;
	}
	public void setP_DateAcct_From(Timestamp p_DateAcct_From) {
		this.p_DateAcct_From = p_DateAcct_From;
	}
	public Timestamp getP_DateAcct_To() {
		return p_DateAcct_To;
	}
	public void setP_DateAcct_To(Timestamp p_DateAcct_To) {
		this.p_DateAcct_To = p_DateAcct_To;
	}
	private Timestamp p_DateAcct_To;
	
	private boolean isPullMaster;
	
	
	   public boolean isPullMaster() {
		return isPullMaster;
	}
	public void setPullMaster(boolean isPullMaster) {
		this.isPullMaster = isPullMaster;
	}*/
	
	
	public Date from;
	public Date to;
	
	public Time fromTime;
	public Time toTime;
	
	private boolean isPullMaster;
	public int noOfRecords;
	public boolean executeCondition;
	public boolean isExecuteCondition() {
		return executeCondition;
	}
	public void setExecuteCondition(boolean executeCondition) {
		this.executeCondition = executeCondition;
	}
	public int getNoOfRecords() {
		return noOfRecords;
	}
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	
	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}
	
	public Time getToTime() {
		return toTime;
	}
	
	public Time getFromTime() {
		return fromTime;
	}
	
	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}
	
	   public boolean isPullMaster() {
		return isPullMaster;
	}
	public void setPullMaster(boolean isPullMaster) {
		this.isPullMaster = isPullMaster;
	}
	
	private boolean isOnlyHighVol;
	
	public boolean isOnlyHighVol() {
		return isOnlyHighVol;
	}
	public boolean isDispatch() {
		return isDispatch;
	}
	public void setDispatch(boolean isDispatch) {
		this.isDispatch = isDispatch;
	}
	public void setOnlyHighVol(boolean isOnlyHighVol) {
		this.isOnlyHighVol = isOnlyHighVol;
	}
	 
	
	/*List<MImportConfig> imports = new Query(getCtx(), MImportConfig.Table_Name, " isImport='Y' ", get_TrxName()).setOnlyActiveRecords(true).list(); 
	List<String> tables = new LinkedList<String>(); 
	for(MImportConfig config : imports)
	{
		System.out.println(config.getAD_Table().getName()+" ksndfksmdf");
		tables.add(config.getAD_Table().getName());
	}*/
	
	public void saveSAPData(String trx) throws Exception
	   {
		   Exception sapException = null;
		   IRepository mRepository = null;
		   JCO.Function jcoFunction=null;
		   SapConnection sapConnection=null;
		   sapConnection = new SapConnection();
		   
		   
		   String SAPClient ="450";
		   String SAPUser ="COMM";
		   String HostName = "rcmappdev";//10.255.13.6 - rcmappdev -103.25.172.189
		   String Password= "pass,123";
		   String SysID = "03";
		   String LanguageISO = "EN";
//		   
//		   String SAPClient ="010";
//		   String SAPUser ="E-CRM_PORTAL";
//		   String HostName = "10.1.1.111";
//		   String Password= "Pass@123";
//		   String SysID = "00";
//		   String LanguageISO = "EN";
//		   
		   
		   System.out.println("Host Connect " +HostName + "user " + SAPUser + " passowrd" +Password);
		// if( MSysConfig.getBooleanValue("PRINT_CONS", false, 11))
		  	System.out.println("Host " +HostName + "user " + SAPUser + " passowrd" +Password);	   
		   /*
		     * Production
		     * 
		     **/ sapException = sapConnection.Connect( SAPClient,       // SAP client
		    		 SAPUser,   // userid
		    		 Password,     // password
		    		 LanguageISO,        // language
					 HostName, // host name
					 SysID);   //System

		   
		   
		    if ( sapException == null )
		    {
		    	JCO.Client connection = SapConnection.mConnection;
		    	connection.endAuthorizationTracing();
		    	connection.connect();
		    	connection.endAuthorizationTracing();
		    	 try
		         {
		    		 mRepository = new JCO.Repository("hFrank", sapConnection.mConnection); 
		         }
		        catch (Exception mException)
		         { 
		           mException.printStackTrace();
		           throw mException;
		         }

		    	 try
		         { // Get a function template from the repository   0000006973
		    		 
		    		 
		    		 //for all tables pulling
		    		 IFunctionTemplate ftemplate = mRepository.getFunctionTemplate("ZJAVA_TAB_SEND_BI");
		    		 
		           	//BAPI_SALESORDER_GETSTATUS   ZIFM_ITS_PENDINGCF
		           // Create a function from the template
		           jcoFunction = new JCO.Function(ftemplate);
		           
		           if(jcoFunction!=null)
		        	   System.out.println("Function Found");
		         }
		        catch (Exception mException)
		         { //mException.printStackTrace();
		           mException.printStackTrace();
		           throw mException;
		         }
		    	 
		    	 System.out.println(""+ jcoFunction.getTableParameterList().getTabName(0));
		    	 		    	 
		    	//---------------------------------------------------------
		    	   // Set the SALESDOCUMENT import parameter
		    	   //---------------------------------------------------------
		    	   try
		    	     { 
		    		   
		    		   //Date Range
		    		   
		    		   /*JCO.Table tab  = jcoFunction.getImportParameterList().getTable("DATE");
		    		   tab.appendRow();
		    		   tab.setValue(from, "LOW");
		    		   tab.setValue(to, "HIGH");
		    		   tab.setValue("BT", "OPTION");
		    		   tab.setValue("I", "SIGN");*/
		    		   
		    		   //Time
		    		   
		    		   /*if(fromTime!=null && toTime==null)
		    		   {
		    			   
//		    			   toTime = new Time(115199000+999);
		    			   toTime = new Time(fromTime.getTime());
		    			   toTime.setHours(23);
		    			   toTime.setMinutes(59);
		    			   toTime.setSeconds(59);
		    			   System.out.println(" 1 GetSAPData if(fromTime!=null && toTime==null) is True toTime :"+toTime);
		    		   }
		    		   if(fromTime==null && toTime!=null)
		    		   {
//		    			   fromTime = new Time(115199000+1000);
		    			   fromTime = new Time(toTime.getTime());
		    			   fromTime.setHours(0);
		    			   fromTime.setMinutes(0);
		    			   fromTime.setSeconds(0);
		    			   
		    			   System.out.println(" 2 GetSAPData  if(fromTime==null && toTime!=null) is True fromTime :"+fromTime);
		    		   }
		    		   System.out.println("--------------From Date  "+from+" From Time "+fromTime+" To Date "+to+" To Time "+toTime);
		    		   
		    		   if(fromTime!=null && toTime!=null)
		    		   {
		    			   tab  = jcoFunction.getImportParameterList().getTable("TIME");
		    			   tab.appendRow();
		    		   
		    			   tab.setValue(fromTime, "LOW");
		    			   tab.setValue(toTime, "HIGH");
		    			   tab.setValue("BT", "OPTION");
		    			   tab.setValue("I", "SIGN");
		    		   }*/
		    		   
		    		   //Master
		    		   
		    		   //JCO.Field master =  jcoFunction.getImportParameterList().getField("MASTER");
		    		  // JCO.Field milestone =  jcoFunction.getImportParameterList().getField("MILESTONE");
		    		  // milestone.setValue("Y");
		    		   
//		    		   JCO.Field milestone =  jcoFunction.getImportParameterList().getField("VKORG");
//			    		 milestone.setValue("1051");
		    		  /* System.out.println("master.getString::::::"+master.getString());
		    		   System.out.println("master.toString::::::"+master.toString());
		    		   System.out.println("master::::::"+isPullMaster);
		    		   
		    		   if(isPullMaster)
		    		   {
		    			   System.out.println("isPullMaster::::::"+isPullMaster);
		    			   master.setValue("X");
		    			  
		    		   }*/
		    		   
		    	     }
		    	   catch (Exception mException)
		    	     { 
		    		   mException.printStackTrace();
		    		   throw mException;
		    	     }


		    	   //---------------------------------------------------------
		    	   // Execute function
		    	   //---------------------------------------------------------

		    	    try
		    	     { 
		    	    	System.out.println("Execution started. . .");
		    	    sapConnection.mConnection.execute(jcoFunction);
		    	     System.out.println("Execution end. . .");
		    	     }
		    	    catch (Exception mException)
		    	     { //mException.printStackTrace();
		    	    	System.out.println("Exception in execution");
		    	       mException.printStackTrace();
		    	       throw mException;
		    	     }
		    	    System.out.println("after execution");
		    	   //---------------------------------------------------------
		    	   // Get sales order status. Item info is saved in the
		    	   // array itemData.
		    	   //---------------------------------------------------------

		    	   try
		    	     { 
		    		   //IT_VBAK
		    		  save(jcoFunction,trx);
		    		   	  	   
		    		    /*int numRows = jcoStatusInfo.getNumRows();
		    		    int numColumns=jcoStatusInfo.getNumColumns();*/
		    		  JCO.Table jcoStatusInfo = null;
		    	       int numRows = 0;
//		    	       System.out.println("NumRows ----- "+numRows+" name"+jcoStatusInfo.getName());
		    	       Connection conn =  null;
		    	       Statement stm =null;
		    	       int x=0;
			    			System.out.println(jcoFunction.getTableParameterList());  
	    		    jcoStatusInfo = jcoFunction.getTableParameterList().getTable("OUTPUT");
	    	        numRows = jcoStatusInfo.getNumRows();boolean error = false;
	    	        System.out.println("NumRows ----- "+numRows+" name"+jcoStatusInfo.getName());
		    	     	
		    			}
		    	   catch (Exception mException)
		    	      {
		    		   mException.printStackTrace();
		    	      }

		    	   finally {
		    		   sapConnection.mConnection.disconnect();
		    	   }
		    	   //---------------------------------------------------------
		    	   // Get BAPIRETURN
		    	   //---------------------------------------------------------

		    	  /* try
		    	     { JCO.Structure jcoBapiReturn = jcoFunction.getExportParameterList().getStructure("RETURN");

		    	       oBapiReturn = jcoBapiReturn.getField("TYPE").getValue() + " " +
		    	                     jcoBapiReturn.getField("MESSAGE").getValue();
		    	     }
		    	   catch (Exception mException)
		    	     { throw new BapiReturnException();
		    	     }
	*/	    	 /*Added by Satheesh*/
		    	
		    	   if(mailMessage.length()>1)
		    	   {
		    		   //Mail(Env.getCtx(), mailMessage.toString(), from,fromTime ,to,toTime);
		    	   }
		    	   /*END*/

		    }
		    
		    else
		    { 
		    	System.out.println("Exception "+sapException);
		    	throw sapException;
		    }
	   }
		    
public void save(JCO.Function jcoFunction,String trx){

try{
	System.out.println();
	JCO.Table jcoStatusInfo = null;
    int numRows = 0;
    int numColumns=0;
//    System.out.println("NumRows ----- "+numRows+" name"+jcoStatusInfo.getName());
    Connection conn =  null;
    Statement stm =null;
    int x=0;
    /*List<MImportConfig> imports = new Query(Env.getCtx(), MImportConfig.Table_Name, " isImport='Y' ", trx).setOnlyActiveRecords(true).list(); 
	   List<String> tables = new LinkedList<String>(); 
		for(MImportConfig config : imports)
		{
			//System.out.println(config.getAD_Table().getName()+" ksndfksmdfwww");
			tables.add(config.getAD_Table().getName());
		}*/
	
    
    
    
		
}
catch (Exception mException)
{
 mException.printStackTrace();
}
}

public String randomString(int len){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
}


public boolean isThisDateValid(String dateToValidate, String dateFromat){

	if(dateToValidate == null){
		return false;
	}

	SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
	sdf.setLenient(false);

	try {

		//if not valid, it will throw ParseException
		sdf.parse(dateToValidate);
		//System.out.println(date);

	} catch (ParseException e) {

		//e.printStackTrace();
		return false;
	}

	return true;
}

}