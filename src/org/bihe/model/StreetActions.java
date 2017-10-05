package org.bihe.model;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.chancesAndCommunityChset.Chance;
import org.bihe.ui.chancesAndCommunityChset.CommunityChest;

public class StreetActions
{
	private HashMap<Integer, Estate> estates;
	private Person person;
	private static boolean buy;

	public StreetActions()
	{
		
	}

	public void action()
	{
		estates = EstateDAO.getEstateDAO().getEstates();
		person = PersonDAO.getPersonDAO().getThePerson();
		PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
		if (person.getLocation() == 1 || person.getLocation() == 3 || person.getLocation() == 6
				|| person.getLocation() == 8 || person.getLocation() == 9 || person.getLocation() == 11
				|| person.getLocation() == 13 || person.getLocation() == 14 || person.getLocation() == 16
				|| person.getLocation() == 18 || person.getLocation() == 19 || person.getLocation() == 21
				|| person.getLocation() == 23 || person.getLocation() == 24 || person.getLocation() == 26
				|| person.getLocation() == 27 || person.getLocation() == 29 || person.getLocation() == 31
				|| person.getLocation() == 32 || person.getLocation() == 34 || person.getLocation() == 37
				|| person.getLocation() == 39)
		{

			Street s = (Street) estates.get(person.getLocation());
			if (!s.isOwned())
			{
				if (person.getMoney() < s.getPrice())
				{
					JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
				} else
				{
					GUIManager.getBuyStreetDialog().setVisible(true);
					if (buy)
					{
						int money = person.newMoney(-1 * s.getPrice());
						person.setMoney(money);
						s.setOwned(true);
						s.setOwner(person.getUserName());

						ArrayList<Estate> e = new ArrayList<>();
						e.add(s);
						person.setEstates(person.newEstates_add(e));

					} 
				}
			} else
			{
				if (!s.getOwner().equals(person.getUserName()))
				{
					Person owner = playerDao.getOnePlayer(s.getOwner());
					owner.setMoney(owner.newMoney(s.rent()));
					person.setMoney(person.newMoney(-1 * s.rent()));
					playerDao.changeOnePlayer(owner);
				}
			}
			EstateDAO.getEstateDAO().changeEstate(s);
			PersonDAO.getPersonDAO().changePerson(person);

			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		}
		if (person.getLocation() == 5 || person.getLocation() == 15 || person.getLocation() == 25
				|| person.getLocation() == 35)
		{
			RailRoad r = (RailRoad) estates.get(person.getLocation());
			if (!r.isOwned())
			{
				if (person.getMoney() < r.getPrice())
				{
					JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
				} else
				{
					if (person.getMoney() < r.getPrice())
					{
						JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
						return;
					}
					GUIManager.getBuyStreetDialog().setVisible(true);
					if (buy)
					{
						int money = person.newMoney(-1 * r.getPrice());
						person.setMoney(money);
						r.setOwned(true);
						r.setOwner(person.getUserName());

						ArrayList<Estate> e = new ArrayList<>();
						e.add(r);
						person.setEstates(person.newEstates_add(e));

					} 
				}
			} else
			{
				if (!r.getOwner().equals(person.getUserName()))
				{
					Person owner = playerDao.getOnePlayer(r.getOwner());
					owner.setMoney(owner.newMoney(r.rent()));
					person.setMoney(person.newMoney(-1 * r.rent()));
					playerDao.changeOnePlayer(owner);

				}
			}
			EstateDAO.getEstateDAO().changeEstate(r);
			PersonDAO.getPersonDAO().changePerson(person);

			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		}
		if (person.getLocation() == 12 || person.getLocation() == 28)
		{
			Utility u = (Utility) estates.get(person.getLocation());
			if (!u.isOwned())
			{
				if (person.getMoney() < u.getPrice())
				{
					JOptionPane.showMessageDialog(null, "You don't have enough money to buy this Street");
				} else
				{
					GUIManager.getBuyStreetDialog().setVisible(true);
					if (buy)
					{
						int money = person.newMoney(-1 * u.getPrice());
						person.setMoney(money);
						u.setOwned(true);
						u.setOwner(person.getUserName());

						ArrayList<Estate> e = new ArrayList<>();
						e.add(u);
						person.setEstates(person.newEstates_add(e));
					}
				}
			} else
			{
				if (!u.getOwner().equals(person.getUserName()))
				{
					Person owner = playerDao.getOnePlayer(u.getOwner());
					owner.setMoney(owner.newMoney(u.rent()));
					person.setMoney(person.newMoney(-1 * u.rent()));
					playerDao.changeOnePlayer(owner);
				}
			}
			EstateDAO.getEstateDAO().changeEstate(u);
			PersonDAO.getPersonDAO().changePerson(person);

			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (person.getLocation() == 7 || person.getLocation() == 22 || person.getLocation() == 36)
		{
			Chance c = new Chance();
			c.chance();
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (person.getLocation() == 17 || person.getLocation() == 2 || person.getLocation() == 33)
		{
			CommunityChest c = new CommunityChest();
			c.communityChest();
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (person.getLocation() == 4)
		{
			person.setMoney(person.newMoney(-200));
			PersonDAO.getPersonDAO().changePerson(person);
			playerDao.changeOnePlayer(person);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (person.getLocation() == 38)
		{
			person.setMoney(person.newMoney(-100));
			PersonDAO.getPersonDAO().changePerson(person);
			playerDao.changeOnePlayer(person);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (person.getLocation() == 30)
		{
			if (person.HaveJailCard())
			{
				person.setHaveJailCard(false);
				JOptionPane.showMessageDialog(null, "You've lost your Get Out of Jail Card!", "Attention", 1);
			} else
			{
				person.setLocation(10);
				person.goToJail();
				GamePanel gamePanel = GUIManager.getGamePanel();
				JOptionPane.showMessageDialog(null, "you will go to jail");
				gamePanel.movePieceOnePlace(person.getPieceNumber(), gamePanel.distance(30, 10));
			}
			PersonDAO.getPersonDAO().changePerson(person);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		}
	}
	
	public static boolean isBuy()
	{
		return buy;
	}

	public static void setBuy(boolean abuy)
	{
		buy = abuy;
	}

}