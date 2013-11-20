package com.example.uixml;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import android.content.Context;
import android.widget.TextView;

public class TextDisplayer implements Runnable {
	HashMap mapToDisplay;
	WeakReference textViewRef;

	public TextDisplayer(HashMap mapToDisplay, WeakReference textViewRef) {
		super();
		this.mapToDisplay = mapToDisplay;
		this.textViewRef = textViewRef;
	}

	@Override
	public void run() {
		((TextView) textViewRef.get()).setText(mapToDisplay.toString());
		// TextView txtView = (TextView)MainActivityfindViewById(R.id.response);
	
	}

}
