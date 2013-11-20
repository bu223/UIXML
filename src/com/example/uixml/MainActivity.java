package com.example.uixml;

import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.json.JSONInputStream;
import org.quickconnect.json.JSONOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.uixml.MESSAGE";
	Handler mainHandler;
	int num = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mainHandler = new Handler(this.getMainLooper());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonSend = (Button) findViewById(R.id.sendButton);
		buttonSend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				final TextView responseView = (TextView) findViewById(R.id.response);
				EditText textInput = (EditText) findViewById(R.id.message);
				final String theText = textInput.getText().toString();

				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						HashMap aMap = null;

						try {
							Socket socket = new Socket("10.0.2.2", 9595);
							JSONInputStream fromServer = new JSONInputStream(
									socket.getInputStream());
							JSONOutputStream toServer = new JSONOutputStream(
									socket.getOutputStream());
							System.out.println("got streams");
							num++;
							ArrayList data = new ArrayList();
							data.add(num);
							data.add(theText);

							Communication aBean = new Communication();
							aBean.setCommand("Speak");
							aBean.setData(data);
							System.out.println("bean created");
							toServer.writeObject(aBean);
							System.out.println("sent");

							aMap = (HashMap) fromServer.readObject();
							System.out.println(aMap);

						} catch (Exception e) {
							e.printStackTrace();
						}
						MainActivity.this.mainHandler.post(new TextDisplayer(aMap, new WeakReference(responseView)));						
					}

				});

			}
		});
	}

}
