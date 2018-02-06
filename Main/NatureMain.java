package scripts.dmNature.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import org.tribot.api.input.Mouse;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.dmNature.Nodes.Ardougne.ThieveNature;
import scripts.dmNature.Nodes.Lumbridge.ProtectSkills;
import scripts.dmNature.Nodes.Lumbridge.ThieveMen;
import scripts.dmNature.Nodes.Lumbridge.WalkToCow;
import scripts.dmNature.Nodes.Lumbridge.WalkToMen;
import scripts.dmNature.Utils.Node;

@ScriptManifest(authors = { "Liam." }, category = "DMM", name = "DeadManMode Nature Thiever")
public class NatureMain extends Script {
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	private final Thread anti = new Thread(new AntiBan());
	public static boolean loggedout = false;
	private String status = "";

	@Override
	public void run() {
		Mouse.setSpeed(150 + (int) (Math.random() * ((300 - 250) + 1)));
		initialize();

		anti.setDaemon(true);
		anti.start();
		while (true) {
			System.out.println("TEST 1");
			for (final Node n : nodes) {
				if (Login.getLoginState() == STATE.LOGINSCREEN) {
					if (loggedout == true) {
						stopScript();
					}

				}
				if (n.isNodeValid()) {
					System.out.println("NODE VALIDD");
					status = n.getClass().getSimpleName();
					System.out.println(n.getClass().getSimpleName());
					n.execute();
				}
			}
			sleep(7);
		}

	}

	private void initialize() {

		{

			if (Login.getLoginState() == STATE.INGAME) {

			}

			nodes.add(new ProtectSkills());
			nodes.add(new WalkToMen());
			nodes.add(new ThieveMen());
			nodes.add(new WalkToCow());
			nodes.add(new WalkToMen());
			nodes.add(new ThieveNature());

		}
	}
}