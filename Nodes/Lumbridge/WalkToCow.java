package scripts.dmNature.Nodes.Lumbridge;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.WebWalking;
import org.tribot.api.rs3.Skills.SKILLS;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;

import scripts.dmNature.Utils.Location;
import scripts.dmNature.Utils.Node;



public class WalkToCow extends Node{
Location walkToCowField = Location.walkCowField;
	@Override
	public boolean isNodeValid() {
		return SKILLS.HITPOINTS.getActualLevel() != 35 && SKILLS.THIEVING.getActualLevel() == 28;
	}
	@Override
	public void execute() {
		WebWalking.setUseRun(true);
		WebWalking.walkTo(walkToCowField.getArea().getRandomTile());
	}

	
	
}
