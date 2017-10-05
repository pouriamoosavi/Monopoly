package org.bihe.model;

import java.util.HashMap;

import org.bihe.DAO.EstateDAO;

public class RailRoad extends Estate implements Rent
{
	private static final long serialVersionUID = 6897047567218898676L;
	private int rentForTwo;
	private int rentForThree;
	private int rentForfour;

	public RailRoad(String name, int panelNo, int price, int rent, int mortage, int unMortage)
	{
		super(name, panelNo, price, rent, mortage, unMortage);
	}

	public RailRoad(String name, int panelNo, int price, int rent, int mortage, int unMortage, int rentForTwo,
			int rentForThree, int rentForfour)
	{
		super(name, panelNo, price, rent, mortage, unMortage);
		this.rentForTwo = rentForTwo;
		this.rentForThree = rentForThree;
		this.rentForfour = rentForfour;
	}

	public int getRentForTwo()
	{
		return rentForTwo;
	}

	public void setRentForTwo(int rentForTwo)
	{
		this.rentForTwo = rentForTwo;
	}

	public int getRentForThree()
	{
		return rentForThree;
	}

	public void setRentForThree(int rentForThree)
	{
		this.rentForThree = rentForThree;
	}

	public int getRentForfour()
	{
		return rentForfour;
	}

	public void setRentForfour(int rentForfour)
	{
		this.rentForfour = rentForfour;
	}

	@Override
	public int rent()
	{
		int count = 0;
		int rent;
		HashMap<Integer, Estate> estates = EstateDAO.getEstateDAO().getEstates();
		if (estates.get(5).getOwner().equals(super.getOwner()))
			count++;
		if (estates.get(15).getOwner().equals(super.getOwner()))
			count++;
		if (estates.get(25).getOwner().equals(super.getOwner()))
			count++;
		if (estates.get(35).getOwner().equals(super.getOwner()))
			count++;
		if (count == 1)
			rent = super.getRent();
		else if (count == 2)
			rent = rentForTwo;
		else if (count == 3)
			rent = rentForThree;
		else
			rent = rentForfour;

		return rent;
	}

}