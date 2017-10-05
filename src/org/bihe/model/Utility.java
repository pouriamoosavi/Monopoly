package org.bihe.model;

import java.util.HashMap;

import org.bihe.DAO.EstateDAO;

public class Utility extends Estate implements Rent
{
	private static final long serialVersionUID = -3301710191628595014L;
	private int rentForTwo;

	public Utility(String name, int panelNo, int price, int rent, int mortage, int unMortage)
	{
		super(name, panelNo, price, rent, mortage, unMortage);
	}

	public Utility(String name, int panelNo, int price, int rent, int mortage, int unMortage, int rentForTwo)
	{
		super(name, panelNo, price, rent, mortage, unMortage);
		this.rentForTwo = rentForTwo;
	}

	public int getRentForTwo()
	{
		return rentForTwo;
	}

	public void setRentForTwo(int rentForTwo)
	{
		this.rentForTwo = rentForTwo;
	}

	@Override
	public int rent()
	{
		int count = 0;
		int rent;
		HashMap<Integer, Estate> estates = EstateDAO.getEstateDAO().getEstates();
		if (estates.get(12).getOwner().equals(super.getOwner()))
			count++;
		if (estates.get(28).getOwner().equals(super.getOwner()))
			count++;
		if (count == 1)
			rent = super.getRent();
		else
			rent = rentForTwo;
		
		return rent;
	}

}