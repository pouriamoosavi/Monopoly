package org.bihe.model;

import java.io.Serializable;

public abstract class Estate implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private int panelNo;
	private int price;
	private int rent;
	private int mortgage;
	private int unMortgage;
	private boolean isOwned;
	private String owner;
	private boolean isMortgage = false;

	public Estate(String name, int panelNo, int price, int rent, int mortgage, int unMortgage)
	{
		this.name = name;
		this.panelNo = panelNo;
		this.price = price;
		this.rent = rent;
		this.mortgage = mortgage;
		this.unMortgage = unMortgage;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getPanelNo()
	{
		return panelNo;
	}

	public void setPanelNo(int panelNo)
	{
		this.panelNo = panelNo;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public int getRent()
	{
		return rent;
	}

	public void setRent(int rent)
	{
		this.rent = rent;
	}

	public int getMortgage()
	{
		return mortgage;
	}

	public void setMortgage(int mortgage)
	{
		this.mortgage = mortgage;
	}

	public int getUnMortgage()
	{
		return unMortgage;
	}

	public void setUnMortgage(int unMortgage)
	{
		this.unMortgage = unMortgage;
	}

	public boolean isMortgage()
	{
		return isMortgage;
	}

	public void setMortgage(boolean isMortgage)
	{
		this.isMortgage = isMortgage;
	}

	public boolean isOwned()
	{
		return isOwned;
	}

	public void setOwned(boolean isOwned)
	{
		this.isOwned = isOwned;
	}

	public String getOwner()
	{
		return owner;
	}

	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	@Override
	public String toString()
	{
		if (isMortgage)
			return this.getName() + "  (mortgaged)";
		return this.getName();
	}

}
