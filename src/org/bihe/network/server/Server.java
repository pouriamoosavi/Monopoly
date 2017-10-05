package org.bihe.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.bihe.DAO.PlayerDAO;
import org.bihe.model.Exit;
import org.bihe.model.Person;
import org.bihe.model.Request;
import org.bihe.ui.GUIManager;

public class Server
{
	private ArrayList<ObjectInputStream> objIns = new ArrayList<>();
	private ArrayList<ObjectOutputStream> objOuts = new ArrayList<>();
	@SuppressWarnings("unused")
	private static int counter = -1;
	private static Server instance;
	private ArrayList<Socket> sockets = new ArrayList<>();

	public static Server getServer()
	{
		if (instance == null)
			instance = new Server();

		return instance;
	}

	private Server()
	{

	}

	public void runServer(int port, int numberOfPlayers)
	{

		try
		{
			@SuppressWarnings("resource")
			ServerSocket ss = new ServerSocket(port);
			
			for (int i = 0; i < numberOfPlayers; i++)
			{
				counter = i + 1;
				Socket s = ss.accept();
				sockets.add(s);
				ThreadedServer t = new ThreadedServer(s);
				new Thread(t).start();
			}
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void sendObject(int socketNumber, Object obj)
	{
		@SuppressWarnings("unused")
		Socket s = sockets.get(socketNumber);

		try
		{
			objOuts.get(socketNumber).writeObject(obj);
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void sendObjectToAll(Object obj)
	{
		for (int i = 0; i < sockets.size(); i++)
		{
			try
			{
				objOuts.get(i).writeObject(obj);
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void sendClintNo()
	{
		for (int i = 0; i < sockets.size(); i++)
		{
			try
			{
				objOuts.get(i).writeObject(i + 1);
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class ThreadedServer implements Runnable
	{
		private Socket s;

		public ThreadedServer(Socket s)
		{
			this.s = s;
		}

		@Override
		public void run()
		{

			try
			{
				ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream());
				objOuts.add(objOut);

				ObjectInputStream objIn = new ObjectInputStream(s.getInputStream());
				objIns.add(objIn);

				Person person = (Person) objIn.readObject();
				GUIManager.getMakeNewGamePanel()
						.updateList("Name: " + person.getUserName() + "    IP: " + s.getInetAddress());
				PlayerDAO.getPlayerDAO().addPlayer(person);

				while (true)
				{
					Object obj = objIn.readObject();
					if (obj instanceof Request)
					{
						Request request = (Request) obj;
						try
						{
							Thread.sleep(100);
						} catch (InterruptedException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						sendObject(PlayerDAO.getPlayerDAO().getOnePlayer(request.getReceiver()).getPieceNumber() - 1,
								obj);

					} else if (obj instanceof Exit)
					{
						sendObjectToAll(obj);
						
						
						
					} else
					{
						sendObjectToAll(obj);
					}
				}
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}