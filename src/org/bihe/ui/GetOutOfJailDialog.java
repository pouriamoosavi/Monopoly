package org.bihe.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Person;

public class GetOutOfJailDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JButton yesButton;
	private JButton noButton;
	public GetOutOfJailDialog()
	{
		Dimension dim = GUIManager.getMainFrame().getSize();
		this.setTitle("In Gail");
		this.setModal(true);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(dim.width * 13 / 16, 150));
		this.setMinimumSize(new Dimension(dim.width * 13 / 16, 150));
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - dim.width * 13 / 32,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 75);
		
		JLabel jailLabel = new JLabel("<html>You are in jail.<br> Do yo want to pay 50M to get free?<html>");
		jailLabel.setFont(new Font("", Font.PLAIN, 13));
		this.add(jailLabel);
		//
		yesButton = new JButton("Yes");
		yesButton.setPreferredSize(new Dimension(dim.width / 4, 30));
		yesButton.addActionListener(new getActions());
		this.add(yesButton);
		//
		noButton = new JButton("No");
		noButton.setPreferredSize(new Dimension(dim.width / 4, 30));
		noButton.addActionListener(new getActions());
		this.add(noButton);
	}
	private class getActions implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(yesButton))
			{
				Person p = PersonDAO.getPersonDAO().getThePerson();
				p.setMoney(p.newMoney(-50));
				p.outOfJail();
				PersonDAO.getPersonDAO().changePerson(p);
				GUIManager.getGetOutOfJailDialog().setVisible(false);
			} else if (e.getSource().equals(noButton))
			{
				GUIManager.getGetOutOfJailDialog().setVisible(false);
			}
		}

	}
}
