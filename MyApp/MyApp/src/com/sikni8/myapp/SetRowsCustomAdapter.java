package com.sikni8.myapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SetRowsCustomAdapter extends ArrayAdapter<SetRows> {
    Context context;
   int layoutResourceId;
   ArrayList<SetRows> data=new ArrayList<SetRows>();
   public SetRowsCustomAdapter(Context context, int layoutResourceId, ArrayList<SetRows> data) {
       super(context, layoutResourceId, data);
       this.layoutResourceId = layoutResourceId;
       this.context = context;
       this.data = data;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       View row = convertView;
       ImageHolder holder = null;

       if(row == null)
       {
           LayoutInflater inflater = ((Activity)context).getLayoutInflater();
           row = inflater.inflate(layoutResourceId, parent, false);

           holder = new ImageHolder();
           holder.tID = (TextView)row.findViewById(R.id.tvID);
           holder.tType = (TextView)row.findViewById(R.id.tvType);
           holder.tData = (TextView)row.findViewById(R.id.tvData);
           row.setTag(holder);
       }
       else
       {
           holder = (ImageHolder)row.getTag();
       }

       SetRows myImage = data.get(position);
       holder.tID.setText(myImage.id);
       holder.tType.setText(myImage.type);
       holder.tData.setText(myImage.data);
      return row;

   }
   /*public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// arg2 = the id of the item in our view (List/Grid) that we clicked
		// arg3 = the id of the item that we have clicked
		// if we didn't assign any id for the Object (Book) the arg3 value is 0
		// That means if we comment, aBookDetail.setBookIsbn(i); arg3 value become 0
	   Toast.makeText(getContext(), "TEST", 2000).show();
		//Toast.makeText((), "You clicked on position : " + arg2 + " and id : " + arg3, Toast.LENGTH_LONG).show();
	}*/

   static class ImageHolder
   {
       TextView tID;
       TextView tType;
       TextView tData;
   }
}