package org.novelveritasPatch.rfc;
import com.sap.mw.jco.*;  //The JCO

public class SapConnection
{ public static JCO.Client mConnection = null;

  public SapConnection()
  {
  }

  public Exception Connect(String client,
                           String User,
                           String Pw,
                           String Langu,
                           String Host,
                           String System)

   { try
     { mConnection = JCO.createClient(client,    //SAP client
                                      User,      //User ID
                                      Pw,        //Password
                                      Langu,     //Language
                                      Host,      //Host
                                      System);   //System
        mConnection.connect();

        return null;
      }
   catch (Exception ex)
      {mConnection = null;
       return ex;
      }

   }


}