package resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

public class Resources 
{
	public static Image getImage(String name)
	{
		return Toolkit.getDefaultToolkit().getImage(Resources.class.getResource(name));
	}
	public static File getFile(String name)
	{
		try
		{
			File f = new File(Resources.class.getResource(name).toURI());
			return f;
		} catch (URISyntaxException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
