package com.trc202.Helpers;

public class Helper {

	public static boolean isInteger(String possableNum)
	{
		try{
			Integer.valueOf(possableNum);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}
