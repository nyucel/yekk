package com.example.timer;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		 final Timer zamanlayici;
		 final Handler yardimci;
		 final long ZAMAN = 1000;
		 
		final TextView  txt=(TextView)findViewById(R.id.textView1);		        
		Button btn=(Button)findViewById(R.id.button1);
		 	   
		zamanlayici = new Timer();
		yardimci = new Handler(Looper.getMainLooper());
			  
		zamanlayici.scheduleAtFixedRate(new TimerTask()
		{
			int sayi = 19;
			   @Override
			   public void run()
			   {
				   
				   yardimci.post(new Runnable()
				    {
				     public void run()
				     {
				      
				      txt.setText(String.valueOf(sayi));
				      sayi = sayi + 1;
				      
				     }
				    });
			   }
			  }, 0, 1000);
	}
			      
			         
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

