package scripts.dmNature.Utils;

import org.tribot.api2007.types.RSTile;

public enum Chest {

	NATURE(11736, new RSTile(2673,3304,0),11720, 16671, 16673, new RSTile(2672, 3301, 1), new RSTile(2673,3303,0));
	
	private int id;
	private RSTile rstile;
	private int doorId;
	private int stairUp;
	private int stairDown;
	private RSTile chestTile;
	private RSTile insideDoor;
	
	Chest(int id, RSTile rstile, int doorId, int stairUp, int stairDown, RSTile chestTile, RSTile insideDoor)
	{
		this.id = id;
		this.rstile = rstile;
		this.doorId = doorId;
		this.stairUp = stairUp;
		this.stairDown = stairDown;
		this.chestTile = chestTile;
		this.insideDoor = insideDoor;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public RSTile getRstile()
	{
		return this.rstile;
	}
	
	public int getDoorId()
	{
		return this.doorId;
	}
	public int getStairUp()
	{
		return this.stairUp;
	}
	public int getStairDown()
	{
		return this.stairDown;
	}
	public RSTile getChestTile()
	{
		return this.chestTile;
	}
	public RSTile getInsideDoor()
	{
		return this.insideDoor;
	}
}