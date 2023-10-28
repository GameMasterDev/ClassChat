package app.classchat.server.utils.logger;

public enum LoggerUsage {

    ONLY_FOR_WARNINGS("only_warns"),
    ONLY_FOR_ERRORS("only_errors"),
    ONLY_FOR_LOG("only_logs"),
    ALL_USAGES("all_usages");

    private final String logger_usage;

    LoggerUsage(String logger_usage) {
        this.logger_usage = logger_usage;
    }

    public String getValue() {
        return logger_usage;
    }

}
