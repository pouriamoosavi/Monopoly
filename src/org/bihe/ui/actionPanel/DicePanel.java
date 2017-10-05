
package org.bihe.ui.actionPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bihe.DAO.EstateDAO;
import org.bihe.DAO.PersonDAO;
import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Data;
import org.bihe.model.Person;
import org.bihe.model.StreetActions;
import org.bihe.network.client.Client;
import org.bihe.playSound.PlaySound;
import org.bihe.ui.GUIManager;
import org.bihe.ui.GamePanel;

public class DicePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private int HEIGHT;
	private int WIDTH;
	private JButton diceButton;
	private JLabel diceOneLabel;
	private JLabel diceTwoLabel;
	public static boolean moveOnToss = true;
	public boolean isYourTurn = true;
	private int diceMultipiedTen;
	private JLabel jailLabel;

	public DicePanel()
	{
		this.setLayout(new FlowLayout());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		HEIGHT = (int) dimension.getHeight();
		WIDTH = (int) dimension.getWidth() * 10 / 46;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(new Color(150, 150, 150));
		this.setPreferredSize(new Dimension(WIDTH / 2, (HEIGHT - 30) / 3));
		//
		diceButton = new JButton("Toss dice");
		diceButton.setPreferredSize(new Dimension(WIDTH / 2 - 40, (HEIGHT - 30) * 2 / 50));
		diceButton.addActionListener(new GetActions());
		this.add(diceButton);
		//
		if (diceOneLabel == null)
			diceOneLabel = new JLabel();

		//
		if (diceTwoLabel == null)
			diceTwoLabel = new JLabel();
	}

	private int makeRandom()
	{
		int a = (int) (Math.random() * 10);
		while (a > 6 || a == 0)
			a = (int) (Math.random() * 10);

		return a;
	}

	private void tossDice()
	{
		diceOneLabel.setPreferredSize(new Dimension(WIDTH / 4, WIDTH / 4));
		diceTwoLabel.setPreferredSize(new Dimension(WIDTH / 4, WIDTH / 4));
		GUIManager.getDicePanel().add(diceOneLabel);
		GUIManager.getDicePanel().add(diceTwoLabel);
		GUIManager.getDicePanel().revalidate();
		GUIManager.getDicePanel().repaint();
	}

	private void inJail()
	{
		jailLabel = new JLabel("You Are in Jail!");
		jailLabel.setPreferredSize(new Dimension(WIDTH / 3, 25));
		jailLabel.setFont(new Font("", Font.BOLD, 13));
		jailLabel.setForeground(Color.RED);
		GUIManager.getDicePanel().add(jailLabel);
	}

	public int getDiceMultipiedTen()
	{
		return diceMultipiedTen;
	}

	public void setDiceEnable(boolean boo)
	{
		diceButton.setEnabled(boo);
		this.revalidate();
		this.repaint();
	}

	private class GetActions implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if (arg0.getSource().equals(diceButton))
			{
				Thread tr = new Thread(new Runnable()
				{			
					@Override
					public void run()
					{
						PlaySound p = new PlaySound("TD.wav");
						p.playSound();
					}
				});	
				tr.start();
				
				GUIManager.getDicePanel().remove(diceOneLabel);
				GUIManager.getDicePanel().remove(diceTwoLabel);
				int a = makeRandom();
				int b = makeRandom();
				try
				{
					Thread.sleep(500);
				} catch (InterruptedException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				diceOneLabel = new JLabel(new ImageIcon(resources.Resources.getImage("diceNo" + a + ".png")));
				diceTwoLabel = new JLabel(new ImageIcon(resources.Resources.getImage("diceNo" + b + ".png")));
				Person p = PersonDAO.getPersonDAO().getThePerson();
				if (moveOnToss && isYourTurn)
				{
					if (a == b)
					{
						if (p.isThreePair())
						{
							p.goToJail();
							//
							int location = p.getLocation();
							GamePanel gamePanel = GUIManager.getGamePanel();
							gamePanel.movePieceOnePlace(p.getPieceNumber(), gamePanel.distance(location, 10));
							//
							p.setLocation(10);
							PersonDAO.getPersonDAO().changePerson(p);
							Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
									EstateDAO.getEstateDAO().getEstates(),
									PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), location,
									PersonDAO.getPersonDAO().getThePerson().getLocation());
							Client.getClient().sendObject(data);
						}
					} else
					{
						p.resetPair();
					}
					if (p.getJail() > 0)
					{
						//
						GUIManager.getGetOutOfJailDialog().setVisible(true);;
						//
						if (jailLabel != null)
							remove(jailLabel);

						if (p.getJail() > 0)
						{
							inJail();
							p.oneRoundInJail();
							PersonDAO.getPersonDAO().changePerson(p);
							Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
									EstateDAO.getEstateDAO().getEstates(),
									PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), 10, 10);
							Client.getClient().sendObject(data);
						} else
						{
							if (jailLabel != null)
								remove(jailLabel);

							tossDice();
							int location = p.getLocation();
							p.setLocation(p.newLocation(a + b));
							PersonDAO.getPersonDAO().changePerson(p);
							GamePanel gamePanel = GUIManager.getGamePanel();
							gamePanel.movePieceOnePlace(p.getPieceNumber(), a + b);
							Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
									EstateDAO.getEstateDAO().getEstates(),
									PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), location,
									PersonDAO.getPersonDAO().getThePerson().getLocation());
							Client.getClient().sendObject(data);
							StreetActions s = new StreetActions();
							s.action();
						}
					} else
					{
						if (jailLabel != null)
							remove(jailLabel);

						tossDice();
						int location = p.getLocation();
						p.setLocation(p.newLocation(a + b));
						PersonDAO.getPersonDAO().changePerson(p);
						GamePanel gamePanel = GUIManager.getGamePanel();
						gamePanel.movePieceOnePlace(p.getPieceNumber(), a + b);
						Data data = new Data(PlayerDAO.getPlayerDAO().getPlayers(),
								EstateDAO.getEstateDAO().getEstates(),
								PersonDAO.getPersonDAO().getThePerson().getPieceNumber(), location,
								PersonDAO.getPersonDAO().getThePerson().getLocation());
						Client.getClient().sendObject(data);
						StreetActions s = new StreetActions();
						s.action();
					}
					setDiceEnable(false);
				} else if(isYourTurn)
				{
					tossDice();
					diceMultipiedTen = (a + b) * 10;
				}

				GUIManager.getDicePanel().revalidate();
				GUIManager.getDicePanel().repaint();
			}
		}
	}
}