package scripts.dmNature.Utils;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public enum Stall {
	
	CAKE("Baker's stall", new RSArea(new RSTile(2666,3310,0), new RSTile(2668,3312,0)), new RSTile(2668,3312,0), 2000);
	
	private String name;
	private RSArea area;
	private RSTile tile;
	private int respawn;
	
	Stall(String name, RSArea area, RSTile tile, int respawn)
	{
		this.name = name;
		this.area = area;
		this.tile = tile;
		this.respawn = respawn;
	}
	
	
	public String getName()
	{
		return this.name;
	}
	
	public RSArea getArea()
	{
		return this.area;
	}
	
	public RSTile getTile()
	{
		return this.tile;
	}
	public int getRespawn()
	{
		return this.respawn;
	}
	

}