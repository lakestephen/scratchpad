package com.lake.william;

import java.util.Scanner;

public class WilliamTest {

	/**
	 * @param args
	 */
	public static void main(String arg[]) {		Scanner input = new Scanner(System.in); // Decl. & init. a Scanner.

		while (true) {
			System.out.print("What is your age? >"); 
			String age = input.next(); 
			System.out.print("What is your hair color? >"); 
			String hairColor = input.next(); 
			System.out.println(); 
	
			String name = "sorry i don't know your name";
			try {
				int iAge = Integer.parseInt(age);
				switch(iAge) {
				case 3:
					if  (iHairColor strawberry blonde)
					name = "Lauren";
					break;
				case 5:
					if  (iHairColor blonde)
					name = "Emily";
					break;
				case 9:
					if  (iHairColor blonde)
					name = "Eloise";
					break;
				case 12:
					if  (iHairColor blonde)
					name = "William";
					break;
				case 39:
					if  (iHairColor salt and pepper)
					name = "Daddy";
					break;
				case 40:
					if  (iHairColor broun)
					name = "Mummy";
					break;
				case 59:
					if  (iHairColor gray)
					name = "Grandma";
					break;
				case 61:
					if  (iHairColor gray)
					name = "Grandad";
					break;
				}
				
				if (iAge > 99) {
					name = "a wally. Enter a number less than 100";
			}
			catch (Exception e) {
				name = "a wally. Enter a number less than 100";
			}

			System.out.println("You name is " + name + ". I am clever arnt I");
			System.out.println(); 
		}
	}

}
