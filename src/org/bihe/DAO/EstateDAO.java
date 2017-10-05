package org.bihe.DAO;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bihe.model.Estate;
import org.bihe.model.RailRoad;
import org.bihe.model.Street;
import org.bihe.model.Utility;

public class EstateDAO
{
	private HashMap<Integer, Estate> estates;
	private static EstateDAO instance;

	private EstateDAO()
	{
		estates = new HashMap<>();
		putStreets();
		putRailRoads();
		putUtilities();
	}

	public static EstateDAO getEstateDAO()
	{
		if (instance == null)
		{
			instance = new EstateDAO();
		}
		return instance;
	}

	public void changeEstate(Estate estate)
	{
		estates.replace(estate.getPanelNo(), estate);
	}

	public void changeEstateDAO(HashMap<Integer, Estate> estates)
	{
		this.estates = estates;
	}

	public HashMap<Integer, Estate> getEstates()
	{
		return this.estates;
	}

	public Estate getOneEstate(int panelNo)
	{
		for (Entry<Integer, Estate> E : estates.entrySet())
		{
			if (E.getKey().equals(panelNo))
				return E.getValue();
		}
		return null;
	}

	public Estate getOneEstateByName(String stateName)
	{
		for (Entry<Integer, Estate> E : estates.entrySet())
		{
			if (E.getValue().getName().equals(stateName))
				return E.getValue();
		}
		return null;
	}

	private void putStreets()
	{
		Estate str1 = new Street("MediterraneanAvenue", 1, 60, 2, 30, 33, 4, 10, 30, 90, 160, 250, 50, 50);

		Estate str2 = new Street("BalticAvenue", 3, 60, 4, 30, 33, 8, 20, 60, 180, 320, 450, 50, 50);
		//
		Estate str3 = new Street("OrientalAvenue", 6, 100, 6, 50, 55, 12, 30, 90, 270, 400, 550, 50, 50);
		Estate str4 = new Street("VermontAvenue", 8, 100, 6, 50, 55, 12, 30, 90, 270, 400, 550, 50, 50);
		Estate str5 = new Street("ConnecticutAvenue", 9, 120, 8, 60, 66, 16, 40, 100, 300, 450, 600, 50, 50);
		//
		Estate str6 = new Street("St.CharlesPlace", 11, 140, 10, 70, 77, 20, 50, 150, 450, 625, 750, 100, 100);
		Estate str7 = new Street("StatesAvenue", 13, 140, 10, 70, 77, 20, 50, 150, 450, 625, 750, 100, 100);
		Estate str8 = new Street("VirginiaAvenue", 14, 160, 12, 80, 88, 24, 60, 180, 500, 700, 900, 100, 100);
		//
		Estate str9 = new Street("St.JamesPlace", 16, 180, 14, 90, 99, 28, 70, 200, 550, 750, 950, 100, 100);
		Estate str10 = new Street("TennesseeAvenue", 18, 180, 14, 90, 99, 28, 70, 200, 550, 750, 950, 100, 100);
		Estate str11 = new Street("NewyorkAvenue", 19, 200, 16, 100, 110, 32, 80, 220, 600, 800, 1000, 100, 100);
		//
		Estate str12 = new Street("KentuckyAvenue", 21, 220, 18, 110, 121, 36, 90, 250, 700, 875, 1050, 150, 150);
		Estate str13 = new Street("IndiniaAvenue", 23, 220, 18, 110, 121, 36, 90, 250, 700, 875, 1050, 150, 150);
		Estate str14 = new Street("IllinoisAvenue", 24, 240, 20, 120, 132, 40, 100, 300, 750, 925, 1100, 150, 150);
		//
		Estate str15 = new Street("AtlanticAvenue", 26, 260, 22, 130, 143, 44, 110, 330, 800, 975, 1150, 150, 150);
		Estate str16 = new Street("VentnorAvenue", 27, 260, 22, 130, 143, 44, 110, 330, 800, 975, 1150, 150, 150);
		Estate str17 = new Street("MarvinAvenue", 29, 280, 24, 140, 154, 48, 120, 360, 850, 1025, 1200, 150, 150);
		//
		Estate str18 = new Street("PacificAvenue", 31, 300, 26, 150, 165, 52, 130, 390, 900, 1100, 1275, 200, 200);
		Estate str19 = new Street("NorthCaliforniaAvenue", 32, 300, 26, 150, 165, 52, 130, 390, 900, 1100, 1275, 200,
				200);
		Estate str20 = new Street("PennsylvaniaAvenue", 34, 320, 28, 160, 176, 56, 150, 450, 1000, 1200, 1400, 200,
				200);
		//
		Estate str21 = new Street("ParkPlace", 37, 350, 35, 175, 193, 70, 175, 500, 1100, 1300, 1500, 200, 200);
		Estate str22 = new Street("BoardWalk", 39, 400, 50, 200, 220, 100, 200, 600, 1400, 1700, 2000, 200, 200);
		//

		estates.put(1, str1);
		estates.put(3, str2);
		estates.put(6, str3);
		estates.put(8, str4);
		estates.put(9, str5);
		estates.put(11, str6);
		estates.put(13, str7);
		estates.put(14, str8);
		estates.put(16, str9);
		estates.put(18, str10);
		estates.put(19, str11);
		estates.put(21, str12);
		estates.put(23, str13);
		estates.put(24, str14);
		estates.put(26, str15);
		estates.put(27, str16);
		estates.put(29, str17);
		estates.put(31, str18);
		estates.put(32, str19);
		estates.put(34, str20);
		estates.put(37, str21);
		estates.put(39, str22);

	}

	private void putUtilities()
	{
		Estate utl1 = new Utility("ElectricCompany", 12, 150, 4, 75, 83, 10);
		Estate utl2 = new Utility("WaterWork", 28, 150, 4, 75, 83, 10);
		//
		estates.put(12, utl1);
		estates.put(28, utl2);
	}

	private void putRailRoads()
	{
		Estate rr1 = new RailRoad("ReadingRailroad", 5, 200, 25, 100, 110, 50, 100, 200);
		Estate rr2 = new RailRoad("PennsylvaniaRailroad", 15, 200, 25, 100, 110, 50, 100, 200);
		Estate rr3 = new RailRoad("B&ORailroad", 25, 200, 25, 100, 110, 50, 100, 200);
		Estate rr4 = new RailRoad("ShortLine", 35, 200, 25, 100, 110, 50, 100, 200);
		//
		estates.put(5, rr1);
		estates.put(15, rr2);
		estates.put(25, rr3);
		estates.put(35, rr4);
	}
}