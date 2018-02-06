package scripts.dmNature.Nodes.Lumbridge;

import org.tribot.api.General;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceChild;

import scripts.dmNature.Utils.Location;
import scripts.dmNature.Utils.Node;

public class ProtectSkills extends Node {
	Location spawnPoint = Location.lumbStartLocation;
//HUGELY WORK IN PROGRESS BECAUSE SIMPLE INTERFACE
	@Override
	public boolean isNodeValid() {

		return spawnPoint.atLocation(Player.getPosition())
				&& SKILLS.THIEVING.getXP() == 0;
	}

	@Override
	public void execute() {
		do {
			GameTab.open(TABS.EQUIPMENT);
			General.sleep(100, 150);
		} while (isEquipmentOpen() == false);
		if (openDeathItems()) {
			General.sleep(50, 80);
			clickProtectSkills();
		}
		GameTab.open(TABS.COMBAT);
		Combat.selectIndex(0);
		Combat.setAutoRetaliate(false);
	}

	public boolean isEquipmentOpen() {
		return GameTab.getOpen() == TABS.EQUIPMENT;

	}

	public boolean openDeathItems() {
		RSInterfaceChild deathScreen = Interfaces.get(387, 21);
		if (deathScreen != null) {
			deathScreen.click();
			return true;
		}
		return false;
	}

	public void clickProtectSkills() {
		RSInterfaceChild attackOption = Interfaces.get(226, 19);
		RSInterfaceChild thieveOption = Interfaces.get(226, 27);
		RSInterfaceChild hitpointsOption = Interfaces.get(226, 30);
		if (attackOption != null) {
			attackOption.click();
			General.sleep(50, 80);
		}
		if (thieveOption != null) {
			thieveOption.click();
			General.sleep(50, 80);
		}

		if (hitpointsOption != null) {
			thieveOption.click();
			General.sleep(50, 80);
		}

	}
}
