package org.bihe.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.bihe.model.Request;
import org.bihe.network.client.Client;

public class RequestDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JButton yesButton;
	private JButton noButton;
	private Request request;
	private Dimension dim;
	private JScrollPane scroll;

	public RequestDialog()
	{

		dim = GUIManager.getMainFrame().getSize();
		this.setTitle("new request");
		this.setModal(true);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(dim.width , 300));
		this.setMinimumSize(new Dimension(dim.width , 300));
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - dim.width * 13 / 32,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 75);

		scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(dim.width -30, 200));
		this.add(scroll);

		yesButton = new JButton("yes");
		yesButton.setPreferredSize(new Dimension(dim.width / 4, 30));
		yesButton.addActionListener(new getActions());
		this.add(yesButton);
		//
		noButton = new JButton("no");
		noButton.setPreferredSize(new Dimension(dim.width / 4, 30));
		noButton.addActionListener(new getActions());
		this.add(noButton);
	}

	public void setRequest(Request request)
	{
		this.request = request;
		JLabel requestLabel = new JLabel("");
		requestLabel.setText("<html>&nbsp You've got a new request from " + request.getSender() + "<br>"
				+ "&nbsp He/She wants to give these estates: " + "<br>&nbsp " + request.getGiveEstates() + "<br>"
				+ "&nbsp and this money: " + request.getGiveMoney() + "<hr>" + "&nbsp He/She wants to recieve these estates: "
				+ "<br>&nbsp " + request.getRecieveEstates() + "<br>" + "&nbsp and this money: " + request.getRecieveMoney()
				+ "<br>" + "&nbsp Do you want to accept it?" + "</html>");
		requestLabel.setPreferredSize(new Dimension(dim.width + 200, 165));
		scroll.setViewportView(requestLabel);

		requestLabel.setFont(new Font("", Font.PLAIN, 13));
	}

	private class getActions implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(yesButton))
			{
				String temp = request.getReceiver();
				request.setReceiver(request.getSender());
				request.setSender(temp);
				request.setResponse(1);
				Client.getClient().sendObject(request);
				GUIManager.getRequestDialog().setVisible(false);
			} else if (e.getSource().equals(noButton))
			{
				String temp = request.getReceiver();
				request.setReceiver(request.getSender());
				request.setSender(temp);
				request.setResponse(-1);
				Client.getClient().sendObject(request);
				GUIManager.getRequestDialog().setVisible(false);
			}
		}

	}
}