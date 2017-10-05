package org.bihe.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.bihe.DAO.PersonDAO;
import org.bihe.ui.GUIManager;

public class Person implements Serializable
{
	private static final long serialVersionUID = 1L;
	// =============================Fields===============================
	private String userName;
	private String password;
	private int location = 0;
	private ArrayList<Estate> estates;
	private int money;
	private int pieceNumber;
	private int jail = 0;
	private boolean haveJailCard;
	private boolean isYourTurn;
	private boolean[] pair = new boolean[] { false, false, false };

	// ==========================Constructors============================

	public Person(String userName, String password)
	{
		super();
		this.userName = userName;
		this.password = password;
	}

	public Person(String userName, String password, int location, int money)
	{
		super();
		this.userName = userName;
		this.password = password;
		this.location = location;
		this.money = money;
	}

	// =============================Methods===============================

	public int newLocation(int change)
	{
		int result = this.location + change;
		if (result > 39)
		{
			result -= 40;
			Person p = PersonDAO.getPersonDAO().getThePerson();
			p.setMoney(p.newMoney(200));
			PersonDAO.getPersonDAO().changePerson(p);
			GUIManager.getPlayerPanel().setPriceLabel(p.getMoney() + " M ");
		}

		if (result < 0)
			result += 40;

		return result;
	}

	public int newMoney(int change)
	{
		return (this.money + change);
	}

	public ArrayList<Estate> newEstates_add(ArrayList<Estate> change)
	{
		ArrayList<Estate> result = new ArrayList<>();
		result.addAll(change);
		if (this.estates != null)
			result.addAll(this.estates);

		return result;
	}

	public ArrayList<Estate> newEstates_remove(ArrayList<Estate> change)
	{
		for (int i = 0; i < change.size(); i++)
		{
			for (int j = 0; j < estates.size(); j++)
			{
				if (change.get(i).getPanelNo() == estates.get(j).getPanelNo())
				{
					estates.remove(j);
				}
			}
		}

		return estates;
	}

	public boolean isThreePair()
	{
		if(this.pair[0])
		{
			if(this.pair[1])
			{
				resetPair();
				return true;
			}else{
				this.pair[1] = true;
				return false;
			}
		}else{
			this.pair[0] = true;
			return false;
		}
	}
	
	public void resetPair()
	{
		this.pair = new boolean[] { false, false, false };
	}
	
	
	public void goToJail()
	{
		this.jail = 3;
	}

	public void outOfJail()
	{
		this.jail = 0;
	}

	public void oneRoundInJail()
	{
		this.jail -= 1;
	}

	private void brokeUp()
	{
		if (getMoney() < 0)
		{
			JOptionPane.showMessageDialog(null,
					"Your monye is very low. do one of the followings:\n1- Sell house or hotel."
							+ "\n2- mortgage one of your streets.",
					"Attention", 1);

			GUIManager.getDicePanel().isYourTurn = false;
		} else
		{
			GUIManager.getDicePanel().isYourTurn = true;
		}
	}

	// =======================Getters and Setters=========================
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getLocation()
	{
		return location;
	}

	public void setLocation(int location)
	{
		this.location = location;
	}

	public ArrayList<Estate> getEstates()
	{
		return estates;
	}

	public void setEstates(ArrayList<Estate> streets)
	{
		this.estates = streets;
		GUIManager.getEstatePanel().updateEstates();
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		GUIManager.getPlayerPanel().setPriceLabel(money + " M ");
		this.money = money;
		brokeUp();
	}

	public int getPieceNumber()
	{
		return pieceNumber;
	}

	public void setPieceNumber(int pieceNumber)
	{
		this.pieceNumber = pieceNumber;
	}

	public int getJail()
	{
		return this.jail;
	}

	public boolean HaveJailCard()
	{
		return haveJailCard;
	}

	public void setHaveJailCard(boolean haveJailCard)
	{
		this.haveJailCard = haveJailCard;
	}

	public boolean isYourTurn()
	{
		return isYourTurn;
	}

	public void setYourTurn(boolean isYourTurn)
	{
		GUIManager.getDicePanel().setDiceEnable(isYourTurn);
		this.isYourTurn = isYourTurn;
	}

}