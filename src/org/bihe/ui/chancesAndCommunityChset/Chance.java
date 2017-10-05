package org.bihe.ui.chancesAndCommunityChset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Data;
import org.bihe.model.Estate;
import org.bihe.model.Person;
import org.bihe.model.Street;
import org.bihe.model.StreetActions;
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;
import org.bihe.ui.actionPanel.DicePanel;

public class Chance
{
	private int location;
	private static ImageIcon imgIconC = new ImageIcon(resources.Resources.getImage("Chance.png"));

	public Chance()
	{
		
	}

	private int makeRandom()
	{
		int a = (int) (Math.random() * 100);
		while (a > 15 || a == 0)
			a = (int) (Math.random() * 100);

		return a;
	}

	public void chance()
	{
		int chanceNumber = makeRandom();
		if (chanceNumber == 1)
		{
			JOptionPane.showMessageDialog(null, "Advance to Go (Collect $200) (Mr. M hops on both feet.) ", "Chance", 1,
					imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(200));
			location = p.getLocation();
			GamePanel gamePanel = GUIManager.getGamePanel();
			gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(location, 0));
			p.setLocation(0);
			personDao.changePerson(p);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
					EstateDAO.getEstateDAO().getEstates(),
					PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), location,
					PersonDAO.getPersonDAO().getThePerson().getLocation());
			Client.getClient().sendObject(data);
			
		} else if (chanceNumber == 2)
		{
			JOptionPane.showMessageDialog(null,
					"Advance to Illinois Ave. - If you pass Go, collect $200 {Second sentence omitted.}\n"
							+ " (Mr. M has tied a cloth bundle onto his cane to make a bindle, carried over his right shoulder, and is smoking a cigar) ",
					"Chance", 1, imgIconC);

			// Illinois Ave is 24th panel.
			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			location = p.getLocation();
			GamePanel gamePanel = GUIManager.getGamePanel();
			gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(location, 24));
			p.setLocation(24);
			if (location - 24 > 0)
			{
				p.setMoney(p.newMoney(200));
			}
			personDao.changePerson(p);
			StreetActions st = new StreetActions();
			st.action();
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
					EstateDAO.getEstateDAO().getEstates(),
					PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), location,
					PersonDAO.getPersonDAO().getThePerson().getLocation());
			Client.getClient().sendObject(data);

		} else if (chanceNumber == 3)
		{
			JOptionPane.showMessageDialog(null, "Advance to St. Charles Place Â If you pass Go, collect $200 \n"
					+ "(Mr. M hurries along, hauling a little boy by the hand) ", "Chance", 1, imgIconC);

			// St. Charles place is 11th panel.
			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			location = p.getLocation();
			GamePanel gamePanel = GUIManager.getGamePanel();
			gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(location, 11));
			p.setLocation(11);
			if (location - 11 > 0)
			{
				p.setMoney(p.newMoney(200));
			}
			personDao.changePerson(p);
			StreetActions st = new StreetActions();
			st.action();
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
					EstateDAO.getEstateDAO().getEstates(),
					PersonDAO.getPersonDAO().getThePerson().getPieceNumber(),  location,
					PersonDAO.getPersonDAO().getThePerson().getLocation());
			Client.getClient().sendObject(data);

		} else if (chanceNumber == 4)
		{
			JOptionPane.showMessageDialog(null,
					"Advance token to nearest Utility. If unowned, you may buy it from the Bank. \n"
							+ "If owned, throw dice and pay owner a total ten times the amount thrown. \n"
							+ "(Mr. M trudges along with a huge battleship token on his back) ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			location = p.getLocation();
			if (location == 7)
			{
				GamePanel gamePanel = GUIManager.getGamePanel();
				gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(7, 12));
				p.setLocation(12);
				if (EstateDAO.getEstateDAO().getOneEstate(12).isOwned())
				{
					String utilityOwnerName = EstateDAO.getEstateDAO().getOneEstate(12).getOwner();
					if (!utilityOwnerName.equals(p.getUserName()))
					{
						DicePanel.moveOnToss = false;
						int changeMoney = GUIManager.getDicePanel().getDiceMultipiedTen();
						p.setMoney(p.newMoney(-changeMoney));
						//
						PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
						if (playerDao.getOnePlayer(utilityOwnerName) != null)
						{
							Person utilityOwner = playerDao.getOnePlayer(utilityOwnerName);
							utilityOwner.setMoney(utilityOwner.newMoney(changeMoney));
							playerDao.changeOnePlayer(utilityOwner);
							// playerDao.changeOnePlayer(p);
						}
						//
						DicePanel.moveOnToss = true;
					}
				} else
				{
					if (p.newMoney(-150) > 0)
					{
						p.setMoney(p.newMoney(-150));
						ArrayList<Estate> ar = new ArrayList<>();
						EstateDAO.getEstateDAO().getOneEstate(12).setOwner(p.getUserName());
						EstateDAO.getEstateDAO().getOneEstate(12).setOwned(true);
						p.setEstates(p.newEstates_add(ar));

					} 
				}
				EstateDAO.getEstateDAO().changeEstate(EstateDAO.getEstateDAO().getOneEstate(12));
				Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
						EstateDAO.getEstateDAO().getEstates(),
						PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), location,
						PersonDAO.getPersonDAO().getThePerson().getLocation());
				Client.getClient().sendObject(data);
				
			} else if (location == 22)
			{
				GamePanel gamePanel = GUIManager.getGamePanel();
				gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(22, 28));
				p.setLocation(28);
				if (EstateDAO.getEstateDAO().getOneEstate(28).isOwned())
				{
					String utilityOwnerName = EstateDAO.getEstateDAO().getOneEstate(28).getOwner();
					if (!utilityOwnerName.equals(p.getUserName()))
					{
						DicePanel.moveOnToss = false;
						int changeMoney = GUIManager.getDicePanel().getDiceMultipiedTen();
						p.setMoney(p.newMoney(-changeMoney));
						//
						PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
						if (playerDao.getOnePlayer(utilityOwnerName) != null)
						{
							Person utilityOwner = playerDao.getOnePlayer(utilityOwnerName);
							utilityOwner.setMoney(utilityOwner.newMoney(changeMoney));
							playerDao.changeOnePlayer(utilityOwner);
						}
						//
						DicePanel.moveOnToss = true;
					}
				} else
				{
					if (p.newMoney(-150) > 0)
					{
						p.setMoney(p.newMoney(-150));
						ArrayList<Estate> ar = new ArrayList<>();
						EstateDAO.getEstateDAO().getOneEstate(28).setOwner(p.getUserName());
						EstateDAO.getEstateDAO().getOneEstate(28).setOwned(true);
						p.setEstates(p.newEstates_add(ar));

					} 
				}
				EstateDAO.getEstateDAO().changeEstate(EstateDAO.getEstateDAO().getOneEstate(28));
				Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
						EstateDAO.getEstateDAO().getEstates(),
						PersonDAO.getPersonDAO().getThePerson().getPieceNumber(),  location,
						PersonDAO.getPersonDAO().getThePerson().getLocation());
				Client.getClient().sendObject(data);

			}
			personDao.changePerson(p);

		} else if (chanceNumber == 5)
		{
			JOptionPane.showMessageDialog(null,
					"Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he}"
							+ " is otherwise entitled.\n If Railroad is unowned, you may buy it from the Bank. "
							+ "(There are two of these.)\n (At lower left, Mr. M carries a large flatiron token with two hands;"
							+ " at upper right a railroad crossing is seen) ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			location = p.getLocation();
			if (location == 7)
			{
				GamePanel gamePanel = GUIManager.getGamePanel();
				gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(7, 15));
				p.setLocation(15);
				if (EstateDAO.getEstateDAO().getOneEstate(15).isOwned())
				{
					String utilityOwnerName = EstateDAO.getEstateDAO().getOneEstate(15).getOwner();
					if (!utilityOwnerName.equals(p.getUserName()))
					{
						int changeMoney = (EstateDAO.getEstateDAO().getOneEstate(15).getRent()) * 2;
						p.setMoney(p.newMoney(-changeMoney));
						//
						PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
						if (playerDao.getOnePlayer(utilityOwnerName) != null)
						{
							Person utilityOwner = playerDao.getOnePlayer(utilityOwnerName);
							utilityOwner.setMoney(utilityOwner.newMoney(changeMoney));
							playerDao.changeOnePlayer(utilityOwner);
							// playerDao.changeOnePlayer(p);
						}
						//
					}

				} else
				{
					if (p.newMoney(-200) > 0)
					{
						p.setMoney(p.newMoney(-200));
						ArrayList<Estate> ar = new ArrayList<>();
						EstateDAO.getEstateDAO().getOneEstate(15).setOwner(p.getUserName());
						EstateDAO.getEstateDAO().getOneEstate(15).setOwned(true);
						p.setEstates(p.newEstates_add(ar));

					} else
					{
						// õTODO don't have enough money.
					}
				}
				EstateDAO.getEstateDAO().changeEstate(EstateDAO.getEstateDAO().getOneEstate(15));
				Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
						EstateDAO.getEstateDAO().getEstates(),
						PersonDAO.getPersonDAO().getThePerson().getPieceNumber(),  location,
						PersonDAO.getPersonDAO().getThePerson().getLocation());
				Client.getClient().sendObject(data);
	
			} else if (location == 22)
			{
				GamePanel gamePanel = GUIManager.getGamePanel();
				gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(22, 25));
				p.setLocation(25);
				if (EstateDAO.getEstateDAO().getOneEstate(25).isOwned())
				{
					String utilityOwnerName = EstateDAO.getEstateDAO().getOneEstate(25).getOwner();
					if (!utilityOwnerName.equals(p.getUserName()))
					{
						int changeMoney = (EstateDAO.getEstateDAO().getOneEstate(15).getRent()) * 2;
						p.setMoney(p.newMoney(-changeMoney));
						//
						PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
						if (playerDao.getOnePlayer(utilityOwnerName) != null)
						{
							Person utilityOwner = playerDao.getOnePlayer(utilityOwnerName);
							utilityOwner.setMoney(utilityOwner.newMoney(changeMoney));
							playerDao.changeOnePlayer(utilityOwner);
							// playerDao.changeOnePlayer(p);
						}
						//
					}

				} else
				{
					if (p.newMoney(-200) > 0)
					{
						p.setMoney(p.newMoney(-200));
						ArrayList<Estate> ar = new ArrayList<>();
						EstateDAO.getEstateDAO().getOneEstate(25).setOwner(p.getUserName());
						EstateDAO.getEstateDAO().getOneEstate(25).setOwned(true);
						p.setEstates(p.newEstates_add(ar));

					} 
				}
				EstateDAO.getEstateDAO().changeEstate(EstateDAO.getEstateDAO().getOneEstate(25));
				Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
						EstateDAO.getEstateDAO().getEstates(),
						PersonDAO.getPersonDAO().getThePerson().getPieceNumber(),  location,
						PersonDAO.getPersonDAO().getThePerson().getLocation());
				Client.getClient().sendObject(data);

			}
			PersonDAO.getPersonDAO().changePerson(p);
		} else if (chanceNumber == 6)
		{
			JOptionPane.showMessageDialog(null,
					"Bank pays you dividend of $50 (With his feet up on a telephone-bearing desk, \n"
							+ "Mr. M leans back in an overstuffed chair, blowing cigar smoke rings) ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(50));
			personDao.changePerson(p);
			
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (chanceNumber == 7)
		{
			JOptionPane.showMessageDialog(null,
					" Get out of Jail Free Â This card may be kept until needed, or traded/sold \n"
							+ "{This card may be kept until needed or sold - Get Out of Jail Free}\n"
							+ "{The first sentence is much smaller than the second} \n"
							+ "(Mr. M, in close-fitting one-piece prison stripes, is literally kicked out) ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setHaveJailCard(true);
			personDao.changePerson(p);
			
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);

		} else if (chanceNumber == 8)
		{
			JOptionPane.showMessageDialog(null,
					" Go Back 3 Spaces (Mr. M is hauled back by a cane hooked around his neck) \n"
							+ "{Presumably an allusion to amateur nights at theaters} ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			location = p.getLocation();
			GamePanel gamePanel = GUIManager.getGamePanel();
			gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(p.getLocation(), p.newLocation(-3)));
			p.setLocation(p.newLocation(-3));
			personDao.changePerson(p);
			//
			PersonDAO.getPersonDAO().changePerson(p);		
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
					EstateDAO.getEstateDAO().getEstates(),
					PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), location,
					PersonDAO.getPersonDAO().getThePerson().getLocation());
			Client.getClient().sendObject(data);
			StreetActions st = new StreetActions();
			st.action();
		} else if (chanceNumber == 9)
		{
			JOptionPane.showMessageDialog(null,
					"Go to Jail Â Go directly to Jail Â Do not pass Go, do not collect $200 \n"
							+ "(A truncheon-carrying policeman in a dark-colored uniform hauls a surprised Mr M along by the feet) ",
					"Chance", 1, imgIconC);

			Person p = PersonDAO.getPersonDAO().getThePerson();
			location = p.getLocation();
			if (p.HaveJailCard())
			{
				p.setHaveJailCard(false);
				JOptionPane.showMessageDialog(null, "You've lost your Get Out of Jail Card!", "Attention", 1);
			} else
			{
				p.goToJail();
				p.setLocation(10);
				GamePanel gamePanel = GUIManager.getGamePanel();
				gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(location, 10));
			}
			PersonDAO.getPersonDAO().changePerson(p);		
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
					EstateDAO.getEstateDAO().getEstates(),
					PersonDAO.getPersonDAO().getThePerson().getPieceNumber(),  location,
					PersonDAO.getPersonDAO().getThePerson().getLocation());
			Client.getClient().sendObject(data);
			
		} else if (chanceNumber == 10)
		{
			JOptionPane.showMessageDialog(null,
					"Make general repairs on all your property Â For each house pay $25 Â For each hotel $100 \n"
							+ "(Consulting a \"How to Fix It\" brochure, a hammer-wielding \n"
							+ "Mr. M sits astride a house not much larger than he is; it buckles under his weight) ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			ArrayList<Estate> PersonEstates = new ArrayList<>();
			PersonEstates = p.getEstates();
			int cost = 0;
			if (!PersonEstates.isEmpty())
			{
				for (Estate E : PersonEstates)
				{
					if (E instanceof Street)
					{
						cost = ((Street) E).getHouseCount() * 25;
						if (((Street) E).isHotelExist())
							cost = cost + 100;

					}
				}
				p.setMoney(p.newMoney(-cost));
				personDao.changePerson(p);
			}
		} else if (chanceNumber == 11)
		{
			JOptionPane
					.showMessageDialog(null,
							"Pay poor tax of $15 (His trouser pockets pulled out to show them empty, Mr. M spreads his hands)\n"
									+ " (The video game version replaces this with Speeding fine $15,)",
							"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(-15));
			personDao.changePerson(p);
		} else if (chanceNumber == 12)
		{
			JOptionPane.showMessageDialog(null,
					"Take a trip to Reading Railroad {Take a ride on the Reading} Â If you pass Go, collect $200\n"
							+ " (Mr. M rides astride the TOOTing engine of a train) ",
					"Chance", 1, imgIconC);

			// ReadingRailRoad is 5th panel;
			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			int location = p.getLocation();
			GamePanel gamePanel = GUIManager.getGamePanel();
			gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(location, 5));
			p.setLocation(5);
			if (location - 5 > 0)
			{
				p.setMoney(p.newMoney(200));
			}
			personDao.changePerson(p);
			StreetActions st = new StreetActions();
			st.action();
		} else if (chanceNumber == 13)
		{
			JOptionPane.showMessageDialog(null,
					"Take a walk on the Boardwalk Â Advance token to Boardwalk {Board Walk in both sentences}\n "
							+ "(Mr. M, a smallish dog hung over one arm, with the other pushes a squalling baby in a small pram;\n"
							+ " behind them, birds fly in the sky above a low fence) ",
					"Chance", 1, imgIconC);
			// TODO what the fuck?!
		} else if (chanceNumber == 14)
		{
			JOptionPane.showMessageDialog(null,
					" You have been elected Chairman of the Board Â Pay each player $50 \n"
							+ "(A newsboy shouts an Extra with Mr. M's headshot on its front page) ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			//
			PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
			HashMap<String, Person> playersMap = playerDao.getPlayers();
			for (Entry<String, Person> E : playersMap.entrySet())
			{
				p.setMoney(p.newMoney(-50));
				if (E.getKey() != p.getUserName())
					E.getValue().setMoney(E.getValue().newMoney(50));

			}
			personDao.changePerson(p);
			playerDao.changePlayerDAO(playersMap);
			
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);

		} else if (chanceNumber == 15)
		{
			JOptionPane.showMessageDialog(null,
					"Your building {and} loan matures Â Collect $150 {Up until the 1980s a \"building and loan\"\n"
							+ " was a financial institution.} (Mr. M joyfully embraces an apparent wife) ",
					"Chance", 1, imgIconC);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(150));
			personDao.changePerson(p);
		}
	}

}