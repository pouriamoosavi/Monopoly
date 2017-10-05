package org.bihe.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class SendRequestFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	  private static final int WIDTH = (Toolkit.getDefaultToolkit().getScreenSize().width)/2;
	  private static final int HEIGHT = (Toolkit.getDefaultToolkit().getScreenSize().height - 55)*2/3;
	public SendRequestFrame()
	{
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationRelativeTo(null);

		
		this.setVisible(true);
	}
}
