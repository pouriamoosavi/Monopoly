package org.bihe.main;

import javax.swing.UIManager;
import org.bihe.DAO.PersonDAO;
import org.bihe.ui.GUIManager;

public class Main
{

	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		PersonDAO personDao = PersonDAO.getPersonDAO();
		personDao.getPersons();
		//
		GUIManager.getMainFrame();
	}

}