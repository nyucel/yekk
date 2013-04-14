package com.google5;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.android.apps.authenticator.Base32String;
import com.google.android.apps.authenticator.PasscodeGenerator;
import com.google.android.apps.authenticator.Base32String.DecodingException;

import javax.crypto.spec.SecretKeySpec;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView rastgele = (TextView)findViewById(R.id.textView1);
		final TextView rastgele2 = (TextView)findViewById(R.id.textView2);
		
		 final Timer zamanlayici;
		 final Handler yardimci;
		 final long ZAMAN = 1000;
		 
		 final String chars = "abcdefghijklmnopqrstuvwxyz"
	                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    
		final Random rnd = new SecureRandom();
		 
		zamanlayici = new Timer();
		yardimci = new Handler(Looper.getMainLooper());
			  
		zamanlayici.scheduleAtFixedRate(new TimerTask()
		{
		    int sayi = 0;
		    @Override
		    public void run()
		    {
			    yardimci.post(new Runnable()
			    {
			    	public void run()
			    	{
				    	final StringBuilder pass = new StringBuilder();
				    	 
				    	for (int i = 0; i < 8; i++)
			                    pass.append(chars.charAt(rnd.nextInt(chars.length())));
				    	   
				    	String checkCode = null;
				    	String cehars = pass.toString();
				 		
			 			try {
							checkCode = getCheckCode(cehars);
						} catch (GeneralSecurityException e) {
							e.printStackTrace();
						} catch (DecodingException e) {
							e.printStackTrace();
						}
			 			
				 		rastgele2.setText(checkCode);
				 		
				 		sayi = sayi + 1;
			    	}
			    });
		     }
		  }, 0, 10000);
	
		final Timer zamanlayici1;
		final Handler yardimci1;
		
		zamanlayici1 = new Timer();
		yardimci1 = new Handler(Looper.getMainLooper());
			  
		zamanlayici1.scheduleAtFixedRate(new TimerTask()
		{
			int sayi = 10;
			   @Override
			   public void run()
			   {
				   yardimci1.post(new Runnable()
				    {
					     public void run()
					     {
					 		rastgele.setText(String.valueOf(sayi));
							if (sayi == 0) 
								sayi = 10;
							
							sayi = sayi - 1;
					     }
					});
			   }
		}, 0, 1000);	
	}

    static String getCheckCode(String secret) throws GeneralSecurityException,
    DecodingException {
    final byte[] keyBytes = Base32String.decode(secret);
    Mac mac = Mac.getInstance("HMACSHA1");
    mac.init(new SecretKeySpec(keyBytes, ""));
    PasscodeGenerator pcg = new PasscodeGenerator(mac);
    return pcg.generateResponseCode(0L);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}