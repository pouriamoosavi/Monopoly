package org.bihe.ui.mainFrame;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Person;
import org.bihe.ui.GUIManager;

/**
 * Make a panel for creating new game or joining to a game or loading an old
 * game
 */
public class JoinGamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JButton newGame;
	private JButton join;
	private JButton changePassword;
	private JButton gameRules;
	private JButton signOut;
	private Dimension dim;
	private JButton saveButton;
	private JButton cancelButton;
	private JDialog changePasswordDialog;
	private JTextField password1;
	private JTextField password2;

	public JoinGamePanel()
	{
		setBackground(Color.WHITE);
		dim = GUIManager.getMainFrame().getSize();
		setLayout(creatGbl());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0d, 0.0d, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

		gbc.gridy = 1;
		JLabel title = new JLabel("Monopoly");
		title.setFont(new Font("", Font.BOLD, 70));
		this.add(title, gbc);

		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.CENTER;
		newGame = new JButton("New Game");
		newGame.setPreferredSize(new Dimension(dim.width / 3, 30));
		newGame.addActionListener(new GetActions());
		this.add(newGame, gbc);
		//
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.CENTER;
		join = new JButton("Join");
		join.setPreferredSize(new Dimension(dim.width / 3, 30));
		join.addActionListener(new GetActions());
		this.add(join, gbc);
		//
		gbc.gridy = 6;
		gbc.fill = GridBagConstraints.CENTER;
		changePassword = new JButton("Change Password");
		changePassword.setPreferredSize(new Dimension(dim.width / 3, 30));
		changePassword.addActionListener(new GetActions());
		this.add(changePassword, gbc);
		//
		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.CENTER;
		gameRules = new JButton("Game Rules");
		gameRules.setPreferredSize(new Dimension(dim.width / 3, 30));
		gameRules.addActionListener(new GetActions());
		this.add(gameRules, gbc);
		//
		gbc.gridy = 8;
		gbc.fill = GridBagConstraints.CENTER;
		signOut = new JButton("Sign Out");
		signOut.setPreferredSize(new Dimension(dim.width / 3, 30));
		signOut.addActionListener(new GetActions());
		this.add(signOut, gbc);
	}

	private GridBagLayout creatGbl()
	{

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 400 };
		gbl.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
		gbl.columnWeights = new double[] { 0 };
		return gbl;

	}

	private JDialog changePasswordDialog()
	{
		changePasswordDialog = new JDialog(GUIManager.getMainFrame(), "Change Password", true);
		changePasswordDialog.setLayout(new FlowLayout());
		changePasswordDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		changePasswordDialog.setSize(new Dimension(dim.width * 13 / 16, 150));
		changePasswordDialog.setMinimumSize(new Dimension(dim.width * 13 / 16, 150));
		changePasswordDialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - dim.width * 13 / 32,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 75);
		//
		changePasswordDialog.add(new JLabel("Enter your new Password: "));
		password1 = new JPasswordField();
		password1.setPreferredSize(new Dimension(dim.width * 4 / 15, 25));
		changePasswordDialog.add(password1);
		//
		changePasswordDialog.add(new JLabel("    Confirm your Password: "));
		password2 = new JPasswordField();
		password2.setPreferredSize(new Dimension(dim.width * 4 / 15, 25));
		changePasswordDialog.add(password2);
		//
		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(dim.width / 4, 25));
		saveButton.addActionListener(new GetActions());
		changePasswordDialog.add(saveButton);
		//
		cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(dim.width / 4, 25));
		cancelButton.addActionListener(new GetActions());
		changePasswordDialog.add(cancelButton);

		return changePasswordDialog;
	}

	private class GetActions implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(newGame))
			{
				GUIManager.addMakeNewGamePaneltoMainFrame();
			} else if (arg0.getSource().equals(join))
			{
				Person p = PersonDAO.getPersonDAO().getThePerson();
				p.setHaveJailCard(false);
				p.outOfJail();
				p.setLocation(0);
				p.setMoney(1500);
				p.setEstates(new ArrayList<>());
				GUIManager.addEnterGamePaneltoMainFrame();

			} else if (arg0.getSource().equals(changePassword))
			{
				changePasswordDialog = changePasswordDialog();
				changePasswordDialog.setVisible(true);

			} else if (arg0.getSource().equals(gameRules))
			{
				File file = resources.Resources.getFile("GameRules.pdf");
				try
				{
					Desktop.getDesktop().open(file);
				} catch (IOException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (arg0.getSource().equals(signOut))
			{
				PersonDAO.getPersonDAO().setUserThatSignIn(null);
				GUIManager.addSignInPaneltoMainFrame();
			} else if (arg0.getSource().equals(saveButton))
			{
				if (password1.getText().trim().equals(password2.getText().trim()))
				{
					PersonDAO personDao = PersonDAO.getPersonDAO();
					Person p = personDao.getThePerson();
					p.setPassword(password1.getText().trim());
					personDao.changePerson(p);
					changePasswordDialog.setVisible(false);
				} else
					JOptionPane.showMessageDialog(GUIManager.getMainFrame(), "Your inputs didn't match!", "Error",
							JOptionPane.ERROR_MESSAGE);

			} else if (arg0.getSource().equals(cancelButton))
			{
				changePasswordDialog.setVisible(false);
			}
		}

	}
}