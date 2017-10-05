package org.bihe.ui;

import java.awt.BorderLayout;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel[] panels = new JPanel[41];
	private StreetPanel[] streets = new StreetPanel[41];
	private HashMap<Integer, Piece> pieces = new HashMap<>();
	private Piece tempPiece;
	private JPanel north = new JPanel();
	private JPanel south = new JPanel();
	private JPanel west = new JPanel();
	private JPanel east = new JPanel();
	private JPanel center = new JPanel();
	private JPanel northWest = new JPanel();
	private JPanel northEast = new JPanel();
	private JPanel northCenter = new JPanel();
	private JPanel southWest = new JPanel();
	private JPanel southEast = new JPanel();
	private JPanel southCenter = new JPanel();
	private int panelSize = (GameFrame.getHEIGHT() * 190) / 1500;

	public GamePanel()
	{

		for (int i = 0; i < 41; i++)
		{
			panels[i] = new JPanel();
		}

		makeBorderLayout();
		addPanelsToNorth();
		addPanelsToSouth();
		addpanelsToWest();
		addPanelsToEast();

		repaint();
		revalidate();

	}

	public void repaintStreets()
	{
		for (int i = 0; i < 41; i++)
		{
			streets[i].repaint();
		}
	}

	/**
	 * Add the images of the game to the panels
	 */
	public void addImageToPanels()
	{
		String imageName;
		for (int i = 0; i < 40; i++)
		{
			imageName = "panelNo" + i + ".png";
			Image image = resources.Resources.getImage(imageName);
			image = image.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
			if (i == 1 || i == 3 || i == 6 || i == 8 || i == 9)
			{
				Image house1 = resources.Resources.getImage("south_house1.png");
				house1 = house1.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house2 = resources.Resources.getImage("south_house2.png");
				house2 = house2.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house3 = resources.Resources.getImage("south_house3.png");
				house3 = house3.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house4 = resources.Resources.getImage("south_house4.png");
				house4 = house4.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image hotel = resources.Resources.getImage("south_hotel.png");
				hotel = hotel.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);

				streets[i] = new StreetPanel(i, image, house1, house2, house3, house4, hotel);
			} else if (i == 11 || i == 13 || i == 14 || i == 16 || i == 18 || i == 19)
			{
				Image house1 = resources.Resources.getImage("west_house1.png");
				house1 = house1.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house2 = resources.Resources.getImage("west_house2.png");
				house2 = house2.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house3 = resources.Resources.getImage("west_house3.png");
				house3 = house3.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house4 = resources.Resources.getImage("west_house4.png");
				house4 = house4.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image hotel = resources.Resources.getImage("west_hotel.png");
				hotel = hotel.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);

				streets[i] = new StreetPanel(i, image, house1, house2, house3, house4, hotel);
			}

			else if (i == 21 || i == 23 || i == 24 || i == 26 || i == 27 || i == 29)
			{
				Image house1 = resources.Resources.getImage("north_house1.png");
				house1 = house1.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house2 = resources.Resources.getImage("north_house2.png");
				house2 = house2.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house3 = resources.Resources.getImage("north_house3.png");
				house3 = house3.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house4 = resources.Resources.getImage("north_house4.png");
				house4 = house4.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image hotel = resources.Resources.getImage("north_hotel.png");
				hotel = hotel.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);

				streets[i] = new StreetPanel(i, image, house1, house2, house3, house4, hotel);
			}

			else if (i == 31 || i == 32 || i == 34 || i == 37 || i == 39)
			{
				Image house1 = resources.Resources.getImage("east_house1.png");
				house1 = house1.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house2 = resources.Resources.getImage("east_house2.png");
				house2 = house2.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house3 = resources.Resources.getImage("east_house3.png");
				house3 = house3.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image house4 = resources.Resources.getImage("east_house4.png");
				house4 = house4.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);
				Image hotel = resources.Resources.getImage("east_hotel.png");
				hotel = hotel.getScaledInstance(panels[i].getWidth(), panels[i].getHeight(), Image.SCALE_SMOOTH);

				streets[i] = new StreetPanel(i, image, house1, house2, house3, house4, hotel);
			}

			else
			{
				streets[i] = new StreetPanel(i, image);
			}
			panels[i].setLayout(new BorderLayout());
			panels[i].add(streets[i], BorderLayout.CENTER);
		}
		revalidate();
		repaint();

	}

	/**
	 * 
	 */
	private void makeBorderLayout()
	{

		this.setLayout(new BorderLayout());
		north.setPreferredSize(new Dimension(GameFrame.getHEIGHT(), (GameFrame.getHEIGHT() * 190) / 1500));
		west.setPreferredSize(new Dimension((GameFrame.getHEIGHT() * 190) / 1500, 1000));
		south.setPreferredSize(new Dimension(GameFrame.getHEIGHT(), (GameFrame.getHEIGHT() * 190) / 1500));
		east.setPreferredSize(new Dimension((GameFrame.getHEIGHT() * 190) / 1500, 1000));

		this.add(north, BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);
		this.add(west, BorderLayout.WEST);
		this.add(east, BorderLayout.EAST);
		this.add(center, BorderLayout.CENTER);
	}

	/**
	 * add panels to north
	 */
	private void addPanelsToNorth()
	{
		north.setLayout(new BorderLayout());
		northWest.setPreferredSize(new Dimension(panelSize, panelSize));
		northEast.setPreferredSize(new Dimension(panelSize, panelSize));
		northCenter.setLayout(new GridLayout());
		for (int i = 21; i < 30; i++)
		{
			northCenter.add(panels[i]);
		}
		northEast.setLayout(new BorderLayout());
		northWest.setLayout(new BorderLayout());
		northEast.add(panels[30], BorderLayout.CENTER);
		northWest.add(panels[20], BorderLayout.CENTER);
		north.add(northWest, BorderLayout.WEST);
		north.add(northEast, BorderLayout.EAST);
		north.add(northCenter, BorderLayout.CENTER);

	}

	/**
	 * add panels to south
	 */
	private void addPanelsToSouth()
	{
		south.setLayout(new BorderLayout());
		southWest.setPreferredSize(new Dimension(panelSize, panelSize));
		southEast.setPreferredSize(new Dimension(panelSize, panelSize));
		southCenter.setLayout(new GridLayout());
		for (int i = 9; i > 0; i--)
		{
			southCenter.add(panels[i]);
		}
		southEast.setLayout(new BorderLayout());
		southWest.setLayout(new BorderLayout());
		southEast.add(panels[0], BorderLayout.CENTER);
		southWest.add(panels[10], BorderLayout.CENTER);
		south.add(southWest, BorderLayout.WEST);
		south.add(southEast, BorderLayout.EAST);
		south.add(southCenter, BorderLayout.CENTER);
	}

	/**
	 * add panels to west
	 */
	private void addpanelsToWest()
	{
		west.setLayout(new GridLayout(9, 1));
		for (int i = 19; i > 10; i--)
		{
			west.add(panels[i]);
		}

	}

	/**
	 * add panels to east
	 */
	private void addPanelsToEast()
	{
		east.setLayout(new GridLayout(9, 1));
		for (int i = 31; i < 40; i++)
		{
			east.add(panels[i]);
		}

	}

	public Piece makePiece(int pieceNumber, int panelNumber)
	{
		String imageName;
		Image image;
		if (panelNumber < 11)
		{
			imageName = "south_pieceNo" + pieceNumber + ".png";

			if (panelNumber == 0 || panelNumber == 10)
			{
				imageName = "south_pieceNo" + pieceNumber + "`.png";
			}
			image = resources.Resources.getImage(imageName);
			image = image.getScaledInstance(panels[panelNumber].getWidth(), panels[panelNumber].getHeight(),
					Image.SCALE_SMOOTH);

		} else if (panelNumber < 21)
		{
			imageName = "west_pieceNo" + pieceNumber + ".png";
			if (panelNumber == 20)
			{
				imageName = "west_pieceNo" + pieceNumber + "`.png";
			}
			image = resources.Resources.getImage(imageName);
			image = image.getScaledInstance(panels[panelNumber].getWidth(), panels[panelNumber].getHeight(),
					Image.SCALE_SMOOTH);

		} else if (panelNumber < 31)
		{
			imageName = "north_pieceNo" + pieceNumber + ".png";
			if (panelNumber == 30)
			{
				imageName = "north_pieceNo" + pieceNumber + "`.png";
			}

			image = resources.Resources.getImage(imageName);
			image = image.getScaledInstance(panels[panelNumber].getWidth(), panels[panelNumber].getHeight(),
					Image.SCALE_SMOOTH);
		} else
		{

			imageName = "east_pieceNo" + pieceNumber + ".png";
			image = resources.Resources.getImage(imageName);
			image = image.getScaledInstance(panels[panelNumber].getWidth(), panels[panelNumber].getHeight(),
					Image.SCALE_SMOOTH);
		}

		Piece piece = new Piece(image, pieceNumber, panelNumber, true);
		streets[panelNumber].addPiece(piece);
		pieces.put(piece.getPieceNumber(), piece);
		return piece;

	}

	public void addAllPieces(HashMap<Integer, Piece> pieces)
	{
		for (int i = 1; i < 7; i++)
		{
			if (pieces.containsKey(i))
			{
				streets[pieces.get(i).getPanelNumber()].addPiece(pieces.get(i));
				repaint();
			}
		}
		streets[tempPiece.getPanelNumber()].addPiece(tempPiece);
	}

	public void movePieceOnePlace(int pieceNumber, int count)
	{
		Piece piece = pieces.get(pieceNumber);
		pieces.put(piece.getPieceNumber(), piece);
		if (piece.getPanelNumber() != 39)
		{
			tempPiece = makePiece(piece.getPieceNumber(), piece.getPanelNumber() + 1);

		} else
		{
			tempPiece = makePiece(piece.getPieceNumber(), 0);
		}
		tempPiece.setMove(false);

		if (piece.getPanelNumber() < 10)
		{
			tempPiece.setX(panels[tempPiece.getPanelNumber()].getWidth());

		} else if (piece.getPanelNumber() == 10)
		{
			if (!piece.isMove())
				tempPiece.setY(panels[tempPiece.getPanelNumber()].getHeight());
			else
				tempPiece.setX(panels[tempPiece.getPanelNumber()].getWidth());
		} else if (piece.getPanelNumber() < 20)
		{
			tempPiece.setY(panels[tempPiece.getPanelNumber()].getHeight());
		} else if (piece.getPanelNumber() == 20)
		{
			if (!piece.isMove())
				tempPiece.setX(-1 * panels[tempPiece.getPanelNumber()].getWidth());
			else
				tempPiece.setY(panels[tempPiece.getPanelNumber()].getHeight());
		} else if (piece.getPanelNumber() < 30)
		{
			tempPiece.setX(-1 * panels[tempPiece.getPanelNumber()].getWidth());
		} else if (piece.getPanelNumber() == 30)
		{
			if (!piece.isMove())
				tempPiece.setY(-1 * panels[tempPiece.getPanelNumber()].getHeight());
			else
				tempPiece.setX(-1 * panels[tempPiece.getPanelNumber()].getWidth());
		} else
		{
			tempPiece.setY(-1 * panels[tempPiece.getPanelNumber()].getHeight());
		}

		addAllPieces(pieces);
		piece.setMove(true);

		PieceWorker p1 = new PieceWorker(streets[piece.getPanelNumber()], piece, count, false);
		PieceWorker p2 = new PieceWorker(streets[tempPiece.getPanelNumber()], tempPiece, count, true);
		p1.execute();
		p2.execute();

	}

	public Dimension getPanelSize(int n)
	{
		Dimension dm = new Dimension(panels[n].getWidth(), panels[n].getHeight());
		return dm;

	}

	public int distance(int location, int newLocation)
	{
		int distance;
		if (newLocation >= location)
			distance = newLocation - location;
		else
			distance = 40 - location + newLocation;

		return distance;
	}

}