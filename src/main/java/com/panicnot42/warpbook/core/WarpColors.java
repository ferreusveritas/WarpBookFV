package com.panicnot42.warpbook.core;

public enum WarpColors {
	
	UNBOUND(0xC3C36A),//The default tawny color of a warp page
	BOUND(0x098C76),//The color of an ender pearl
	//PLAYER(0x9F1CE5),//The original ugly purple player page
	PLAYER(0x8e0000),//Red color.. blood maybe?
	HYPER(0x09AA38),//Hyper Green
	DEATHLY(0x131313),//Near Black
	LEATHER(0x654b17);//The color of vanilla books
	
	private int	color;
	
	WarpColors(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
}
