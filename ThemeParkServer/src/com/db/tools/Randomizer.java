package com.db.tools;

import java.util.Random;

public class Randomizer {
	
	private static final Random random = new Random();
	
	public static int randomInt(int min, int max){
		return random.nextInt((max - min) + 1) + min;
	}
	
	public static float randomFloat(float min, float max, int persicion){
		return random.nextFloat() * (max - min) + min;
	}
	
	public static char randomCharacter(){
		return (char)(random.nextInt(26) + 'A');
	}
	
	public static boolean randomBoolean(){
		return random.nextBoolean();
	}
}
