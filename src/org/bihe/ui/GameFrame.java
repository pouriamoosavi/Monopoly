package org.bihe.ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bihe.network.client.Client;

import java.awt.Toolkit;

public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height - 31;
	private int x = WIDTH * 10 / 46;

	public GameFrame()
	{
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Online Monopoly!");
		this.setIconImage(resources.Resources.getImage("MrMonopoly.gif"));
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.WHITE);
		this.setBounds(x / 2, 0, HEIGHT + x, HEIGHT);
		this.setMinimumSize(new Dimension(HEIGHT + x, HEIGHT));

		this.add(GUIManager.getActionPanel());
		GamePanel gamePanel = GUIManager.getGamePanel();
		if (System.getProperty("os.name").toLowerCase().equals("linux"))
		{
			gamePanel.setBounds(x, 0, HEIGHT - 2, HEIGHT - 2);
		} else
		{
			gamePanel.setBounds(x, 0, HEIGHT - 20, HEIGHT - 38);
		}
		this.pack();
		this.add(gamePanel);
		this.pack();
		this.setVisible(true);
		gamePanel.addImageToPanels();
		
		for (int i = 1; i < Client.getClient().getNumberOfPlayers()+1; i++)
		{
			gamePanel.makePiece(i, 0);
		
		}
		repaint();
		Runnable r = new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					Thread.sleep(10);
					GUIManager.getPlayerPanel().fillPlayersList();
				} catch (InterruptedException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		};
		new Thread(r).start();
	}

	public static int getWIDTH()
	{
		return WIDTH;
	}

	public static int getHEIGHT()
	{
		return HEIGHT;
	}

	public void addComponent(JPanel p)
	{
	}

}