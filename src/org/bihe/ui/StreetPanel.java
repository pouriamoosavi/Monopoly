package org.bihe.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import org.bihe.DAO.EstateDAO;
import org.bihe.model.Estate;
import org.bihe.model.Street;

public class StreetPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4172903723945892154L;
	private int panelNo;
	private Image backGround;
	private Image house1;
	private Image house2;
	private Image house3;
	private Image house4;
	private Image hotel;
	private ArrayList<Piece> pieces = new ArrayList<>();

	private HashMap<Integer, Estate> estates;

	public StreetPanel()
	{

	}

	public StreetPanel(int number, Image image)
	{
		estates = EstateDAO.getEstateDAO().getEstates();
		this.setNumber(number);
		this.backGround = image;
		repaint();
	}

	public StreetPanel(int panelNo, Image backGround, Image house1, Image house2, Image house3, Image house4,
			Image hotel)
	{
		estates = EstateDAO.getEstateDAO().getEstates();
		this.panelNo = panelNo;
		this.backGround = backGround;
		this.house1 = house1;
		this.house2 = house2;
		this.house3 = house3;
		this.house4 = house4;
		this.hotel = hotel;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		estates = EstateDAO.getEstateDAO().getEstates();
		g.drawImage(backGround, 0, 0, this);
		for (int i = 0; i < pieces.size(); i++)
		{
			g.drawImage(pieces.get(i).getImage(), pieces.get(i).getX(), pieces.get(i).getY(), this);
		}

		if (panelNo == 1 || panelNo == 3 || panelNo == 6 || panelNo == 8 || panelNo == 9)
		{
			Street s = (Street) estates.get(panelNo);
			if (s.isHotelExist())
				g.drawImage(hotel, 0, 0, this);
			else
			{
				if (s.getHouseCount() == 1)
				{
					g.drawImage(house1, 0, 0, this);
				} else if (s.getHouseCount() == 2)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
				} else if (s.getHouseCount() == 3)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
				} else if (s.getHouseCount() == 4)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
					g.drawImage(house4, 0, 0, this);
				}
			}

		} else if (panelNo == 11 || panelNo == 13 || panelNo == 14 || panelNo == 16 || panelNo == 18 || panelNo == 19)
		{
			Street s = (Street) estates.get(panelNo);
			if (s.isHotelExist())
				g.drawImage(hotel, 0, 0, this);
			else
			{
				if (s.getHouseCount() == 1)
				{
					g.drawImage(house1, 0, 0, this);
				} else if (s.getHouseCount() == 2)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
				} else if (s.getHouseCount() == 3)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
				} else if (s.getHouseCount() == 4)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
					g.drawImage(house4, 0, 0, this);
				}
			}

		} else if (panelNo == 21 || panelNo == 23 || panelNo == 24 || panelNo == 26 || panelNo == 27 || panelNo == 29)
		{
			Street s = (Street) estates.get(panelNo);
			if (s.isHotelExist())
				g.drawImage(hotel, 0, 0, this);
			else
			{
				if (s.getHouseCount() == 1)
				{
					g.drawImage(house1, 0, 0, this);
				} else if (s.getHouseCount() == 2)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
				} else if (s.getHouseCount() == 3)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
				} else if (s.getHouseCount() == 4)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
					g.drawImage(house4, 0, 0, this);
				}
			}

		} else if (panelNo == 31 || panelNo == 32 || panelNo == 34 || panelNo == 37 || panelNo == 39)
		{
			Street s = (Street) estates.get(panelNo);
			if (s.isHotelExist())
				g.drawImage(hotel, 0, 0, this);
			else
			{
				if (s.getHouseCount() == 1)
				{
					g.drawImage(house1, 0, 0, this);
				} else if (s.getHouseCount() == 2)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
				} else if (s.getHouseCount() == 3)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
				} else if (s.getHouseCount() == 4)
				{
					g.drawImage(house1, 0, 0, this);
					g.drawImage(house2, 0, 0, this);
					g.drawImage(house3, 0, 0, this);
					g.drawImage(house4, 0, 0, this);
				}
			}

		}
	}

	public void addPiece(Piece piece)
	{
		this.pieces.add(piece);
	}

	public int getNumber()
	{
		return panelNo;
	}

	public void setNumber(int number)
	{
		this.panelNo = number;
	}

}
