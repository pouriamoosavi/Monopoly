package org.bihe.model;

import java.io.Serializable;
import java.util.HashMap;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;

public class Data implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3232895936533010422L;

	private HashMap<String, Person> players;
	private HashMap<Integer, Estate> estates;
	private int pieceNumber;
	private boolean playerMove;
	private int location;
	private int newLocation;

	public Data(HashMap<String, Person> players, HashMap<Integer, Estate> estates)
	{

		this.players = players;
		this.estates = estates;
		this.playerMove = false;
	}

	public Data(HashMap<String, Person> players, HashMap<Integer, Estate> estates, int pieceNumber, int location,
			int newLocation)
	{

		this.players = players;
		this.estates = estates;
		this.pieceNumber = pieceNumber;
		this.playerMove = true;
		this.location = location;
		this.newLocation = newLocation;
	}

	public void analysData()
	{
		PlayerDAO.getPlayerDAO().changePlayerDAO(getPlayers());
		EstateDAO.getEstateDAO().changeEstateDAO(getEstates());
		if (playerMove)
		{
			if (pieceNumber != PersonDAO.getPersonDAO().getThePerson().getPieceNumber())
			{
				GamePanel gamePanel = GUIManager.getGamePanel();
				gamePanel.movePieceOnePlace(getPieceNumber(), gamePanel.distance(getLocation(), getNewLocation()));
			}
		}
		GUIManager.getEstatePanel().updateEstates();
		GUIManager.getPlayerPanel().setPriceLabel(PersonDAO.getPersonDAO().getThePerson().getMoney() + " M ");
		GUIManager.getGameFrame().repaint();
	}

	public int getLocation()
	{
		return location;
	}

	public int getNewLocation()
	{
		return newLocation;
	}

	public HashMap<String, Person> getPlayers()
	{
		return players;
	}

	public HashMap<Integer, Estate> getEstates()
	{
		return estates;
	}

	public int getPieceNumber()
	{
		return pieceNumber;
	}

	public boolean isPlayerMove()
	{
		return playerMove;
	}

}
