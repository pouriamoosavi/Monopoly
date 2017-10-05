package org.bihe.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class FileService {

	public static boolean writeObj(Serializable obj, String filePath) {
		File f = new File(filePath);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		ObjectOutputStream objOut = null;
		try {
			objOut = new ObjectOutputStream(new BufferedOutputStream( new FileOutputStream(f)));
			objOut.writeObject(obj);
			return true ;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally {
			if (objOut != null){
				try {
					objOut.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		return false ;
	}
	
	
	public static Serializable readObj(String filePath){
		File f = new File(filePath);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		try {
			@SuppressWarnings("resource")
			ObjectInputStream objIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			return (Serializable) objIn.readObject() ;
		}catch (EOFException e) {
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {			
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}
