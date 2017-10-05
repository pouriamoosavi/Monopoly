package org.bihe.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.Request;
import org.bihe.network.client.Client;

public class SendRequestPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> model1;
	private JList<String> offerList1;
	private DefaultListModel<String> model2;
	private JList<String> offerList2;
	private JButton sendRequestButton;
	private JButton cancelRequestButton;
	private static final int WIDTH = (Toolkit.getDefaultToolkit().getScreenSize().width) / 2;
	private static final int HEIGHT = (Toolkit.getDefaultToolkit().getScreenSize().height - 55) * 2 / 3;
	private JTextField recieveMoneyField;
	private JTextField giveMoneyField;
	private int maxRecieveMoney;
	private Person player;
	private Person p;

	public SendRequestPanel()
	{
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//
		JLabel giveLabel = new JLabel("Give these things: ");
		giveLabel.setPreferredSize(new Dimension(WIDTH * 2 / 5, 15));
		this.add(giveLabel);
		JLabel recieveLabel = new JLabel("Recieve these things: ");
		recieveLabel.setPreferredSize(new Dimension(WIDTH * 2 / 5, 15));
		this.add(recieveLabel);
		//
		model1 = new DefaultListModel<>();
		offerList1 = new JList<>(model1);
		offerList1.setPreferredSize(new Dimension(WIDTH * 2 / 5, WIDTH * 2 / 5));

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setPreferredSize(new Dimension(WIDTH * 2 / 5, WIDTH * 2 / 5));
		scrollPane1.setViewportView(offerList1);
		this.add(scrollPane1);
		//
		model2 = new DefaultListModel<>();
		offerList2 = new JList<>(model2);
		offerList2.setPreferredSize(new Dimension(WIDTH * 2 / 5, WIDTH * 2 / 5));

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setPreferredSize(new Dimension(WIDTH * 2 / 5, WIDTH * 2 / 5));
		scrollPane2.setViewportView(offerList2);
		this.add(scrollPane2);
		//
		giveMoneyField = new JTextField();
		giveMoneyField.setPreferredSize(new Dimension(WIDTH * 2 / 9, 30));
		//
		JPanel giveMoneyPanel = new JPanel(new FlowLayout());
		giveMoneyPanel.setPreferredSize(new Dimension(WIDTH * 21 / 50, 35));
		giveMoneyPanel.add(new JLabel("Give money: "));
		giveMoneyPanel.add(giveMoneyField);
		this.add(giveMoneyPanel);
		//
		recieveMoneyField = new JTextField();
		recieveMoneyField.setPreferredSize(new Dimension(WIDTH * 2 / 9, 30));
		//
		JPanel recieveMoneyPanel = new JPanel(new FlowLayout());
		recieveMoneyPanel.setPreferredSize(new Dimension(WIDTH * 21 / 50, 35));
		recieveMoneyPanel.add(new JLabel("Recieve money: "));
		recieveMoneyPanel.add(recieveMoneyField);
		this.add(recieveMoneyPanel);
		//
		JLabel adjustLabel2 = new JLabel();
		adjustLabel2.setPreferredSize(new Dimension(WIDTH * 1 / 15, 30));
		this.add(adjustLabel2);

		JLabel adjustLabel3 = new JLabel();
		adjustLabel3.setPreferredSize(new Dimension(WIDTH - 10, 20));
		this.add(adjustLabel3);
		//
		sendRequestButton = new JButton("Send this request!");
		sendRequestButton.setPreferredSize(new Dimension(160, 30));
		sendRequestButton.addActionListener(new GetActions());
		this.add(sendRequestButton);

		cancelRequestButton = new JButton("Cancel");
		cancelRequestButton.setPreferredSize(new Dimension(125, 30));
		cancelRequestButton.addActionListener(new GetActions());
		this.add(cancelRequestButton);

		fillList();
	}

	public void fillList()
	{
		p = PersonDAO.getPersonDAO().getThePerson();
		player = PlayerDAO.getPlayerDAO().getOnePlayer(GUIManager.getPlayerPanel().getPlayerUserName());
		model1.removeAllElements();
		model2.removeAllElements();
		if (p.getEstates() != null)
		{
			for (int i = 0; i < p.getEstates().size(); i++)
			{
				if (!p.getEstates().get(i).isMortgage())
					model1.addElement(p.getEstates().get(i).getName());
			}
		}

		if (player.getEstates() != null)
		{
			for (int i = 0; i < player.getEstates().size(); i++)
			{
				if (!player.getEstates().get(i).isMortgage())
					model2.addElement(player.getEstates().get(i).getName());
			}
		}
		maxRecieveMoney = player.getMoney();
	}

	private class GetActions implements ActionListener
	{
		private ArrayList<Estate> giveEstates;
		private ArrayList<Estate> recieveEstates;
		private int recieveMoney;
		private int giveMoney;

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(sendRequestButton))
			{
				if (!giveMoneyField.getText().equals(""))
				{
					if (p.getMoney() < Integer.parseInt(giveMoneyField.getText()))
					{
						JOptionPane.showMessageDialog(null, "Your input (money) is more than your currency!", "Error",
								JOptionPane.ERROR_MESSAGE);
						giveMoneyField.setText("");
						return;
					}
				}
				if(!recieveMoneyField.getText().equals(""))
				{
					if(maxRecieveMoney < Integer.parseInt(recieveMoneyField.getText()))
						JOptionPane.showMessageDialog(null, "Your input (money) is more than the other player's currency!", "Error",
								JOptionPane.ERROR_MESSAGE);
						recieveMoneyField.setText("");
						return;
				}
				if (!offerList1.getSelectedValuesList().isEmpty())
				{
					ArrayList<String> giveEstatesNames = (ArrayList<String>) offerList1.getSelectedValuesList();
					giveEstates = new ArrayList<>();
					for (String st : giveEstatesNames)
					{
						giveEstates.add(EstateDAO.getEstateDAO().getOneEstateByName(st));
					}
				} else
				{
					giveEstates = new ArrayList<>();
				}
				if (!offerList2.getSelectedValuesList().isEmpty())
				{
					ArrayList<String> recieveEstatesNames = (ArrayList<String>) offerList2.getSelectedValuesList();
					recieveEstates = new ArrayList<>();
					for (String st : recieveEstatesNames)
					{
						recieveEstates.add(EstateDAO.getEstateDAO().getOneEstateByName(st));
					}
				} else
				{
					recieveEstates = new ArrayList<>();
				}
				if (!recieveMoneyField.getText().equals(""))
				{
					recieveMoney = Integer.parseInt(recieveMoneyField.getText());
				} else
				{
					recieveMoney = 0;
				}
				if (!giveMoneyField.getText().equals(""))
				{
					giveMoney = Integer.parseInt(giveMoneyField.getText());
				} else
				{
					giveMoney = 0;
				}
				Request request = new Request(giveEstates, recieveEstates, giveMoney, recieveMoney, p.getUserName(),
						player.getUserName());
				Client.getClient().sendObject(request);
				GUIManager.getSendRequestFrame().setVisible(false);
				giveMoneyField.setText("");
				recieveMoneyField.setText("");
			}

			if (arg0.getSource().equals(cancelRequestButton))
			{
				model1.removeAllElements();
				model2.removeAllElements();
				giveMoneyField.setText("");
				recieveMoneyField.setText("");
				GUIManager.getSendRequestFrame().setVisible(false);
			}
		}
	}
}