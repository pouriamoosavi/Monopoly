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
import org.bihe.network.client.Client;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;

public class CommunityChest
{
	private static ImageIcon imgIconM = new ImageIcon(resources.Resources.getImage("CommunityChest.png"));
	private int location;
	public CommunityChest()
	{

	}

	private int makeRandom()
	{
		int a = (int) (Math.random() * 100);
		while (a > 15 || a == 0)
			a = (int) (Math.random() * 100);

		return a;
	}

	public void communityChest()
	{
		int chanceNumber = makeRandom();
		if (chanceNumber == 1)
		{
			JOptionPane.showMessageDialog(null, "Advance to Go (Collect $200) <Mr. M strides in 7-league boots> ",
					"Community Chest", 1, imgIconM);
			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			location = p.getLocation();
			p.setMoney(p.newMoney(200));
			GamePanel gamePanel = GUIManager.getGamePanel();
			gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(location, 0));
			p.setLocation(0);
			personDao.changePerson(p);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
					EstateDAO.getEstateDAO().getEstates(),
					PersonDAO.getPersonDAO().getThePerson().getPieceNumber(),  location,
					PersonDAO.getPersonDAO().getThePerson().getLocation());
			Client.getClient().sendObject(data);

		} else if (chanceNumber == 2)
		{
			JOptionPane
					.showMessageDialog(null,
							"Bank error in your favor  Collect $200 \n<Mr. M falls back in astonishment as an arm presents"
									+ " a sheaf of cash out of a bank teller's window> ",
							"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(200));
			personDao.changePerson(p);

		} else if (chanceNumber == 3)
		{
			JOptionPane.showMessageDialog(null,
					" Doctor's fees {fee}  Pay $50 <Mr. M angrily brandishes crutches as he stomps with a leg cast>\n"
							+ "From sale of stock you get $50 {$45}\n <Mr. M, happily entangled in the tape of a stock ticker,"
							+ " waves cash (with no $ sign this time)> ",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(-50));
			personDao.changePerson(p);

		} else if (chanceNumber == 4)
		{
			JOptionPane.showMessageDialog(null,
					"Get Out of Jail Free {Get out of Jail, Free}  This card may be kept until needed or sold \n"
							+ "<A winged Mr. M flutters out of a bird cage>",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setHaveJailCard(true);
			personDao.changePerson(p);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);

		} else if (chanceNumber == 5)
		{
			JOptionPane.showMessageDialog(null,
					"Go to Jail  Go directly to jail  Do not pass Go  Do not collect $200 \n"
							+ "<A truncheon-wielding policeman in a light-colored "
							+ "uniform lifts a surprised Mr M by the collar> ",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
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
			personDao.changePerson(p);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
					EstateDAO.getEstateDAO().getEstates(),
					PersonDAO.getPersonDAO().getThePerson().getPieceNumber(),  location,
					PersonDAO.getPersonDAO().getThePerson().getLocation());
			Client.getClient().sendObject(data);

		} else if (chanceNumber == 6)
		{
			JOptionPane.showMessageDialog(null,
					"Grand Opera Night {Opening}  Collect $50 from every player for opening night seats\n "
							+ "<A wall sign near steps reads \"Opera Tonite - 8 PM Sharp\";\n"
							+ " Mr. M leans against it checking his pocket watch in annoyance>",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			//
			PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
			HashMap<String, Person> playersMap = playerDao.getPlayers();
			for (Entry<String, Person> E : playersMap.entrySet())
			{
				if (E.getKey() != p.getUserName())
				{
					E.getValue().setMoney(E.getValue().newMoney(-50));
					p.setMoney(p.newMoney(50));
				}
			}
			//
			playerDao.changePlayerDAO(playersMap);
			personDao.changePerson(p);

			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (chanceNumber == 7)
		{
			JOptionPane.showMessageDialog(null,
					"Holiday {Xmas} Fund matures - Receive {Collect} $100 \n"
							+ "<Mr. M carries along a giant Xmas sock containing a sheaf of cash>",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(100));
			personDao.changePerson(p);

		} else if (chanceNumber == 8)
		{
			JOptionPane.showMessageDialog(null,
					"Income tax refund  Collect $20 \n<Mr M faints back against a man displaying the Refund paper> ",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(20));
			personDao.changePerson(p);

		} else if (chanceNumber == 9)
		{
			JOptionPane.showMessageDialog(null,
					"It is your birthday \n- Collect $10 from each player {Not in the deck}", "Community Chest", 1,
					imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			//
			PlayerDAO playerDao = PlayerDAO.getPlayerDAO();
			HashMap<String, Person> playersMap = playerDao.getPlayers();
			for (Entry<String, Person> E : playersMap.entrySet())
			{
				if (E.getKey() != p.getUserName())
				{
					E.getValue().setMoney(E.getValue().newMoney(-10));
					p.setMoney(p.newMoney(10));
				}
			}
			//
			playerDao.changePlayerDAO(playersMap);
			personDao.changePerson(p);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (chanceNumber == 10)
		{
			JOptionPane.showMessageDialog(null,
					"Life insurance matures  Collect $100 \n"
							+ "<Below an I N S sign stands a bent Mr M, his long beard brushing the floor>",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(100));
			personDao.changePerson(p);

		} else if (chanceNumber == 11)
		{
			JOptionPane.showMessageDialog(null,
					"Pay hospital fees of $100 {Pay hospital $100} \n" + "<A bored nurse holds out her hand for payment"
							+ " while Mr. M holds 2 swaddled infants, one in each arm> ",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(-100));
			personDao.changePerson(p);

			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (chanceNumber == 12)
		{
			JOptionPane.showMessageDialog(null,
					"Pay school fees {tax} of $150 \n<A bespectacled schoolboy unhappily receives a head pat \nand a dime"
							+ " ((Rockefeller style) from Mr. M.>",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(-150));
			personDao.changePerson(p);
			Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
			Client.getClient().sendObject(data);
		} else if (chanceNumber == 13)
		{
			JOptionPane.showMessageDialog(null,
					"Receive $25 consultancy fee {Receive for services $25}\n"
							+ "<As Justice of the Peace, a stern Mr. M holds out his hand\n"
							+ "for fee from an embarrassed groom whose bride hold a bouquet behind him> ",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(25));
			personDao.changePerson(p);

		} else if (chanceNumber == 14)
		{
			JOptionPane.showMessageDialog(null,
					"You are assessed for street repairs  $40 per house  $115 per hotel \n"
							+ "<Mr. M., supported by his near-ubiquitous cane in his left hand,\n"
							+ " holds a pick and shovel over his right shoulder> ",
					"Community Chest", 1, imgIconM);

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
						cost = ((Street) E).getHouseCount() * 40;
						if (((Street) E).isHotelExist())
							cost = cost + 115;

					}
				}
				p.setMoney(p.newMoney(-cost));
				personDao.changePerson(p);
				Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(), EstateDAO.getEstateDAO().getEstates());
				Client.getClient().sendObject(data);
			}
		} else if (chanceNumber == 15)
		{
			JOptionPane.showMessageDialog(null, "You have won second prize in a beauty contest  Collect $10 \n"
					+ "<Mr. M preens with a sash and large bouquet> ", "Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(10));
			personDao.changePerson(p);

		} else if (chanceNumber == 16)
		{
			JOptionPane
					.showMessageDialog(null,
							"You inherit $100 <Mr M.\n holds his head as unseen people's hands offer brochures titled"
									+ " \"Buy Yacht\", \"World Tour\", and \"Rolls Royce\"> ",
							"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(100));
			personDao.changePerson(p);

		} else if (chanceNumber == 17)
		{
			JOptionPane.showMessageDialog(null,
					" From sale of stock you get $50 {$45} \n<Mr. M, happily entangled in the "
							+ "tape of a stock ticker,\nwaves cash (with no $ sign this time)> ",
					"Community Chest", 1, imgIconM);

			PersonDAO personDao = PersonDAO.getPersonDAO();
			Person p = personDao.getThePerson();
			p.setMoney(p.newMoney(50));
			personDao.changePerson(p);

		}
	}
}