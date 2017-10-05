package org.bihe.ui;

import java.awt.Image;

public class Piece
{
	private Image image;
	private int x = 0;
	private int y = 0;
	private int pieceNumber;
	private int panelNumber;
	private boolean move;

	public Piece(Image image, int pieceNumber, int panelNumber, boolean move)
	{

		this.image = image;
		this.pieceNumber = pieceNumber;
		this.panelNumber = panelNumber;
		this.move = move;
	}

	public void movePiece()
	{
		if (panelNumber < 11)
		{
			if (panelNumber == 10 && move)
				y--;
			else if (panelNumber == 0 && !move)
				y++;
			else
				x--;

		} else if (panelNumber < 21)
			if (panelNumber == 20 && move)
				x++;
			else
				y--;
		else if (panelNumber < 31)
			if (panelNumber == 30 && move)
				y++;
			else
				x++;
		else
			y++;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setImage(Image image)
	{
		this.image = image;
	}

	public Image getImage()
	{
		return image;
	}

	public int getPieceNumber()
	{
		return pieceNumber;
	}

	public int getPanelNumber()
	{
		return panelNumber;
	}

	public void setPanelNumber(int panelNumber)
	{
		this.panelNumber = panelNumber;
	}

	public boolean isMove()
	{
		return move;
	}

	public void setMove(boolean move)
	{
		this.move = move;
	}

}
