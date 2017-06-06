package com.cears.serviceapp.JsonHelper;

import android.content.Context;
import android.os.AsyncTask;

public class CallWebServiceInBackground extends AsyncTask<Object, Integer, String> {

	private GetJSONListener getJSONListener;
	private Context mContext;
	private String response;

	@Override
	protected String doInBackground(Object... urls) {
		if(urls.length == 2)
		{
			response = HttpPostHelper.requestLogin((String)urls[0], mContext, (String)urls[1]);
		}else
		{
			for(Object urlObj : urls){
				response = HttpPostHelper.getResponsefromURL((String)urlObj, mContext);
			}
		}
		return response;
	}

	public CallWebServiceInBackground(Context context, GetJSONListener listener) {
		this.getJSONListener = listener;
		this.mContext=context;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		this.getJSONListener.onRemoteCallComplete(response);
	}
}
