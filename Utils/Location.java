package scripts.dmNature.Utils;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;

public enum Location {

	menLocation("MenLocation", new RSArea(new RSTile(3215, 3243, 0),
			new RSTile(3234, 3234, 0))), walkMenLocation("WalkMenLocation",
			new RSArea(new RSTile(3225, 3236, 0), new RSTile(3220, 3241, 0))), lumbStartLocation("Lumbridge-Spawn",
					new RSArea(new RSTile(3226, 3209, 0), new RSTile(3217, 3225, 0))), ardougneLocation("Ardougne-ChestHouse",
							new RSArea(new RSTile(2679, 3297, 0), new RSTile(2669, 3305, 0))), outSideChestHouse("Ardougne-OutsideChestHouse",
									new RSArea(new RSTile(2671, 3305, 0), new RSTile(2677, 3304, 0))), insideChestHouse("Ardougne-InsideChestHouse",
											new RSArea(new RSTile(2673, 3303, 0), new RSTile(2675, 3299, 0))), insideUpperChestHouse("Ardougne-InsideUpperChestHouse",
													new RSArea(new RSTile(2675, 3301, 1), new RSTile(2671, 3299, 1))), walkCowField("Walk-CowField",
															new RSArea(new RSTile(3256, 3268, 0), new RSTile(3260, 3264, 0))), cowField("CowField-Location",
																	new RSArea(new RSTile(3265, 3255, 0), new RSTile(3240, 3299, 0))) ;
	private String name;
	private RSArea rsarea;

	Location(String name, RSArea rsarea) {
		this.name = name;
		this.rsarea = rsarea;
	}

	public RSArea getArea() {
		return this.rsarea;
	}

	public String getName() {
		return this.name();
	}

	public boolean atLocation(RSTile tile) {
		return rsarea.contains(tile);
	}

	public void setArea(RSArea area) {
		this.rsarea = area;
	}

	public static void walkToLocation(final Location location) {
		DPathNavigator dpath = new DPathNavigator();
		dpath.setDoorCacheTime(500);
		if (dpath.traverse(location.getArea().getRandomTile())) {

			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {

					return location.atLocation(Player.getPosition());
				}
			}, General.random(1000, 2000));
		}

	}

}