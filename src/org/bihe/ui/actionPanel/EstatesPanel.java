package org.bihe.ui.actionPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.bihe.DAO.PersonDAO;
import org.bihe.model.Estate;

public class EstatesPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private ArrayList<Estate> estatesList = PersonDAO.getPersonDAO().getThePerson().getEstates();
	private int HEIGHT;
	private int WIDTH;

	public JList<Estate> getEstates()
	{
		return estates;
	}

	public void setEstates(JList<Estate> estates)
	{
		this.estates = estates;
	}

	private DefaultListModel<Estate> model1;

	private JList<Estate> estates;

	public EstatesPanel()
	{
		this.setLayout(new FlowLayout());
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		HEIGHT = (int) dimension.getHeight();
		WIDTH = (int) dimension.getWidth() * 10 / 46;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setBackground(Color.white);
		this.setBounds(0, 0, WIDTH, (HEIGHT - 30) / 3);
		//
		JLabel estateLabel = new JLabel("Estates you own: ");
		estateLabel.setPreferredSize(new Dimension(WIDTH - 10, 15));
		this.add(estateLabel);
		//
		model1 = new DefaultListModel<>();
		estates = new JList<>(model1);
		estates.setPreferredSize(new Dimension(WIDTH + 50, HEIGHT / 3 + 500));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(WIDTH - 50, HEIGHT / 3 - 70));
		scrollPane.setViewportView(estates);
		this.add(scrollPane);
	}

	public void updateEstates()
	{
		estatesList = PersonDAO.getPersonDAO().getThePerson().getEstates();
		if (estatesList != null)
		{
			model1.removeAllElements();
			for (Estate st : estatesList)
			{			
				model1.addElement(st);
			}
		}
		
	}
}