package org.bihe.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;

public class Request implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5064072459813802833L;

	private ArrayList<Estate> giveEstates;
	private ArrayList<Estate> recieveEstates;
	private int giveMoney;
	private int recieveMoney;
	private String sender;
	private String receiver;
	private int response;

	public Request(ArrayList<Estate> giveEstates, ArrayList<Estate> recieveEstates, int giveMoney, int recieveMoney,
			String sender, String receiver)
	{
		this.giveEstates = giveEstates;
		this.recieveEstates = recieveEstates;
		this.giveMoney = giveMoney;
		this.recieveMoney = recieveMoney;
		this.sender = sender;
		this.receiver = receiver;
		this.response = 0;
	}

	public ArrayList<Estate> getGiveEstates()
	{
		return giveEstates;
	}

	public void analyseRequest()
	{
		if (response == 0)
		{
			GUIManager.getRequestDialog().setRequest(this);
			GUIManager.getRequestDialog().setVisible(true);

		} else if (response == 1)
		{
			JOptionPane.showMessageDialog(null, sender + " accept your request");
			Person p1 = PlayerDAO.getPlayerDAO().getOnePlayer(receiver);
			Person p2 = PlayerDAO.getPlayerDAO().getOnePlayer(sender);

			p1.setMoney(p1.newMoney(recieveMoney - giveMoney));
			p2.setMoney(p2.newMoney(giveMoney - recieveMoney));
			p1.setEstates(p1.newEstates_remove(giveEstates));
			p1.setEstates(p1.newEstates_add(recieveEstates));
			p2.setEstates(p2.newEstates_remove(recieveEstates));
			p2.setEstates(p2.newEstates_add(giveEstates));
			for (int i = 0; i < giveEstates.size(); i++)
			{
				Estate estate = giveEstates.get(i);
				estate.setOwner(p2.getUserName());
				EstateDAO.getEstateDAO().changeEstate(estate);
			}
			for (int i = 0; i < recieveEstates.size(); i++)
			{
				Estate estate = recieveEstates.get(i);
				estate.setOwner(p1.getUserName());
				EstateDAO.getEstateDAO().changeEstate(estate);
			}
			PlayerDAO.getPlayerDAO().changeOnePlayer(p1);
			PlayerDAO.getPlayerDAO().changeOnePlayer(p2);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);

		} else if (response == -1)
		{
			JOptionPane.showMessageDialog(null, sender + " don't accept your request");
		}
	}

	public ArrayList<Estate> getRecieveEstates()
	{
		return recieveEstates;
	}

	public int getGiveMoney()
	{
		return giveMoney;
	}

	public int getRecieveMoney()
	{
		return recieveMoney;
	}

	public String getSender()
	{
		return sender;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public String getReceiver()
	{
		return receiver;
	}

	public void setReceiver(String receiver)
	{
		this.receiver = receiver;
	}

	public int getResponse()
	{
		return response;
	}

	public void setResponse(int response)
	{
		this.response = response;
	}
}