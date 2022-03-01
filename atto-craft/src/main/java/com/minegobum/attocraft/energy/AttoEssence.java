package com.minegobum.attocraft.energy;

import com.minegobum.attocraft.utils.Utils;

public class AttoEssence {
	
	public static final int BASE_UNIT_TICK_MULTIPLIER = 100;

	private int Essence, Taint;
	
	public int getEssence() {
		return Essence;
	}
	public int getTaint() {
		return Taint;
	}
	
	public AttoEssence(int essence, int taint) {
		this.Essence = essence;
		this.Taint = taint;
	}
	public AttoEssence(AttoEssence essence) {
		this.Essence = essence.Essence;
		this.Taint = essence.Taint;
	}
	
	public static AttoEssence FromBaseUnit(float units, float taintPercent) {
		float purity = 100 - taintPercent;
		int total = Math.round(BASE_UNIT_TICK_MULTIPLIER*units);
		purity = Utils.Math.ClampFloat(purity, 0, 100);
		int essence = (int)(total*(purity/100f));
		int taint = total-essence;
		return new AttoEssence(essence, taint);
	}
	
	public static AttoEssence Empty() {
		return FromBaseUnit(0, 0);
	}
	
	public float PurityLevel() {
		return Essence/(float)(Essence+Taint);
	}
	
	@Override
	public String toString() {
		return PurityLevel()+"% purity (E/T: "+Essence+"/"+Taint+")";
	}
	
	public AttoEssence Copy() {
		return new AttoEssence(this);
	}
}
