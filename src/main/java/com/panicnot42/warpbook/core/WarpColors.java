package com.panicnot42.warpbook.core;

public enum WarpColors {
	
	UNBOUND(0xC3C36A),
	BOUND(0x098C76),
	//PLAYER(0x9F1CE5),
	PLAYER(0x8e0000),
	HYPER(0x09AA38),
	DEATHLY(0x131313);
	
	private int	color;
	
	WarpColors(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
}
