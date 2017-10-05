package org.bihe.model;

import java.io.Serializable;

public class Exit implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3129077900414477859L;
	
	private String userName;
	
	public Exit(String userName)
	{
		this.userName = userName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
