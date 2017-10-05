package org.bihe.ui.actionPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.bihe.ui.GUIManager;


public class ActionPanel extends JPanel
{
	private int HEIGHT;
	private int WIDTH;
	private static final long serialVersionUID = 1L;

	public ActionPanel()
	{
		this.setLayout(new GridLayout(3, 1));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		HEIGHT = (int) dimension.getHeight();
		WIDTH = (int) dimension.getWidth() * 10/46;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(Color.white);
		this.setBounds(0, 0, WIDTH, HEIGHT-55);
		//
		this.add(GUIManager.getPlayerPanel());
		//
		this.add(GUIManager.getEstatePanel());
		//
		JPanel diceAndBtnPanel = new JPanel(new GridLayout(1, 2));
		diceAndBtnPanel.add(GUIManager.getButtonPanel());
		diceAndBtnPanel.add(GUIManager.getDicePanel());
		this.add(diceAndBtnPanel);
	}
	public void addComponent(JPanel p)
	{
		this.add(p);
	}
}
