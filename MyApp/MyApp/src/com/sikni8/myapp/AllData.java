package com.sikni8.myapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class AllData extends Fragment {
	
	DefaultHttpClient httpclient;
	HttpPost httppost;
	InputStream inputStream = null;
	String result = null;
	StringBuilder sb;
	JSONArray jsonArray;
	public ListView lAll;
	public String[] imgID;
	public String[] txtID;
	public String[] sId, sType, sData;
	ArrayList<SetRows> contents = new ArrayList<SetRows>();
	private SetRowsCustomAdapter adapter;
	String id, type, data;
	String dummyValue = "myDummyValue";
	
	private RelativeLayout mFrame1;
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		mFrame1 = (RelativeLayout) inflater.inflate( R.layout.alldata, container, false );
		
		lAll = (ListView) mFrame1.findViewById(R.id.lvAll);
		
		new GetJSON().execute(null, null, null);
		
		return mFrame1;
	}
	
	public class GetJSON extends AsyncTask<Void, Void, Void> {
	     @Override
	     protected Void doInBackground(Void... params) { //Running in background
	    	 try {
	    		 httpclient = new DefaultHttpClient(new BasicHttpParams());
	    		 HttpPost httppost = new HttpPost("http://pagesbyz.com/test.json");
	    		 // Depends on your web service
	    		 httppost.setHeader("Content-type", "application/json");
	 		    HttpResponse response = httpclient.execute(httppost);           
	 		    HttpEntity entity = response.getEntity();

	 		    inputStream = entity.getContent();
	 		    // json is UTF-8 by default
	 		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
	 		    sb = new StringBuilder();

	 		    String line = null;
	 		    while ((line = reader.readLine()) != null)
	 		    {
	 		        sb.append(line + "\n");
	 		    }
	 		    result = sb.toString();
	 		    
	 		} catch (Exception e) {
	 			Log.i("TEST", e.toString());
	 		    // Oops
	 		}
	 		finally {
	 		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
	 		}
			return null;
	     }

	     @Override
	     protected  void onPreExecute() { //Activity is on progress
	     }

	     @Override
         protected void onPostExecute(Void v) {
	    	 try {
	    		 jsonArray = new JSONArray(result);
	    		 	for (int i = 0; i < jsonArray.length(); i++) {
	    		 		JSONObject jsonObj = (JSONObject) jsonArray.get(i);
	    		 		type = jsonObj.getString("type");

	    		 		if (type.equals("image") || type.equals("text") || type.equals("other")) {
	    		 			id = jsonObj.isNull("id") ? dummyValue : jsonObj.getString("id"); //jsonObj.getString("id");

	    		 			// If data is blank or not mapped, it will use the
	    		 			// dummyValue otherwise it will use what it is mapped
	    		 			// to.
	    		 			data = jsonObj.isNull("data") || jsonObj.getString("data").equals("") ? dummyValue : jsonObj.getString("data");

	    		 		}
	    		 		contents.add(new SetRows(id, type, data));
	    		 	} // End the for loop

	    		 	// Now set your adapter.
	    		 	adapter = new SetRowsCustomAdapter(getActivity(), R.layout.listrow, contents);
	    		 	lAll.setAdapter(adapter);
	    		 	lAll.setOnItemClickListener(new OnItemClickListener() {
	    		 		@Override
	    		 			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    		 				Intent myIntent = new Intent(getActivity(), DisplayWeb.class);
	    		 				startActivityForResult(myIntent, 0);
	    		 			}
	    		 		});
	    	 }
	    	 catch (JSONException e) {
	    		 e.printStackTrace();
	    	 }
         }
	}
}