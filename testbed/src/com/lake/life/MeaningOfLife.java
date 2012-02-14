package com.lake.life;

public class MeaningOfLife {
	
	public static String findOutWhatLifeIsAllAbout() {
		int meaning = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				for (int k = 0; k < 300; k++) {
					for (int m = 0; m < 7000; m++) {
						double d = Math.random();
						int val = (int)d + 1;
						
						int meaning2 = meaning;
						meaning += d + 1;
						if (meaning2 + 1!= meaning) {
							System.out.println("hmm");
						}
					}
				}
			}
		}
		return String.valueOf(meaning).replaceAll("0*$", "");
	}

	public static void main(String[] args) {
		System.out.println(findOutWhatLifeIsAllAbout());
	}
}
