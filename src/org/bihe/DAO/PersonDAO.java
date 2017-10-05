package org.bihe.DAO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.bihe.model.Person;
import org.bihe.util.FileService;

public class PersonDAO implements Serializable
{
	private static final long serialVersionUID = 2679513329566019342L;
	private Map<String, Person> persons;
	private static PersonDAO instance;
	private static final String FILE_PATH = "players.npf";
	private String userThatSignIn;

	private PersonDAO()
	{

	}

	public static PersonDAO getPersonDAO()
	{
		if (instance == null)
		{
			instance = new PersonDAO();
		}
		return instance;
	}

	/*
	 * Note: Use for SignUp.
	 */
	@SuppressWarnings("unchecked")
	public boolean addPerson(Person person)
	{
		if (persons == null)
		{
			persons = (Map<String, Person>) FileService.readObj(FILE_PATH);
			if (persons == null)
			{
				persons = new HashMap<>();
			}
		}
		if (persons.containsKey(person.getUserName()))
		{
			return false;
		}
		persons.put(person.getUserName(), person);
		FileService.writeObj((Serializable) persons, FILE_PATH);
		return true;
	}

	/*
	 * Note: Use to fill persons HashMap from file.
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Person> getPersons()
	{
		if (persons == null)
		{
			persons = (Map<String, Person>) FileService.readObj(FILE_PATH);
			if (persons == null)
			{
				persons = new HashMap<>();
			}
		}

		return (HashMap<String, Person>) persons;
	}

	public Person getThePerson()
	{
		return persons.get(userThatSignIn);
	}

	public void changePerson(Person p)
	{
		persons.replace(p.getUserName(), p);
		PlayerDAO.getPlayerDAO().changeOnePlayer(p);
		FileService.writeObj((Serializable) persons, FILE_PATH);
	}

	void changePersonForPlayerDao(Person p)
	{
		persons.replace(p.getUserName(), p);
		FileService.writeObj((Serializable) persons, FILE_PATH);
	}

	/*
	 * Note: Use for SignIn.
	 */
	public boolean checkForPerson(String userName, String password)
	{
		if (persons.containsKey(userName))
		{
			Person p = persons.get(userName);
			if (p.getPassword().equals(password))
				return true;
		}

		return false;
	}

	public String getUserThatSignIn()
	{
		return userThatSignIn;
	}

	public void setUserThatSignIn(String userThatSignIn)
	{
		this.userThatSignIn = userThatSignIn;
	}

}