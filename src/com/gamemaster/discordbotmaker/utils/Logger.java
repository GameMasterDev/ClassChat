package com.gamemaster.discordbotmaker.utils;

public class Logger {

    private String name;

    public Logger(String name) {
        this.name = name;
    }

    public void sendLog(String log) {
        System.out.println("[" + name + "/INFO] " + log);
    }

    public void sendWarn(String warn) {
        System.out.println("[" + name + "/WARN] " + warn);
    }

    public void setLoggerName(String newLoggerName) {
        this.name = newLoggerName;
    }

}
