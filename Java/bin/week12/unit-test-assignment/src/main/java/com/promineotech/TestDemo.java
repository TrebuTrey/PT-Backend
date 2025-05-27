package com.promineotech;

import java.util.Random;

public class TestDemo {
	
	public int addPositive(int a, int b) {
		try{
			assert(a > 0 && b > 0);
			
			return a + b;
		}catch(Exception e) {
			throw new IllegalArgumentException("Both"
					+ " parameters must be positive!");
		}
	}
	
	public boolean equals100(int a, int b, int c) {
		return (a + b + c == 100);
	}
	
	public int randomNumberSquared() {
		int rand = getRandomInt();
		
		return rand*rand;
	}
	
	int getRandomInt() {
		Random random = new Random();
		
		return random.nextInt(10) + 1;
	}
	
}
