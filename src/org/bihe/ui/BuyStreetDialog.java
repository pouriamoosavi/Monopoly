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

import org.bihe.model.StreetActions;

public class BuyStreetDialog extends JDialog
{
	private static final long serialVersionUID = 6333809191238870608L;
	private JButton yesButton;
	private JButton noButton;

	public BuyStreetDialog()
	{
		Dimension dim = GUIManager.getMainFrame().getSize();

		this.setTitle("Buy Street");
		this.setModal(true);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(dim.width * 13 / 16, 150));
		this.setMinimumSize(new Dimension(dim.width * 13 / 16, 150));
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - dim.width * 13 / 32,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 75);
		JLabel buyStreet = new JLabel("    Do you want to buy this street?    ");
		buyStreet.setPreferredSize(new Dimension(dim.width * 10 / 16, 40));
		buyStreet.setFont(new Font("", Font.PLAIN, 13));
		this.add(buyStreet);
		//
		yesButton = new JButton("Yes");
		yesButton.setPreferredSize(new Dimension(dim.width / 4, 30));
		yesButton.addActionListener(new getActions());
		this.add(yesButton);
		//
		noButton = new JButton("No");
		noButton.setPreferredSize(new Dimension(dim.width / 4, 30));
		noButton.addActionListener(new getActions());
		this.add(noButton);
	}

	private class getActions implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(yesButton))
			{
				StreetActions.setBuy(true);
				GUIManager.getBuyStreetDialog().setVisible(false);
			} else if (e.getSource().equals(noButton))
			{
				StreetActions.setBuy(false);
				GUIManager.getBuyStreetDialog().setVisible(false);
			}
		}

	}
}
