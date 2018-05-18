package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import util.ops.ArrayOps;

public class DataReader {
	
	public static double[][] getDataFromFile(String fileName) throws FileNotFoundException { // use filename as string instead
		File file = new File(fileName);
		Scanner fileReader = new Scanner(file);
		double[][] data = new double[0][0];
		while(fileReader.hasNextLine()) {
			data = ArrayOps.append(data, new double[0]);
			data[data.length-1] = readFromString(fileReader.nextLine());
		}
		fileReader.close();
		return data;
	}
	
	public static double[][] safeGetDataFromFile(String fileName) {
		try {
			return getDataFromFile(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static double[] readFromString(String s) {
		Scanner stringReader = new Scanner(s);
		double[] data = new double[0];
		while(stringReader.hasNextDouble())
			data = ArrayOps.append(data, stringReader.nextDouble());
		stringReader.close();
		return data;
	}

}
