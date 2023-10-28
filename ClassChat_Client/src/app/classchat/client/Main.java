package app.classchat.client;

import app.classchat.client.gui.GUI;
import app.classchat.client.logger.Logger;
import app.classchat.client.logger.LoggerUsage;
public class Main {

    private static final Logger logger = new Logger("ClassChat Client", LoggerUsage.ALL_USAGES);

    public static void main(String[] args) {
        logger.log("Bienvenue sur ClassChat Client v2.0 - By GameMaster");
        GUI.launchGUI();
    }

}
