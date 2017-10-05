package org.bihe.ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import resources.Resources;

public class PieceWorker extends SwingWorker<Boolean, Object>
{
	boolean temp;
	private int count;
	private int delay = 1;
	private Component component;
	private Piece piece;
	private GamePanel gamePanel = GUIManager.getGamePanel();

	public PieceWorker(Component component, Piece piece, int count, boolean temp)
	{
		this.component = component;
		this.piece = piece;
		this.setCount(count);
		this.temp = temp;
	}

	@Override
	protected Boolean doInBackground()
	{

		if (piece.getPanelNumber() == 0)
			panelNo0Move();
		else if (piece.getPanelNumber() < 10)
			southMove();
		else if (piece.getPanelNumber() == 10)
			panelNo10Move();
		else if (piece.getPanelNumber() < 20)
			eastMove();
		else if (piece.getPanelNumber() == 20)
			panelNo20Move();
		else if (piece.getPanelNumber() < 30)
			northMove();
		else if (piece.getPanelNumber() == 30)
			panelNo30Move();

		else
			westMove();

		return true;
	}

	@Override
	protected void done()
	{

		super.done();
		if (count - 1 > 0 && temp)
		{
			GUIManager.getGamePanel().movePieceOnePlace(piece.getPieceNumber(), count - 1);
		}
	}

	private void southMove()
	{
		try
		{
			if (piece.isMove())
			{

				while (component.getWidth() > -1 * piece.getX())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{

				while (piece.getX() > 0)
				{
					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void panelNo10Move()
	{
		try
		{
			if (piece.isMove())
			{
				String imageName = "west_pieceNo" + piece.getPieceNumber() + "`.png";
				Image image = Resources.getImage(imageName);
				image = image.getScaledInstance(gamePanel.getPanelSize(piece.getPanelNumber()).width,
						gamePanel.getPanelSize(piece.getPanelNumber()).height, Image.SCALE_SMOOTH);
				piece.setImage(image);
				while (component.getHeight() > -1 * piece.getY())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{

				while (piece.getX() > 0)
				{
					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void panelNo20Move()
	{
		try
		{
			if (piece.isMove())
			{
				String imageName = "north_pieceNo" + piece.getPieceNumber() + "`.png";
				Image image = Resources.getImage(imageName);
				image = image.getScaledInstance(gamePanel.getPanelSize(piece.getPanelNumber()).width,
						gamePanel.getPanelSize(piece.getPanelNumber()).height, Image.SCALE_SMOOTH);
				piece.setImage(image);
				while (component.getWidth() > piece.getX())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{

				while (piece.getY() > 0)
				{
					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void panelNo30Move()
	{
		try
		{
			if (piece.isMove())
			{
				String imageName = "east_pieceNo" + piece.getPieceNumber() + "`.png";
				Image image = Resources.getImage(imageName);
				image = image.getScaledInstance(gamePanel.getPanelSize(piece.getPanelNumber()).width,
						gamePanel.getPanelSize(piece.getPanelNumber()).height, Image.SCALE_SMOOTH);
				piece.setImage(image);
				while (component.getHeight() > piece.getY())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{

				while (piece.getX() < 0)
				{
					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void panelNo0Move()
	{
		try
		{
			if (piece.isMove())
			{
				String imageName = "south_pieceNo" + piece.getPieceNumber() + "`.png";
				Image image = Resources.getImage(imageName);
				image = image.getScaledInstance(gamePanel.getPanelSize(piece.getPanelNumber()).width,
						gamePanel.getPanelSize(piece.getPanelNumber()).height, Image.SCALE_SMOOTH);
				piece.setImage(image);
				while (component.getWidth() > -1 * piece.getX())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{
				String imageName = "east_pieceNo" + piece.getPieceNumber() + "`.png";
				Image image = Resources.getImage(imageName);
				image = image.getScaledInstance(gamePanel.getPanelSize(piece.getPanelNumber()).width,
						gamePanel.getPanelSize(piece.getPanelNumber()).height, Image.SCALE_SMOOTH);
				piece.setImage(image);
				while (piece.getY() < 0)
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void eastMove()
	{
		try
		{
			if (piece.isMove())
			{
				while (component.getHeight() > -1 * piece.getY())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{
				while (piece.getY() > 0)
				{
					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void northMove()
	{
		try
		{
			if (piece.isMove())
			{
				while (component.getWidth() > piece.getX())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{
				while (piece.getX() < 0)
				{
					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void westMove()
	{
		try
		{
			if (piece.isMove())
			{
				while (component.getHeight() > piece.getY())
				{

					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();

					Thread.sleep(delay);
				}
			} else
			{
				while (piece.getY() < 0)
				{
					piece.movePiece();
					component.repaint();
					Toolkit.getDefaultToolkit().sync();
					Thread.sleep(delay);
				}

			}
		} catch (InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Piece getPiece()
	{
		return piece;
	}

	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

}
