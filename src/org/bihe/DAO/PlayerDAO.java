package org.bihe.DAO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bihe.model.Person;

public class PlayerDAO implements Serializable
{
	private static final long serialVersionUID = 2918520485939532163L;
	private HashMap<String, Person> players;
	private static PlayerDAO instance;

	private PlayerDAO()
	{

	}

	public static PlayerDAO getPlayerDAO()
	{
		if (instance == null)
		{
			instance = new PlayerDAO();
		}
		return instance;
	}

	public boolean addPlayer(Person person)
	{
		if (players == null)
			players = new HashMap<>();

		// if (players.containsKey(person.getUserName()))
		// return false;

		players.put(person.getUserName(), person);

		return true;
	}

	public HashMap<String, Person> getPlayers()
	{
		if (players == null)
			players = new HashMap<>();

		return players;
	}

	public Person getOnePlayer(String st)
	{
		if (players == null)
			return null;

		return this.players.get(st);
	}

	public void changeOnePlayer(Person person)
	{
		int peiceNo = PersonDAO.getPersonDAO().getThePerson().getPieceNumber();
		if (person.getUserName().equals(PersonDAO.getPersonDAO().getThePerson().getUserName()))
		{
			person.setPieceNumber(peiceNo);
			PersonDAO.getPersonDAO().changePersonForPlayerDao(person);			
		}
		players.replace(person.getUserName(), person);
	}

	public void changePlayerDAO(HashMap<String, Person> players)
	{
		int peiceNo = PersonDAO.getPersonDAO().getThePerson().getPieceNumber();
		for (Entry<String, Person> E : players.entrySet())
		{
			if (E.getValue().getUserName().equals(PersonDAO.getPersonDAO().getThePerson().getUserName()))
			{
				E.getValue().setPieceNumber(peiceNo);
				PersonDAO.getPersonDAO().changePersonForPlayerDao(E.getValue());
			}	
		}
		this.players = players;
	}

	@Override
	public String toString()
	{
		return "";
	}

}