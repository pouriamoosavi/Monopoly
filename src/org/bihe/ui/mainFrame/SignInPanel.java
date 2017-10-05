package org.bihe.ui.mainFrame;

import javax.swing.*;

import org.bihe.DAO.PersonDAO;
import org.bihe.ui.GUIManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Make a panel for signing in
 */
public class SignInPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JButton enterButton;
	private JTextField userNameField;
	private JTextField passwordField;

	public SignInPanel()
	{
		setBackground(Color.WHITE);
		setLayout(creatGbl());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0d, 0.0d, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
		gbc.gridy = 1;
		//
		JLabel title = new JLabel("Monopoly");
		title.setFont(new Font("", Font.BOLD, 70));
		this.add(title, gbc);
		//
		gbc.gridy = 4;
		JLabel signIn = new JLabel("Sign In");
		signIn.setFont(new Font("", Font.PLAIN, 20));
		this.add(signIn, gbc);
		//
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.BOTH;
		JPanel userNamePanel = new JPanel();
		userNamePanel.setLayout(new FlowLayout());
		userNameField = new JTextField();
		userNameField.setPreferredSize(new Dimension(200, 25));
		JLabel userName = new JLabel("User name: ");
		userNamePanel.add(userName);
		userNamePanel.add(userNameField);
		this.add(userNamePanel, gbc);
		//
		gbc.gridy = 6;
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new FlowLayout());
		JLabel password = new JLabel("Password: ");
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(200, 25));
		passwordPanel.add(password);
		passwordPanel.add(passwordField);
		this.add(passwordPanel, gbc);
		//
		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.CENTER;
		enterButton = new JButton("Enter");
		enterButton.setPreferredSize(new Dimension(400 * 2 / 9, 30));
		enterButton.addActionListener(new GetActions());
		this.add(enterButton, gbc);
		//
		gbc.gridy = 9;
		JPanel newAccountPanel = new JPanel();
		newAccountPanel.setLayout(new FlowLayout());
		JLabel question = new JLabel("Don't have an account?");
		JLabel signUp = new JLabel("sign up");
		signUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUp.setForeground(Color.BLUE);
		signUp.addMouseListener(new GetMouseActions());
		newAccountPanel.add(question);
		newAccountPanel.add(signUp);
		this.add(newAccountPanel, gbc);
	}

	private GridBagLayout creatGbl()
	{

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 400 };
		gbl.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
		gbl.columnWeights = new double[] { 0 };
		return gbl;

	}

	private class GetMouseActions implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			GUIManager.addSignUpPaneltoMainFrame();
			passwordField.setText("");
			userNameField.setText("");
		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
		}
	}

	private class GetActions implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(enterButton))
			{
				String userName = userNameField.getText().trim();
				String password = passwordField.getText().trim();
				if (!userName.equals("") && !password.equals(""))
				{
					PersonDAO personDao = PersonDAO.getPersonDAO();
					if (personDao.checkForPerson(userName, password))
					{
						personDao.setUserThatSignIn(userName);
						GUIManager.addJoinGamePaneltoMainFrame();
					} else
						JOptionPane.showMessageDialog(GUIManager.getMainFrame(), "Username or password wrong", "Error",
								JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(GUIManager.getMainFrame(), "You didn't input any thing!", "Error",
							JOptionPane.ERROR_MESSAGE);
				passwordField.setText("");
				userNameField.setText("");
			}
		}
	}
}
