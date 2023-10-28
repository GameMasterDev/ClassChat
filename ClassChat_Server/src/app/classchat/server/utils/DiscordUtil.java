package app.classchat.server.utils;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class DiscordUtil {

    private final DiscordRPC lib = DiscordRPC.INSTANCE;
    private final DiscordRichPresence presence = new DiscordRichPresence();

    public DiscordUtil () {

        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        this.lib.Discord_Initialize("1134607567134396537", handlers, true, "");
        this.presence.startTimestamp = System.currentTimeMillis() / 1000;
        this.presence.details = "ClassChat Server";
        this.presence.state = "Handling Clients";
        this.presence.largeImageKey = "server";
        this.presence.largeImageText = Reference.SERVER_NAME;
        this.presence.smallImageKey = "version";
        this.presence.smallImageText = Reference.SERVER_VERSION;
        this.lib.Discord_UpdatePresence(this.presence);

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored){}
            }
        }, "RPC-Callback-Handler").start();
    }
    public void setDetails(final String details) {
        this.presence.details = details;
        this.lib.Discord_UpdatePresence(this.presence);
    }

    public void setState(final String state) {
        this.presence.state = state;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setLargeImage(final String key) {
        this.presence.largeImageKey = key;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setLargeImageText(final String text) {
        this.presence.largeImageText = text;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setSmallImage(final String key) {
        this.presence.smallImageKey = key;
        this.lib.Discord_UpdatePresence(this.presence);
    }
    public void setSmallImageText(final String text) {
        this.presence.smallImageText = text;
        this.lib.Discord_UpdatePresence(this.presence);
    }

    public void stopRpc() {
        this.lib.Discord_ClearPresence();
    }

}