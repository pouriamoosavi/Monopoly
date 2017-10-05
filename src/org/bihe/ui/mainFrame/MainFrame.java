package org.bihe.ui.mainFrame;

import javax.swing.*;

import org.bihe.ui.GUIManager;

import java.awt.*;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private final int HEIGHT = 600;
	private final int WIDTH = 400;
	private int x = dim.width / 2 - WIDTH / 2;
	private int y = dim.height / 2 - HEIGHT / 2;
	private JPanel center;

	public MainFrame()
	{
		this.setLayout(new BorderLayout());
		setBounds(x, y, WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 600));
		this.center = new JPanel(new FlowLayout());
		this.center.add(GUIManager.getSignInPanel());
		this.add(center);
		this.setVisible(true);
	}

	public void addComponent(JPanel p)
	{
		this.center.removeAll();
		this.center.add(p);
		this.center.repaint();
		this.center.revalidate();
		this.repaint();
		this.revalidate();
	}
}
