package scripts.dmNature.Nodes.Lumbridge;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;

import scripts.dmNature.Utils.Location;
import scripts.dmNature.Utils.Node;

public class ThieveMen extends Node {
	Location menLocation = scripts.dmNature.Utils.Location.menLocation;

	public boolean isNodeValid() {
		return (searchMen() && SKILLS.HITPOINTS.getActualLevel() == 10 && SKILLS.THIEVING
				.getActualLevel() < 28);
	}

	@Override
	public void execute() {
		pickpocketMen();
	}

	private boolean canReachMen(RSNPC npc) {
		return PathFinding.canReach(npc.getPosition(), false);
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

	private void pickpocketMen() {
		final int OLDXP = Skills.getXP(SKILLS.THIEVING);
		final RSNPC[] victim = NPCs.findNearest("Men", "Woman");

		if (victim.length > 0) {
			if (victim[0].isOnScreen()) {
				if (canReachMen(victim[0]) == false) {
					Walking.walkTo(victim[0].getPosition());
				}
				if (DynamicClicking.clickRSNPC(victim[0], "Pickpocket")) {
					Timing.waitCondition(new Condition() {
						@Override
						public boolean active() {
							return Skills.getXP(SKILLS.THIEVING) > OLDXP
									|| !victim[0].isOnScreen();
						}
					}, General.random(2000, 3500));

					General.sleep(750, 1200);
				}
			} else {
				if (!victim[0].isOnScreen())
					Camera.turnToTile(new RSTile(victim[0].getPosition().getX()
							+ General.random(-4, 5), victim[0].getPosition()
							.getY() + General.random(-4, 3)));
				if (!victim[0].isOnScreen())
					Walking.walkTo(victim[0].getPosition());
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return victim[0].isOnScreen()
								|| victim[0] == null
								|| Player.getPosition().distanceTo(victim[0]) <= 4;
					}
				}, General.random(500, 1000));
			}
		}
	}

}
