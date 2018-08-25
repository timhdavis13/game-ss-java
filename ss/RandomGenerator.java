package com.thd.ss;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator 
{
	public static int randomNumber(int min, int maxInclusive)
	{
		// NOTE: ThreadLocalRandom.nextInt(min, max): max is exclusive, so add 1 to make inclusive:
		return ThreadLocalRandom.current().nextInt(min, maxInclusive + 1);
	}
}
