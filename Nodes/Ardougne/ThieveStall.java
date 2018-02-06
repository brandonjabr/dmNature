package scripts.dmNature.Nodes.Ardougne;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.dmNature.Utils.Node;
import scripts.dmNature.Utils.Stall;

public class ThieveStall extends Node {

	@Override
	public boolean isNodeValid() {
		return false;
	}

	@Override
	public void execute() {

		if (inSpot()) {
			final int OLDXP = Skills.getXP(SKILLS.THIEVING);
			final RSObject[] sStall = Objects.findNearest(2, stall.getName());

			if (sStall.length > 0) {

				if (Camera.getCameraAngle() < 40)
					Camera.setCameraAngle(General.random(50, 70));

				if (DynamicClicking.clickRSObject(sStall[0], "Steal-from "
						+ stall.getName())) {
					Timing.waitCondition(new Condition() {

						@Override
						public boolean active() {
							return Skills.getXP(SKILLS.THIEVING) > OLDXP;
						}
					}, General.random(500, 1000));

					General.sleep((stall.getRespawn() - 1000),
							stall.getRespawn());
				} // click
			} // if

		}

		else if (inLoc() && !inSpot()) {
			getInPosition();
		}

		else {
			walkToLoc();
		}

	}

	public static Stall stall;

	private boolean inLoc() {
		return stall.getArea().contains(Player.getPosition());
	}

	private boolean inSpot() {
		return Player.getPosition().equals(stall.getTile());
	}

	private void walkToLoc() {
		if (WebWalking.walkTo(stall.getTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(200);
					return inLoc();
				}
			}, General.random(3000, 6000));
		}
	}

	private void getInPosition() {
		ABCUtil abc = null;
		abc = new ABCUtil();
		if (Player.getPosition().distanceTo(stall.getTile()) >= abc.INT_TRACKER.WALK_USING_SCREEN
				.next()) {
			DynamicClicking.clickRSTile(stall.getTile(), "Walk here");
			abc.INT_TRACKER.WALK_USING_SCREEN.reset();
		} else {
			Walking.walkTo(stall.getTile());
		}

		Timing.waitCondition(new Condition() {

			@Override
			public boolean active() {
				General.sleep(200);
				return inSpot() && !Player.isMoving();
			}
		}, General.random(500, 750));
	}
}
