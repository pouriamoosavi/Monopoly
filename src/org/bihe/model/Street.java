package org.bihe.model;

import java.util.HashMap;

import javax.swing.JOptionPane;

import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;

public class Street extends Estate implements Rent
{
	private static final long serialVersionUID = 1L;
	private int colorSetRent;
	private int hs1Rent;
	private int hs2Rent;
	private int hs3Rent;
	private int hs4Rent;
	private int ht1Rent;
	private int housesCost;
	private int HotelsCost;
	private int houseCount;
	private boolean isHotelExist;
	private boolean isColorSet;

	public Street(String name, int price, int panelNo, int rent, int mortage, int unMortage)
	{
		super(name, panelNo, price, rent, mortage, unMortage);
	}

	public Street(String name, int panelNo, int price, int rent, int mortage, int unMortage, int rentColorSet,
			int rent1Hs, int rent2Hs, int rent3Hs, int rent4Hs, int rent1Ht, int housesCost, int hotelsCost)
	{
		super(name, panelNo, price, rent, mortage, unMortage);
		this.colorSetRent = rentColorSet;
		this.hs1Rent = rent1Hs;
		this.hs2Rent = rent2Hs;
		this.hs3Rent = rent3Hs;
		this.hs4Rent = rent4Hs;
		this.ht1Rent = rent1Ht;
		this.housesCost = housesCost;
		HotelsCost = hotelsCost;
	}

	public int getColorSetRent()
	{
		return colorSetRent;
	}

	public void setColorSetRent(int colorSetRent)
	{
		this.colorSetRent = colorSetRent;
	}

	public int getHs1Rent()
	{
		return hs1Rent;
	}

	public void setHs1Rent(int hs1Rent)
	{
		this.hs1Rent = hs1Rent;
	}

	public int getHs2Rent()
	{
		return hs2Rent;
	}

	public void setHs2Rent(int hs2Rent)
	{
		this.hs2Rent = hs2Rent;
	}

	public int getHs3Rent()
	{
		return hs3Rent;
	}

	public void setHs3Rent(int hs3Rent)
	{
		this.hs3Rent = hs3Rent;
	}

	public int getHs4Rent()
	{
		return hs4Rent;
	}

	public void setHs4Rent(int hs4Rent)
	{
		this.hs4Rent = hs4Rent;
	}

	public int getHt1Rent()
	{
		return ht1Rent;
	}

	public void setHt1Rent(int ht1Rent)
	{
		this.ht1Rent = ht1Rent;
	}

	public int getHousesCost()
	{
		return housesCost;
	}

	public void setHousesCost(int housesCost)
	{
		this.housesCost = housesCost;
	}

	public int getHotelsCost()
	{
		return HotelsCost;
	}

	public void setHotelsCost(int hotelsCost)
	{
		HotelsCost = hotelsCost;
	}

	public int getHouseCount()
	{
		return houseCount;
	}

	public void setHouseCount(int houseCount)
	{
		this.houseCount = houseCount;
	}

	public boolean isHotelExist()
	{
		return isHotelExist;
	}

	public void setHotelExist(boolean isHotelExist)
	{
		this.isHotelExist = isHotelExist;
	}

	public boolean isColorSet()
	{
		return isColorSet;
	}

	public void setColorSet(boolean isColorSet)
	{
		this.isColorSet = isColorSet;
	}

	@Override
	public int rent()
	{
		int rent;
		if (isHotelExist)
			rent = ht1Rent;
		else if (houseCount == 0)
			rent = super.getRent();
		else if (houseCount == 1)
			rent = hs1Rent;
		else if (houseCount == 2)
			rent = hs2Rent;
		else if (houseCount == 3)
			rent = hs3Rent;
		else
			rent = hs4Rent;

		return rent;
	}

	private boolean haveEstate(int panelNo)
	{
		Person p = PersonDAO.getPersonDAO().getThePerson();
		for (int i = 0; i < p.getEstates().size(); i++)
		{
			if (p.getEstates().get(i).getPanelNo() == panelNo)
				return true;
		}
		return false;
	}

	public boolean buyHouse()
	{
		HashMap<Integer, Estate> estates = EstateDAO.getEstateDAO().getEstates();
		Person p = PersonDAO.getPersonDAO().getThePerson();

		if (haveEstate(1) && haveEstate(3) && !isHotelExist && (super.getPanelNo() == 1 || super.getPanelNo() == 3))
		{
			if (estates.get(1).isMortgage() || estates.get(3).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}
			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible fgd gdf fg");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}

			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;

		} else if (haveEstate(6) && haveEstate(8) && haveEstate(9) && !isHotelExist
				&& (super.getPanelNo() == 6 || super.getPanelNo() == 8 || super.getPanelNo() == 9))
		{
			if (estates.get(6).isMortgage() || estates.get(8).isMortgage() || estates.get(9).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}

			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}
			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;

		} else if (haveEstate(11) && haveEstate(13) && haveEstate(14) && !isHotelExist
				&& (super.getPanelNo() == 11 || super.getPanelNo() == 13 || super.getPanelNo() == 14))
		{
			if (estates.get(11).isMortgage() || estates.get(13).isMortgage() || estates.get(14).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}
			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}
			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;

		} else if (haveEstate(16) && haveEstate(18) && haveEstate(19) && !isHotelExist
				&& (super.getPanelNo() == 16 || super.getPanelNo() == 18 || super.getPanelNo() == 19))
		{
			if (estates.get(16).isMortgage() || estates.get(18).isMortgage() || estates.get(19).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}
			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}
			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;
		} else if (haveEstate(21) && haveEstate(23) && haveEstate(24) && !isHotelExist)
		{
			if (estates.get(21).isMortgage() || estates.get(23).isMortgage() || estates.get(24).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}
			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}
			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;
		} else if (haveEstate(26) && haveEstate(27) && haveEstate(29) && !isHotelExist
				&& (super.getPanelNo() == 26 || super.getPanelNo() == 27 || super.getPanelNo() == 29))
		{
			if (estates.get(26).isMortgage() || estates.get(27).isMortgage() || estates.get(29).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}
			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}
			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;
		} else if (haveEstate(31) && haveEstate(32) && haveEstate(34) && !isHotelExist
				&& (super.getPanelNo() == 31 || super.getPanelNo() == 32 || super.getPanelNo() == 34))
		{
			if (estates.get(31).isMortgage() || estates.get(32).isMortgage() || estates.get(34).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}
			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}
			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;
		} else if (haveEstate(37) && haveEstate(39) && !isHotelExist
				&& (super.getPanelNo() == 37 || super.getPanelNo() == 39))
		{

			if (estates.get(37).isMortgage() || estates.get(39).isMortgage())
			{
				JOptionPane.showMessageDialog(null, "You must unmortgage your street first");
				return false;
			}
			if (!houseExistInAllStreets())
			{
				JOptionPane.showMessageDialog(null, "It is not posible");
				return false;
			}
			if (p.getMoney() < housesCost)
			{
				JOptionPane.showMessageDialog(null, "You don't have  enough money");
				return false;
			}

			if (houseCount == 4)
			{
				houseCount = 0;
				isHotelExist = true;
			} else
				houseCount++;

		} else
		{
			JOptionPane.showMessageDialog(null, "It is not posible");
			return true;
		}
		return true;
	}

	private boolean houseExistInAllStreets()
	{
		Street s1 = (Street) EstateDAO.getEstateDAO().getEstates().get(1);
		Street s3 = (Street) EstateDAO.getEstateDAO().getEstates().get(3);
		Street s6 = (Street) EstateDAO.getEstateDAO().getEstates().get(6);
		Street s8 = (Street) EstateDAO.getEstateDAO().getEstates().get(8);
		Street s9 = (Street) EstateDAO.getEstateDAO().getEstates().get(9);
		Street s11 = (Street) EstateDAO.getEstateDAO().getEstates().get(11);
		Street s13 = (Street) EstateDAO.getEstateDAO().getEstates().get(13);
		Street s14 = (Street) EstateDAO.getEstateDAO().getEstates().get(14);
		Street s16 = (Street) EstateDAO.getEstateDAO().getEstates().get(16);
		Street s18 = (Street) EstateDAO.getEstateDAO().getEstates().get(18);
		Street s19 = (Street) EstateDAO.getEstateDAO().getEstates().get(19);
		Street s21 = (Street) EstateDAO.getEstateDAO().getEstates().get(21);
		Street s23 = (Street) EstateDAO.getEstateDAO().getEstates().get(23);
		Street s24 = (Street) EstateDAO.getEstateDAO().getEstates().get(24);
		Street s26 = (Street) EstateDAO.getEstateDAO().getEstates().get(26);
		Street s27 = (Street) EstateDAO.getEstateDAO().getEstates().get(27);
		Street s29 = (Street) EstateDAO.getEstateDAO().getEstates().get(29);
		Street s31 = (Street) EstateDAO.getEstateDAO().getEstates().get(31);
		Street s32 = (Street) EstateDAO.getEstateDAO().getEstates().get(32);
		Street s34 = (Street) EstateDAO.getEstateDAO().getEstates().get(34);
		Street s37 = (Street) EstateDAO.getEstateDAO().getEstates().get(37);
		Street s39 = (Street) EstateDAO.getEstateDAO().getEstates().get(39);

		if (super.getPanelNo() == 1)
		{
			if (s1.getHouseCount() <= s3.getHouseCount() || s3.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 3)
		{
			if (s3.getHouseCount() <= s1.getHouseCount() || s1.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 6)
		{
			if (s6.getHouseCount() <= s8.getHouseCount() || s8.isHotelExist || s9.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 8)
		{
			if (s8.getHouseCount() <= s6.getHouseCount() || s6.isHotelExist || s9.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 9)
		{
			if (s9.getHouseCount() <= s8.getHouseCount() || s6.isHotelExist || s8.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 11)
		{
			if (s11.getHouseCount() <= s13.getHouseCount() || s13.isHotelExist || s14.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 13)
		{
			if (s13.getHouseCount() <= s11.getHouseCount() || s11.isHotelExist || s14.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 14)
		{
			if (s14.getHouseCount() <= s11.getHouseCount() || s11.isHotelExist || s13.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 16)
		{
			if (s16.getHouseCount() <= s18.getHouseCount() || s18.isHotelExist || s19.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 18)
		{
			if (s18.getHouseCount() <= s16.getHouseCount() || s16.isHotelExist || s19.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 19)
		{
			if (s19.getHouseCount() <= s16.getHouseCount() || s16.isHotelExist || s18.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 21)
		{
			if (s21.getHouseCount() <= s23.getHouseCount() || s23.isHotelExist || s24.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 23)
		{
			if (s23.getHouseCount() <= s21.getHouseCount() || s21.isHotelExist || s24.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 24)
		{
			if (s24.getHouseCount() <= s21.getHouseCount() || s21.isHotelExist || s23.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 26)
		{
			if (s26.getHouseCount() <= s27.getHouseCount() || s27.isHotelExist || s29.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 27)
		{
			if (s27.getHouseCount() <= s26.getHouseCount() || s26.isHotelExist || s29.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 29)
		{
			if (s29.getHouseCount() <= s26.getHouseCount() || s26.isHotelExist || s27.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 31)
		{
			if (s31.getHouseCount() <= s32.getHouseCount() || s32.isHotelExist || s34.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 32)
		{
			if (s32.getHouseCount() <= s31.getHouseCount() || s31.isHotelExist || s34.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 34)
		{
			if (s34.getHouseCount() <= s31.getHouseCount() || s31.isHotelExist || s32.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 37)
		{
			if (s37.getHouseCount() <= s39.getHouseCount() || s39.isHotelExist)
				return true;
		} else if (super.getPanelNo() == 39)
		{
			if (s39.getHouseCount() <= s37.getHouseCount() || s37.isHotelExist)
				return true;
		}

		return false;
	}

	public boolean sellHouse()
	{

		Street s1 = (Street) EstateDAO.getEstateDAO().getEstates().get(1);
		Street s3 = (Street) EstateDAO.getEstateDAO().getEstates().get(3);
		Street s6 = (Street) EstateDAO.getEstateDAO().getEstates().get(6);
		Street s8 = (Street) EstateDAO.getEstateDAO().getEstates().get(8);
		Street s9 = (Street) EstateDAO.getEstateDAO().getEstates().get(9);
		Street s11 = (Street) EstateDAO.getEstateDAO().getEstates().get(11);
		Street s13 = (Street) EstateDAO.getEstateDAO().getEstates().get(13);
		Street s14 = (Street) EstateDAO.getEstateDAO().getEstates().get(14);
		Street s16 = (Street) EstateDAO.getEstateDAO().getEstates().get(16);
		Street s18 = (Street) EstateDAO.getEstateDAO().getEstates().get(18);
		Street s19 = (Street) EstateDAO.getEstateDAO().getEstates().get(19);
		Street s21 = (Street) EstateDAO.getEstateDAO().getEstates().get(21);
		Street s23 = (Street) EstateDAO.getEstateDAO().getEstates().get(23);
		Street s24 = (Street) EstateDAO.getEstateDAO().getEstates().get(24);
		Street s26 = (Street) EstateDAO.getEstateDAO().getEstates().get(26);
		Street s27 = (Street) EstateDAO.getEstateDAO().getEstates().get(27);
		Street s29 = (Street) EstateDAO.getEstateDAO().getEstates().get(29);
		Street s31 = (Street) EstateDAO.getEstateDAO().getEstates().get(31);
		Street s32 = (Street) EstateDAO.getEstateDAO().getEstates().get(32);
		Street s34 = (Street) EstateDAO.getEstateDAO().getEstates().get(34);
		Street s37 = (Street) EstateDAO.getEstateDAO().getEstates().get(37);
		Street s39 = (Street) EstateDAO.getEstateDAO().getEstates().get(39);
		if (houseCount == 0 && isHotelExist == false)
		{
			JOptionPane.showMessageDialog(null, "there is no house");
			return false;
		}
		if (super.getPanelNo() == 1)
		{
			if ((isHotelExist() || s1.getHouseCount() >= s3.getHouseCount()) && !s3.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 3)
		{
			if ((isHotelExist() || s3.getHouseCount() >= s1.getHouseCount()) && !s1.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 6)
		{
			if ((isHotelExist()
					|| (s6.getHouseCount() >= s8.getHouseCount() && s6.getHouseCount() >= s9.getHouseCount()))
					&& !s8.isHotelExist && !s9.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 8)
		{
			if ((isHotelExist()
					|| (s8.getHouseCount() >= s6.getHouseCount() && s8.getHouseCount() >= s9.getHouseCount()))
					&& !s6.isHotelExist && !s9.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 9)
		{
			if ((isHotelExist()
					|| (s9.getHouseCount() >= s8.getHouseCount() && s9.getHouseCount() >= s6.getHouseCount()))
					&& !s6.isHotelExist && !s8.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 11)
		{
			if ((isHotelExist()
					|| (s11.getHouseCount() >= s13.getHouseCount() && s11.getHouseCount() >= s14.getHouseCount()))
					&& !s13.isHotelExist && !s14.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 13)
		{
			if ((isHotelExist()
					|| (s13.getHouseCount() >= s11.getHouseCount() && s13.getHouseCount() >= s14.getHouseCount()))
					&& !s11.isHotelExist && !s14.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 14)
		{
			if ((isHotelExist()
					|| (s14.getHouseCount() >= s11.getHouseCount() && s14.getHouseCount() >= s13.getHouseCount()))
					&& !s11.isHotelExist && !s13.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 16)
		{
			if ((isHotelExist()
					|| (s16.getHouseCount() >= s18.getHouseCount() && s16.getHouseCount() >= s19.getHouseCount()))
					&& !s18.isHotelExist && !s19.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 18)
		{
			if ((isHotelExist()
					|| (s18.getHouseCount() >= s16.getHouseCount() && s18.getHouseCount() >= s19.getHouseCount()))
					&& !s16.isHotelExist && !s19.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 19)
		{
			if ((isHotelExist()
					|| (s19.getHouseCount() >= s16.getHouseCount() && s19.getHouseCount() >= s18.getHouseCount()))
					&& !s16.isHotelExist && !s18.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 21)
		{
			if ((isHotelExist()
					|| (s21.getHouseCount() >= s23.getHouseCount() && s21.getHouseCount() >= s24.getHouseCount()))
					&& !s23.isHotelExist && !s24.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 23)
		{
			if ((isHotelExist()
					|| (s23.getHouseCount() >= s21.getHouseCount() && s23.getHouseCount() >= s24.getHouseCount()))
					&& !s21.isHotelExist && !s24.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 24)
		{
			if ((isHotelExist()
					|| (s24.getHouseCount() >= s21.getHouseCount() && s24.getHouseCount() >= s23.getHouseCount()))
					&& !s21.isHotelExist && !s23.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 26)
		{
			if ((isHotelExist()
					|| (s26.getHouseCount() >= s27.getHouseCount() && s26.getHouseCount() >= s29.getHouseCount()))
					&& !s27.isHotelExist && !s29.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 27)
		{
			if ((isHotelExist()
					|| (s27.getHouseCount() >= s26.getHouseCount() && s27.getHouseCount() >= s29.getHouseCount()))
					&& !s29.isHotelExist && !s26.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 29)
		{
			if ((isHotelExist()
					|| (s29.getHouseCount() >= s26.getHouseCount() && s29.getHouseCount() >= s27.getHouseCount()))
					&& !s26.isHotelExist && !s27.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 31)
		{
			if ((isHotelExist()
					|| (s31.getHouseCount() >= s32.getHouseCount() && s31.getHouseCount() >= s34.getHouseCount()))
					&& !s32.isHotelExist && !s34.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 32)
		{
			if ((isHotelExist()
					|| (s32.getHouseCount() >= s31.getHouseCount() && s32.getHouseCount() >= s34.getHouseCount()))
					&& !s31.isHotelExist && !s34.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 34)
		{
			if ((isHotelExist()
					|| (s34.getHouseCount() >= s31.getHouseCount() && s34.getHouseCount() >= s32.getHouseCount()))
					&& !s31.isHotelExist && !s32.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 37)
		{
			if ((isHotelExist() || s37.getHouseCount() >= s39.getHouseCount()) && !s39.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		} else if (super.getPanelNo() == 39)
		{
			if ((isHotelExist() || s39.getHouseCount() >= s37.getHouseCount()) && !s37.isHotelExist)
			{
				if (isHotelExist())
				{
					isHotelExist = false;
					houseCount = 4;
				} else
					houseCount--;
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "impossible request");
		return false;
	}

	public boolean mortgage()
	{
		Street s1 = (Street) EstateDAO.getEstateDAO().getEstates().get(1);
		Street s3 = (Street) EstateDAO.getEstateDAO().getEstates().get(3);
		Street s6 = (Street) EstateDAO.getEstateDAO().getEstates().get(6);
		Street s8 = (Street) EstateDAO.getEstateDAO().getEstates().get(8);
		Street s9 = (Street) EstateDAO.getEstateDAO().getEstates().get(9);
		Street s11 = (Street) EstateDAO.getEstateDAO().getEstates().get(11);
		Street s13 = (Street) EstateDAO.getEstateDAO().getEstates().get(13);
		Street s14 = (Street) EstateDAO.getEstateDAO().getEstates().get(14);
		Street s16 = (Street) EstateDAO.getEstateDAO().getEstates().get(16);
		Street s18 = (Street) EstateDAO.getEstateDAO().getEstates().get(18);
		Street s19 = (Street) EstateDAO.getEstateDAO().getEstates().get(19);
		Street s21 = (Street) EstateDAO.getEstateDAO().getEstates().get(21);
		Street s23 = (Street) EstateDAO.getEstateDAO().getEstates().get(23);
		Street s24 = (Street) EstateDAO.getEstateDAO().getEstates().get(24);
		Street s26 = (Street) EstateDAO.getEstateDAO().getEstates().get(26);
		Street s27 = (Street) EstateDAO.getEstateDAO().getEstates().get(27);
		Street s29 = (Street) EstateDAO.getEstateDAO().getEstates().get(29);
		Street s31 = (Street) EstateDAO.getEstateDAO().getEstates().get(31);
		Street s32 = (Street) EstateDAO.getEstateDAO().getEstates().get(32);
		Street s34 = (Street) EstateDAO.getEstateDAO().getEstates().get(34);
		Street s37 = (Street) EstateDAO.getEstateDAO().getEstates().get(37);
		Street s39 = (Street) EstateDAO.getEstateDAO().getEstates().get(39);
		if (super.isMortgage())
		{
			JOptionPane.showMessageDialog(null, "Impossible request");
			return false;
		}
		if ((super.getPanelNo() == 1 || super.getPanelNo() == 3)
				&& (s1.getHouseCount() != 0 || s3.getHouseCount() != 0 || s1.isHotelExist || s3.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		} else if ((super.getPanelNo() == 6 || super.getPanelNo() == 8 || super.getPanelNo() == 9)
				&& (s6.getHouseCount() != 0 || s8.getHouseCount() != 0 || s9.getHouseCount() != 0 || s6.isHotelExist
						|| s8.isHotelExist || s9.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		} else if ((super.getPanelNo() == 11 || super.getPanelNo() == 13 || super.getPanelNo() == 14)
				&& (s11.getHouseCount() != 0 || s13.getHouseCount() != 0 || s14.getHouseCount() != 0 || s11.isHotelExist
						|| s13.isHotelExist || s14.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		} else if ((super.getPanelNo() == 16 || super.getPanelNo() == 18 || super.getPanelNo() == 19)
				&& (s16.getHouseCount() != 0 || s18.getHouseCount() != 0 || s19.getHouseCount() != 0 || s16.isHotelExist
						|| s18.isHotelExist || s19.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		} else if ((super.getPanelNo() == 21 || super.getPanelNo() == 23 || super.getPanelNo() == 24)
				&& (s21.getHouseCount() != 0 || s23.getHouseCount() != 0 || s24.getHouseCount() != 0 || s21.isHotelExist
						|| s23.isHotelExist || s24.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		} else if ((super.getPanelNo() == 26 || super.getPanelNo() == 27 || super.getPanelNo() == 29)
				&& (s26.getHouseCount() != 0 || s27.getHouseCount() != 0 || s29.getHouseCount() != 0 || s26.isHotelExist
						|| s27.isHotelExist || s29.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		} else if ((super.getPanelNo() == 31 || super.getPanelNo() == 32 || super.getPanelNo() == 34)
				&& (s31.getHouseCount() != 0 || s32.getHouseCount() != 0 || s34.getHouseCount() != 0 || s31.isHotelExist
						|| s32.isHotelExist || s34.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		} else if ((super.getPanelNo() == 37 || super.getPanelNo() == 39)
				&& (s37.getHouseCount() != 0 || s39.getHouseCount() != 0 || s37.isHotelExist || s39.isHotelExist))
		{
			JOptionPane.showMessageDialog(null, "First you have to sell your houses or hotels");
			return false;
		}
		super.setMortgage(true);
		return true;
	}

	public boolean unMortgage()
	{
		HashMap<Integer, Estate> estates = EstateDAO.getEstateDAO().getEstates();
		Person p = PersonDAO.getPersonDAO().getThePerson();
		if (!estates.get(super.getPanelNo()).isMortgage())
		{
			JOptionPane.showMessageDialog(null, "Not mortgaged");
			return false;
		}
		if (p.getMoney() < estates.get(super.getPanelNo()).getUnMortgage())
		{
			JOptionPane.showMessageDialog(null, "You don't have enough money");
			return false;
		}
		super.setMortgage(false);
		return true;
	}

}