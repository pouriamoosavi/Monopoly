package org.bihe.ui.mainFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Data;
import org.bihe.model.Person;
import org.bihe.network.client.Client;
import org.bihe.network.server.Server;
import org.bihe.ui.GUIManager;

public class MakeNewGamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Dimension dim;
	private JTextField portField;
	private JButton runServerButton;
	private JLabel runningLabel;
	private DefaultListModel<String> model;
	private JButton startGameButton;
	private JLabel portLabel;
	private String portNo;
	private JList<String> joinedPlayersList;
	private JSpinner playerNoSpinner;
	private JLabel playerNoLabel;
	private int playerNo;

	public MakeNewGamePanel()
	{
		setBackground(Color.WHITE);
		dim = GUIManager.getMainFrame().getSize();
		setLayout(creatGbl());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0d, 0.0d, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

		//
		gbc.gridy = 1;
		JLabel title = new JLabel("Monopoly");
		title.setFont(new Font("", Font.BOLD, 70));
		this.add(title, gbc);
		//
		gbc.gridy = 2;
		JPanel ipPanel = new JPanel(new FlowLayout());
		JLabel ipLabel1 = new JLabel("Your local IP address is: ");
		ipLabel1.setFont(new Font("", Font.BOLD, 12));
		ipPanel.add(ipLabel1);
		try
		{
			JLabel ipLabel2 = new JLabel((InetAddress.getLocalHost().getHostAddress()).trim());
			ipLabel2.setFont(new Font("", Font.BOLD, 14));
			ipPanel.add(ipLabel2);
		} catch (UnknownHostException e)
		{
			JOptionPane.showMessageDialog(GUIManager.getMainFrame(), e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		this.add(ipPanel, gbc);
		//
		gbc.gridy = 3;
		JPanel portPanel = new JPanel(new FlowLayout());
		portPanel.setBackground(Color.WHITE);
		portPanel.setPreferredSize(new Dimension(dim.width / 2, 95));
		//
		playerNoLabel = new JLabel("Players number: ");
		playerNoLabel.setFont(new Font("", Font.BOLD, 12));
		portPanel.add(playerNoLabel);
		//
		playerNoSpinner = new JSpinner(getModel());
		playerNoSpinner.setPreferredSize(new Dimension(dim.width / 7, 25));
		portPanel.add(playerNoSpinner);
		//
		portLabel = new JLabel("port: ");
		portLabel.setFont(new Font("", Font.BOLD, 12));
		portLabel.setPreferredSize(new Dimension(dim.width / 10, 25));
		portPanel.add(portLabel);
		//
		portField = new JTextField();
		portField.setPreferredSize(new Dimension(dim.width * 9 / 30, 25));
		portPanel.add(portField);
		//
		runServerButton = new JButton("Run Server");
		runServerButton.setPreferredSize(new Dimension(dim.width / 3, 30));
		runServerButton.addActionListener(new getActions());
		portPanel.add(runServerButton, gbc);
		this.add(portPanel, gbc);
		//
		gbc.gridy = 4;
		runningLabel = new JLabel("Server is ready. Waiting for clients... ");
		runningLabel.setVisible(false);
		this.add(runningLabel, gbc);
		//
		gbc.gridy = 5;
		model = new DefaultListModel<>();

		joinedPlayersList = new JList<>(model);
		joinedPlayersList.setPreferredSize(new Dimension(dim.width * 3 / 7, 140));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(dim.width * 6 / 10, 150));
		scrollPane.setViewportView(joinedPlayersList);
		joinedPlayersList.setEnabled(false);
		this.add(scrollPane, gbc);

		gbc.gridy = 6;
		startGameButton = new JButton("Start Game");
		startGameButton.setPreferredSize(new Dimension(dim.width / 3, 30));
		startGameButton.addActionListener(new getActions());
		startGameButton.setEnabled(false);
		this.add(startGameButton, gbc);

	}

	public String getPortNo()
	{
		return this.portNo;
	}

	public int getPlayerNo()
	{
		return this.playerNo;
	}

	public void updateList(String input)
	{
		model.addElement(input);
	}

	private SpinnerModel getModel()
	{
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(6),
				new Integer(1));
		return spinnerModel;
	}

	private GridBagLayout creatGbl()
	{

		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 400 };
		gbl.rowHeights = new int[] { 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 };
		gbl.columnWeights = new double[] { 0 };
		return gbl;

	}

	private class getActions implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(runServerButton))
			{
				portNo = portField.getText();
				playerNo = (int) playerNoSpinner.getValue();

				playerNoLabel.setEnabled(false);
				playerNoSpinner.setEnabled(false);
				portLabel.setEnabled(false);
				portField.setEnabled(false);
				runServerButton.setEnabled(false);
				runningLabel.setVisible(true);
				joinedPlayersList.setEnabled(true);
				startGameButton.setEnabled(true);
				// network
				Runnable r = new Runnable()
				{
					@Override
					public void run()
					{
						Server s = Server.getServer();
						s.runServer(Integer.parseInt(portField.getText()), playerNo);
						try
						{
							Thread.sleep(10);
						} catch (InterruptedException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				};
				new Thread(r).start();
				Runnable r2 = new Runnable()
				{

					@Override
					public void run()
					{
						Client client = Client.getClient();
						client.runClient(Integer.parseInt(portField.getText()), "localhost");
						try
						{
							Thread.sleep(10);
						} catch (InterruptedException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				};
				new Thread(r2).start();

			} else if (arg0.getSource().equals(startGameButton))
			{
				GUIManager.getMainFrame().setVisible(false);

				Person p = PersonDAO.getPersonDAO().getThePerson();

				p.setPieceNumber(1);
				p.setYourTurn(true);
				p.setLocation(0);
				p.setMoney(1500);
				p.setHaveJailCard(false);
				p.setEstates(new ArrayList<>());
				Server.getServer().sendClintNo();
				Server.getServer().sendObjectToAll(playerNo);
				PersonDAO.getPersonDAO().changePerson(p);
				PlayerDAO.getPlayerDAO().changeOnePlayer(p);
				Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
				Client.getClient().sendObject(data);
			}
		}

	}
}
