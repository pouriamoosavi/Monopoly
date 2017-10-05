package org.bihe.ui.mainFrame;

import javax.swing.*;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Person;
import org.bihe.ui.GUIManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * make a panel for signing up
 */
public class SignUpPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JButton create;
	private JButton back;
	private Dimension dim;
	private JTextField confirmPasswordField;
	private JTextField passwordField;
	private JTextField userNameField;

	public SignUpPanel()
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
		JLabel signIn = new JLabel("Sign Up");
		signIn.setFont(new Font("", Font.PLAIN, 20));
		this.add(signIn, gbc);

		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.BOTH;
		JPanel userNamePanel = new JPanel();
		userNamePanel.setLayout(new FlowLayout());
		userNameField = new JTextField();
		userNameField.setPreferredSize(new Dimension(200, 25));
		JLabel userName = new JLabel("             User name: ");
		userNamePanel.add(userName);
		userNamePanel.add(userNameField);
		this.add(userNamePanel, gbc);
		gbc.gridy = 5;
		// ------------------------------------------
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new FlowLayout());
		JLabel password = new JLabel("              Password: ");
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200, 25));
		passwordPanel.add(password);
		passwordPanel.add(passwordField);
		this.add(passwordPanel, gbc);
		// ------------------------------------------
		gbc.gridy = 6;
		JPanel confirmPasswordPanel = new JPanel();
		passwordPanel.setLayout(new FlowLayout());
		JLabel confirmPassword = new JLabel("Confirm Password: ");
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setPreferredSize(new Dimension(200, 25));
		confirmPasswordPanel.add(confirmPassword);
		confirmPasswordPanel.add(confirmPasswordField);
		this.add(confirmPasswordPanel, gbc);
		// ------------------------------------------
		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.CENTER;
		create = new JButton("Create");
		create.setPreferredSize(new Dimension(dim.width * 2 / 9, 30));
		create.addActionListener(new GetActions());
		this.add(create, gbc);
		//
		gbc.gridy = 8;
		gbc.fill = GridBagConstraints.CENTER;
		back = new JButton("Back");
		back.setPreferredSize(new Dimension(dim.width * 2 / 9, 30));
		back.addActionListener(new GetActions());
		this.add(back, gbc);

	}

	private GridBagLayout creatGbl()
	{

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 400 };
		gbl.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
		gbl.columnWeights = new double[] { 0 };
		return gbl;

	}

	private class GetActions implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(create))
			{
				String userName = userNameField.getText().trim();
				String password = passwordField.getText().trim();
				String password2 = confirmPasswordField.getText().trim();
				if (!userName.equals("") && !password.equals("") && password.equals(password2))
				{
					Person person = new Person(userName, password2);
					PersonDAO personDao = PersonDAO.getPersonDAO();
					if (personDao.addPerson(person))
					{
						personDao.setUserThatSignIn(userName);
						//
						GUIManager.addJoinGamePaneltoMainFrame();
						//
						userNameField.setText("");
						passwordField.setText("");
						confirmPasswordField.setText("");
					} else
					{
						JOptionPane.showMessageDialog(GUIManager.getMainFrame(), "duplicated user name!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} else
					JOptionPane.showMessageDialog(GUIManager.getMainFrame(), "Your inputs was wrong!", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
			if (arg0.getSource().equals(back))
			{
				GUIManager.addSignInPaneltoMainFrame();
				userNameField.setText("");
				passwordField.setText("");
				confirmPasswordField.setText("");
			}
		}

	}
}
