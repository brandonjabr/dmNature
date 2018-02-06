package scripts.dmNature.Nodes.Lumbridge;

import org.tribot.api2007.NPCs;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSNPC;

import scripts.dmNature.Utils.Node;
import scripts.dmNature.Utils.Location;

public class WalkToMen extends Node {
	Location menLocation = scripts.dmNature.Utils.Location.menLocation;

	@Override
	public boolean isNodeValid() {
		return (searchMen()
				&& SKILLS.HITPOINTS.getActualLevel() == 10
				&& SKILLS.THIEVING.getActualLevel() < 28);
	}

	@Override
	public void execute() {
		Location.walkToLocation(Location.walkMenLocation);
	}

	private boolean searchMen() {
		if (menLocation.atLocation(Player.getPosition()) == true) {
			RSNPC[] men = NPCs.find("Men", "Woman");
			if (men != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
}