package com.db.dbmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public abstract class DBHandler extends AsyncTask<String, Void, String>{
	
	/**
	 * updateUI is an Abstract method for updating the table in subclass
	 * @param result is the string to be parsed into JSON and put in the table
	 */
	public abstract void updateUI(String result);

	// Context for called activity
	protected Context context;
	
	// Host for website, where to execute PHP files from
	public static final String siteHost = "http://themeparksimulator.3eeweb.com/";

	/**
	 * Superclass constructor for making a connection to the database
	 * through PHP scripts
	 * @param context is a reference to called activity
	 */
	public DBHandler(Context context){
		this.context = context;
	}

	@Override
	protected String doInBackground(String... params) {
		
		/* Create a stream and a buffer for reading */
		InputStream inputStream = null;
		BufferedReader buffReader;
		
		// String with raw results that is eventually sent to
		// updateUI
		String readString = "";
		
		try {

			HttpClient httpclient = new DefaultHttpClient();
			
			// params[0] contains the PHP file name
			HttpPost httppost = new HttpPost(siteHost + params[0]);
			
			/* Try to make a connection and execute the PHP file */
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();

		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			Log.e("com.db.dbmanager.DB", "Error in reading stream");
			e1.printStackTrace();
		}
		try {
			
			/* Read from the buffer reader and store into one string */
			buffReader = new BufferedReader(new InputStreamReader(inputStream));
			String currentLine = "";
			while((currentLine = buffReader.readLine()) != null){
				readString += currentLine;
			}
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Return the full string
		return readString;
	}

	/**
	 * onPostExecute calls the sub method updateUI to handle what happens
	 * in the UI after the connection
	 */
	@Override
	protected void onPostExecute(String result){
		super.onPostExecute(result);
		updateUI(result);
	}
}
