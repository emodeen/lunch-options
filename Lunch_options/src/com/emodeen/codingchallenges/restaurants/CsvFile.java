/**
 * 
 */
package com.emodeen.codingchallenges.restaurants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric
 *
 */
public class CsvFile extends File {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedReader input;
	
	// This is a list of the rows in the file.
	private List<String> rows = new ArrayList<String>();

	/**
	 * @param pathname
	 */
	public CsvFile(String pathname) {
		super(pathname);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param uri
	 */
	public CsvFile(URI uri) {
		super(uri);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param child
	 */
	public CsvFile(String parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parent
	 * @param child
	 */
	public CsvFile(File parent, String child) {
		super(parent, child);
		// TODO Auto-generated constructor stub
	}

	List<String> getRows()
	{
		if (this.isOK()) {
			
			this.open();
			
			this.read();
			
			this.close();
		}
		
		return rows;
	}
	
	void write( String s) {

        try {
            File file = new File("output.csv");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(s);
            output.close();
        } 
        
        catch ( IOException e ) {
             e.printStackTrace();
        }
	}
	
	/**
	 * 
	 * @return This method returns true if the file is a regular file and it can be read.
	 */
	private void read() {
		
		String strRow = null;
		
		try {
			do {
				strRow = input.readLine();
				
				if ((strRow != null) && (!strRow.equals(""))) {
					
					rows.add( strRow);
				}
			} 
			
			while( strRow != null);
		}
		
		catch ( IOException e) {
			System.out.println( "Error reading row");
		}
	}

	/**
	 * 
	 * @return This method returns true if the file is a regular file and it can be read.
	 */
	private boolean isOK() {
		
		boolean fileOK = false;
		boolean readable = false;
		boolean regular = false;
		
		Path path = Paths.get(this.getAbsolutePath());
		
		if ( Files.isRegularFile(path)) {
			regular = true;
		}
		
		else {
			System.out.println("The specified file does not exist, is not a regular file, or it cannot be determined if the file is a regular file or not.");
		}
		
		if ( Files.isReadable(path)) {
			readable = true;
		}
		
		else {
			System.out.println("The specified file is not readable.");
		}
		
		if ( regular && readable) {
			fileOK = true;
		}
		
		return fileOK;
	}
	
	
	/**
	 * Open the file.
	 */
	private void open()
	{
		try {
			input = new BufferedReader( new FileReader( this));
		}
		
		catch ( IOException e) {
			System.out.println( "Error opening file");
		}
	}
	
	
	/**
	 * Close the file
	 */
	private void close()
	{
		try {
			input.close();
		}
		
		catch ( IOException e) {
			System.out.println( "Error closing file");
		}
		
		input = null;
	}
}
