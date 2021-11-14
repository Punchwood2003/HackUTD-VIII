package com.hackutd.viii.python;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class was originally going to be used to validate the version of Python 
 * installed on the user's machine; however, we ended up finding a way to use
 * the Yahoo Finance API using Java and didn't end up using this class. 
 * This class is here for any potential use in the future
 * @author MatthewSheldon
 */
public class PythonVersionChecker {
	public boolean checkPythonInstallationVersion() throws IOException, InterruptedException {
		Process runPythonVersion = Runtime.getRuntime().exec("PythonInstalationVersion.bat");
		runPythonVersion.waitFor();
		BufferedReader file = new BufferedReader(new FileReader("PythonVersion.txt"));
		String line = file.readLine();
		if(line == null || !line.equals("Python 3.10.0")) {
			System.out.println("Either 'python' is not a recognized command or you are using an outdated version of python");
			System.out.println("Please Install Python v3.10.0 before continuing...");
			System.out.println("https://www.microsoft.com/store/productId/9PJPW5LDXLZ5");
			file.close();
			return false;
		}
		else {
			System.out.println("Using python version 3.10.0...");
			file.close();
			return true;
		}
	}
}
