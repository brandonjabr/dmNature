package scripts.dmNature.Nodes.Ardougne;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

import scripts.dmNature.Utils.Chest;
import scripts.dmNature.Utils.Location;
import scripts.dmNature.Utils.Node;

public class ThieveNature extends Node {
Location inArd = Location.ardougneLocation;
Location outSideHouse = Location.outSideChestHouse;
Location insideHouse = Location.insideChestHouse;
Location upperFloor = Location.insideUpperChestHouse;
	@Override
	public boolean isNodeValid() {

		return SKILLS.THIEVING.getActualLevel() >= 28 && inArd.atLocation(Player.getPosition());
	}

	@Override
	public void execute() {

		if (onChestPlane()) {
			final int OLDXP = Skills.getXP(SKILLS.THIEVING);
			final RSObject[] CHEST = Objects.findNearest(8, chest.getId());

			if (CHEST.length > 0) {
				if (CHEST[0].isClickable()) {
					if (DynamicClicking.clickRSObject(CHEST[0],
							"Search for traps Chest")) {
						Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								return Skills.getXP(SKILLS.THIEVING) > OLDXP
										|| CHEST[0] == null;
							}
						}, General.random(1000, 2000));

						General.sleep(7000, 8000); // respawn time
					}
				}
			}
		}

		else if (!onChestPlane() && atDoor()) {
			final RSObject[] door = Objects.findNearest(3, chest.getDoorId());

			if (door[0].isClickable()) {
				Clicking.click("Pick-lock Door", door[0]);
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return atInsideDoor();
					}
				}, General.random(1500, 2000));

				final RSObject[] stair = Objects.findNearest(7,
						chest.getStairUp());

				if (stair.length > 0) {
					if (stair[0].isClickable()) {
						Clicking.click("Climb-up Staircase", stair[0]);
						Timing.waitCondition(new Condition() {

							@Override
							public boolean active() {
								return onChestPlane();
							}
						}, General.random(1000, 2000));
					}
				}
			}
		}

		else {
			walkToDoor();
		}

	}

	public static Chest chest;

	private boolean onChestPlane() {
		return Player.getPosition().getPlane() == 1;
	}

	private boolean atDoor() {
		return inArd.atLocation(Player.getPosition());
	}

	private boolean atInsideDoor() {
		return outSideHouse.atLocation(Player.getPosition());
	}

	private void walkToDoor() {
		if (WebWalking.walkTo(chest.getRstile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return atDoor() && Player.getAnimation() == -1;
				}
			}, General.random(3000, 6000));
		}
	}
}
