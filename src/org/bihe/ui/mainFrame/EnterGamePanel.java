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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Person;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;

public class EnterGamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Dimension dim;
	private JTextField portField;
	private JTextField ipField;
	private JButton joinButton;
	private String portNo;
	private String ipAddress;

	public EnterGamePanel()
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
		JPanel userNamePanel = new JPanel(new FlowLayout());
		userNamePanel.setBackground(Color.WHITE);
		userNamePanel.setPreferredSize(new Dimension(dim.width * 2 / 3, 50));
		JLabel userNameLabel1 = new JLabel("You'll join by this acccount: ");
		userNameLabel1.setFont(new Font("", Font.PLAIN, 14));
		userNamePanel.add(userNameLabel1);
		JLabel userNameLabel2 = new JLabel(PersonDAO.getPersonDAO().getThePerson().getUserName());
		userNameLabel2.setFont(new Font("", Font.BOLD, 14));
		userNamePanel.add(userNameLabel2);
		this.add(userNamePanel, gbc);
		//
		gbc.gridy = 5;
		JPanel portPanel = new JPanel(new FlowLayout());
		portPanel.setBackground(Color.WHITE);
		portPanel.setPreferredSize(new Dimension(dim.width * 2 / 3, 50));
		JLabel portLabel = new JLabel("port: ");
		portLabel.setFont(new Font("", Font.BOLD, 12));
		portPanel.add(portLabel);
		portField = new JTextField();
		portField.setPreferredSize(new Dimension(dim.width / 3, 25));
		portPanel.add(portField);
		this.add(portPanel, gbc);
		//
		gbc.gridy = 6;
		JPanel ipPanel = new JPanel(new FlowLayout());
		ipPanel.setBackground(Color.WHITE);
		ipPanel.setPreferredSize(new Dimension(dim.width * 2 / 3, 50));
		JLabel ipLabel = new JLabel("IP:    ");
		ipLabel.setFont(new Font("", Font.BOLD, 12));
		ipPanel.add(ipLabel);
		ipField = new JTextField();
		ipField = new JTextField();
		ipField.setPreferredSize(new Dimension(dim.width / 3, 25));
		ipPanel.add(ipField);
		this.add(ipPanel, gbc);
		//
		gbc.gridy = 7;
		joinButton = new JButton("Join");
		joinButton.setPreferredSize(new Dimension(dim.width / 3, 30));
		joinButton.addActionListener(new getActions());
		this.add(joinButton, gbc);
	}

	public String getPortNo()
	{
		return portNo;
	}

	public String getIpAddress()
	{
		return ipAddress;
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
			portNo = portField.getText();
			ipAddress = ipField.getText();
			Client client = Client.getClient();
			client.runClient(Integer.parseInt(getPortNo()), getIpAddress());
			Person p = PersonDAO.getPersonDAO().getThePerson();
			p.setPieceNumber(Client.getClient().getClientNo());
			p.setYourTurn(false);
			PlayerDAO.getPlayerDAO().changeOnePlayer(p);
			
		}

	}

}
