package com.lake.serialisation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.junit.Ignore;
import org.junit.Test;

public class SerialisationTest {

	private static final String FILE_NAME = "C:/Temp/Temp.dat";

	@Test
	public void writeToFile() throws IOException, ClassNotFoundException {
		
		MyObjectTamed myObject = new MyObjectTamed(1);
		
		{
			OutputStream os = new FileOutputStream(FILE_NAME, false);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.writeObject(myObject);
			oos.flush();
			oos.close();
		}	
		
	}
		
	@Test
	public void readFromFile() throws IOException, ClassNotFoundException {

			InputStream is = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(is);			
			
			Object o = ois.readObject();
			
			MyObjectTamed copy = null; 			
			if (o instanceof MyObjectTamed)
			{
				copy = (MyObjectTamed)o;
			}	
			
			System.out.print(copy);
	}
}
