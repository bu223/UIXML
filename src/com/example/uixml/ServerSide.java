package com.example.uixml;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.quickconnect.json.JSONException;
import org.quickconnect.json.JSONInputStream;
import org.quickconnect.json.JSONOutputStream;

public class ServerSide {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		try {
			ServerSocket sSocket = new ServerSocket(9595);
			while(true) {
				System.out.println("Waiting for client connection...");
				
				Socket aSocket = sSocket.accept();
				
				JSONInputStream fromClient = new JSONInputStream(aSocket.getInputStream());
				JSONOutputStream toClient = new JSONOutputStream(aSocket.getOutputStream());
				
				while(true) {
					System.out.println("System waiting for messages");
					HashMap map = (HashMap)fromClient.readObject();
					System.out.println("Got a " + map + "from client");
					Communication aBean = new Communication();
					aBean.setCommand("Done");
					map.get("data");
					
					toClient.writeObject(aBean);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

}
