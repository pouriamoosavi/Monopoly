package org.bihe.ui.actionPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Person;
import org.bihe.ui.GUIManager;

public class PlayerPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private String price = "1000" + "  M ";
	private TreeSet<String> players;
	private JButton requestBtn;
	private JLabel priceLabel;
	private int HEIGHT;
	private int WIDTH;
	private JList<String> playersList;
	private String playerUserName;
	private DefaultListModel<String> model;

	public PlayerPanel()
	{
		this.setLayout(new FlowLayout());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		HEIGHT = (int) dimension.getHeight();
		WIDTH = (int) dimension.getWidth() * 10 / 46;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(Color.white);
		this.setBounds(0, 0, WIDTH, (HEIGHT - 30) / 3);
		//

		//
		this.add(new JLabel("User Name: "));
		JLabel userNameLabel = new JLabel(PersonDAO.getPersonDAO().getThePerson().getUserName());
		userNameLabel.setPreferredSize(new Dimension(WIDTH - 112, 25));
		this.add(userNameLabel);
		//
		this.add(new JLabel("Your Balance: "));
		priceLabel = new JLabel(price);
		priceLabel.setPreferredSize(new Dimension(WIDTH - 120, 25));
		this.add(priceLabel);
		//
		JLabel playersLabel = new JLabel("Players: ");
		playersLabel.setPreferredSize(new Dimension(WIDTH - 10, 15));
		this.add(playersLabel);
		//
		model = new DefaultListModel<>();
		playersList = new JList<>(model);
		fillPlayersList();
		//
		playersList.setPreferredSize(new Dimension(WIDTH - 110, HEIGHT / 3 - 100));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(WIDTH - 85, HEIGHT / 3 - 170));
		scrollPane.setViewportView(playersList);
		this.add(scrollPane);
		//
		requestBtn = new JButton("Send New Request");
		requestBtn.setPreferredSize(new Dimension(WIDTH - 150, (HEIGHT - 30) * 2 / 48));
		requestBtn.addActionListener(new GetActions());
		this.add(requestBtn);
	}

	public void fillPlayersList()
	{
		@SuppressWarnings("unused")
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Thread.sleep(10);
					GUIManager.getPlayerPanel().fillPlayersList();
				} catch (InterruptedException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		};
		HashMap<String, Person> playersFromDao = PlayerDAO.getPlayerDAO().getPlayers();
		players = new TreeSet<>();
		for (Entry<String, Person> E : playersFromDao.entrySet())
		{
			players.add(E.getKey());
		}
		model.removeAllElements();
		for (String st : players)
		{
			model.addElement(st);
		}
	}

	public JLabel getPriceLabel()
	{
		return priceLabel;
	}

	public void setPriceLabel(String s)
	{
		this.priceLabel.setText(s);
		revalidate();
		repaint();
	}

	public String getPlayerUserName()
	{
		return this.playerUserName;
	}

	private class GetActions implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(requestBtn))
			{
				if (playersList.getSelectedValue() != null)
				{
					playerUserName = playersList.getSelectedValue();
					GUIManager.getSendRequestPanel().fillList();
					GUIManager.getSendRequestFrame().add(GUIManager.getSendRequestPanel());
					GUIManager.getSendRequestFrame().setVisible(true);
				} else
				{
					JOptionPane.showMessageDialog(null, "You did't choose any thing", "Error", 2);
				}
			}
		}
	}

}