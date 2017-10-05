package org.bihe.network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Data;
import org.bihe.model.Exit;
import org.bihe.model.Request;
import org.bihe.ui.GUIManager;

public class Client
{

	private ObjectInputStream objIn;
	private ObjectOutputStream objOut;
	private static Client instance;
	private boolean openGameFrame = true;
	private int clientNo;
	private int numberOfPlayers;

	public static Client getClient()
	{
		if (instance == null)
			instance = new Client();
		return instance;
	}

	private Client()
	{
	}

	public void runClient(int port, String ip)
	{
		{
			try
			{
				Socket s = new Socket(ip, port);
				objIn = new ObjectInputStream(s.getInputStream());
				objOut = new ObjectOutputStream(s.getOutputStream());
				sendObject(PersonDAO.getPersonDAO().getThePerson());
				clientNo = (int) objIn.readObject();
				numberOfPlayers = (int) objIn.readObject();

				Runnable checkForInput = new Runnable()
				{

					@Override
					public void run()
					{
						while (true)
						{
							try
							{
								Thread.sleep(100);

								objOut.flush();
								objOut.reset();
								Object obj = objIn.readObject();
								if (obj instanceof Request)
								{
									Request request = (Request) obj;
									request.analyseRequest();

								} else if (obj instanceof Data)
								{
									Data data = (Data) obj;
									data.analysData();
									if (openGameFrame)
									{
										GUIManager.getGameFrame();
										GUIManager.getMainFrame().setVisible(false);
										GUIManager.getPlayerPanel().fillPlayersList();
										openGameFrame = false;
									}
									//
									int myPieceNo = PersonDAO.getPersonDAO().getThePerson().getPieceNumber();
									if (myPieceNo != 1)
									{
										if (data.isPlayerMove())
										{
											if (data.getPieceNumber() == myPieceNo - 1)
											{
												GUIManager.getDicePanel().setDiceEnable(true);
											}
										}
									} else if (myPieceNo == 1)
									{
										if (data.isPlayerMove())
										{
											int lastPlayer = GUIManager.getMakeNewGamePanel().getPlayerNo();
											if (data.getPieceNumber() == lastPlayer)
											{
												GUIManager.getDicePanel().setDiceEnable(true);
											}
										}
									}

								} else if (obj instanceof Exit)
								{
									Exit exit = (Exit) obj;
									objIn.close();
									objOut.close();
									s.close();
									JOptionPane.showMessageDialog(null,
											exit.getUserName() + " exited so game will be closed");
									Thread.sleep(5000);
									System.exit(0);
								}

							} catch (ClassNotFoundException e)
							{
								JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							} catch (IOException e)
							{
								JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							} catch (InterruptedException e)
							{
								JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				};
				Thread t = new Thread(checkForInput);
				t.start();

			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	public void sendObject(Object obj)
	{
		try
		{
			objOut.writeObject(obj);
			objOut.flush();
			objOut.reset();
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int getClientNo()
	{
		return clientNo;
	}

	public void setClientNo(int clientNo)
	{
		this.clientNo = clientNo;
	}

	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers)
	{
		this.numberOfPlayers = numberOfPlayers;
	}

}