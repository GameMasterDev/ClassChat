package app.classchat.server;

import app.classchat.server.gui.GUI;
import app.classchat.server.utils.DiscordUtil;
import app.classchat.server.utils.Reference;
import app.classchat.server.utils.logger.Logger;
import app.classchat.server.utils.logger.LoggerUsage;

public class Main {

    private static DiscordUtil discord;
    private static final Logger logger = new Logger("ClassChat Server", LoggerUsage.ALL_USAGES);

    public static void main(String[] args) {

        discord = new DiscordUtil();

        logger.log("Initializing ClassChat...");
        GUI.launchGUI();

        for (String arg : args) {
            if (arg.startsWith("--DiscordRpcDisabled")) {
                Reference.isDiscordRichPresenceEnabled = false;
                logger.warn("Discord RichPresence was disabled: start argument");
            }
        }

        if (!Reference.isDiscordRichPresenceEnabled) {
            discord.stopRpc();
        } else {
            logger.warn("Discord RichPresence was enabled");
        }

    }

}
