package com.example.uixml;

import java.io.Serializable;
import java.util.ArrayList;

public class Communication implements Serializable {
	private String command;
	private Object data;
	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}
	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
