package com.minegobum.attocraft.utils;

import com.minegobum.attocraft.references.References;

public class Utils {

	public static class Names {
		public static String GetUnlocalizedName(String name) {
			return References.MODID + ":" + name;
		}
		public static String GetRegistryName(String name) {
			return References.MODID + ":" + name;
		}
	}
	
	public static class Math {
		
		public static int ClampInt(int value, int min, int max) {
			if(value < min) return min;
			if(value > max) return max;
			return value;
		}

		public static float ClampFloat(float value, float min, float max) {
			if(value < min) return min;
			if(value > max) return max;
			return value;
		}
		
	}	
}
