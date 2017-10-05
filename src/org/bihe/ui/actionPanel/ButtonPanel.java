package org.bihe.ui.actionPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Data;
import org.bihe.model.Estate;
import org.bihe.model.Exit;
import org.bihe.model.Person;
import org.bihe.model.Street;
import org.bihe.network.client.Client;
import org.bihe.playSound.PlaySound;
import org.bihe.ui.GUIManager;

public class ButtonPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JButton buyHouseOrHotelBtn;
	private JButton sellHouseOrHotelBtn;
	private JButton mortgage;
	private JButton unMortgage;
	private JButton exitBtn;
	private int HEIGHT;
	private int WIDTH;

	public ButtonPanel()
	{
		this.setLayout(new FlowLayout());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		HEIGHT = (int) dimension.getHeight();
		WIDTH = (int) dimension.getWidth() * 10 / 46;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(Color.white);
		this.setBounds(0, 0, WIDTH / 2, (HEIGHT - 30) / 3);
		//
		buyHouseOrHotelBtn = new JButton("Buy a House/Hotel");
		buyHouseOrHotelBtn.setPreferredSize(new Dimension(WIDTH / 2 - 25, (HEIGHT - 30) / 25));
		buyHouseOrHotelBtn.addActionListener(new GetActions());
		this.add(buyHouseOrHotelBtn);
		//
		sellHouseOrHotelBtn = new JButton("Sell a House/Hotel");
		sellHouseOrHotelBtn.setPreferredSize(new Dimension(WIDTH / 2 - 25, (HEIGHT - 30) / 25));
		sellHouseOrHotelBtn.addActionListener(new GetActions());
		this.add(sellHouseOrHotelBtn);
		//
		mortgage = new JButton("mortgage");
		mortgage.setPreferredSize(new Dimension(WIDTH / 2 - 25, (HEIGHT - 30) / 25));
		mortgage.addActionListener(new GetActions());
		this.add(mortgage);
		//
		unMortgage = new JButton("unmortgage");
		unMortgage.setPreferredSize(new Dimension(WIDTH / 2 - 25, (HEIGHT - 30) / 25));
		unMortgage.addActionListener(new GetActions());
		this.add(unMortgage);
		//
		JLabel adjustLabel = new JLabel();
		adjustLabel.setPreferredSize(new Dimension(WIDTH / 2, (HEIGHT - 30) * 2 / 52));
		this.add(adjustLabel);
		//
		exitBtn = new JButton("Exit");
		exitBtn.setPreferredSize(new Dimension(WIDTH / 2 - 25, (HEIGHT - 30) / 25));
		exitBtn.addActionListener(new GetActions());
		this.add(exitBtn);

	}

	private class GetActions implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(buyHouseOrHotelBtn))
			{
				Estate e = GUIManager.getEstatePanel().getEstates().getSelectedValue();

				if (e instanceof Street)
				{
					Person p = PersonDAO.getPersonDAO().getThePerson();
					Street s = (Street) e;
					int money = p.getMoney();
					if (s.buyHouse())
					{
						//
						Thread tr = new Thread(new Runnable()
						{
							@Override
							public void run()
							{
								PlaySound p = new PlaySound("MH.wav");
								p.playSound();
							}
						});
						tr.start();
						//
						money = p.newMoney(-1 * s.getHousesCost());
						p.setMoney(money);
						PlayerDAO.getPlayerDAO().changeOnePlayer(p);
					}
					EstateDAO.getEstateDAO().changeEstate(s);
					Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
					Client.getClient().sendObject(data);

					GUIManager.getPlayerPanel().setPriceLabel(money + " M ");
					GUIManager.getGamePanel().repaint();

				}
			} else if (arg0.getSource().equals(sellHouseOrHotelBtn))
			{

				Estate e = GUIManager.getEstatePanel().getEstates().getSelectedValue();
				if (e instanceof Street)
				{
					Person p = PersonDAO.getPersonDAO().getThePerson();
					Street s = (Street) e;
					int money = p.getMoney();
					if (s.sellHouse())
					{
						//
						Thread tr = new Thread(new Runnable()
						{
							@Override
							public void run()
							{
								PlaySound p = new PlaySound("RH.wav");
								p.playSound();
							}
						});
						tr.start();
						//
						money = p.newMoney(s.getHousesCost() / 2);
						p.setMoney(money);
						PersonDAO.getPersonDAO().changePerson(p);
					}
					EstateDAO.getEstateDAO().changeEstate(s);
					Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
					Client.getClient().sendObject(data);
					GUIManager.getPlayerPanel().setPriceLabel(money + " M ");
					GUIManager.getGamePanel().repaint();

				}
			} else if (arg0.getSource().equals(mortgage))
			{
				Estate e = GUIManager.getEstatePanel().getEstates().getSelectedValue();
				if (e instanceof Street)
				{
					Person p = PersonDAO.getPersonDAO().getThePerson();
					Street s = (Street) e;
					int money = p.getMoney();
					if (s.mortgage())
					{
						money = p.newMoney(s.getMortgage());
						p.setMoney(money);
						PersonDAO.getPersonDAO().changePerson(p);
					}
					EstateDAO.getEstateDAO().changeEstate(s);
					Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
					Client.getClient().sendObject(data);
					GUIManager.getEstatePanel().updateEstates();
					GUIManager.getGamePanel().repaint();

				}

			} else if (arg0.getSource().equals(unMortgage))
			{
				Estate e = GUIManager.getEstatePanel().getEstates().getSelectedValue();
				if (e instanceof Street)
				{
					Person p = PersonDAO.getPersonDAO().getThePerson();
					Street s = (Street) e;
					int money = p.getMoney();
					if (s.unMortgage())
					{
						money = p.newMoney(-1 * s.getUnMortgage());
						p.setMoney(money);
						PersonDAO.getPersonDAO().changePerson(p);
					}
					EstateDAO.getEstateDAO().changeEstate(s);
					Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
					Client.getClient().sendObject(data);
					GUIManager.getEstatePanel().updateEstates();
					GUIManager.getGamePanel().repaint();

				}
			} else if (arg0.getSource().equals(exitBtn))
			{
				Exit exit = new Exit(PersonDAO.getPersonDAO().getThePerson().getUserName());
				Client.getClient().sendObject(exit);
				System.exit(0);
			}
		}
	}
}