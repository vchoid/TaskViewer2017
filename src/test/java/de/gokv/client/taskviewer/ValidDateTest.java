package de.gokv.client.taskviewer;


import org.junit.Test;


import de.gokv.client.reader.ValidDate;

public class ValidDateTest {


	ValidDate testDate = new ValidDate();
	String date = "";
	
	
	@Test
	public void testDateLength() {
		
		date =  "29.02.1982";
		System.out.println(date);
		testDate.validDate(date);
		System.out.println(date);
		System.out.println(testDate.validDate(date));
		
		

	}

}
