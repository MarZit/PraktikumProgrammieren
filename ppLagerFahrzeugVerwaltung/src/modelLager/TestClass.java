package modelLager;

import databaseLager.DatabaseCreator;

public class TestClass {
	
	public static void main (String [] args){
		
		DatabaseCreator dc = new DatabaseCreator();
		dc.createSchema();
		dc.createData();
		
		
	}
}
