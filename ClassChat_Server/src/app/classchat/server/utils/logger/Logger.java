package app.classchat.server.utils.logger;

public class Logger {

    private String loggerName;
    private LoggerUsage loggerUsage;
    private String prefix = "ERROR_NO_PREFIX_SELECTED";


    public Logger (String LoggerName, LoggerUsage Usage) {

        this.loggerName = LoggerName;
        this.loggerUsage = Usage;

        if (this.loggerUsage == LoggerUsage.ONLY_FOR_LOG) {
            this.prefix = "[LOG/INFO] ";
        } else if (this.loggerUsage == LoggerUsage.ONLY_FOR_WARNINGS) {
            this.prefix = "[WARN/INFO] ";
        } else if (this.loggerUsage == LoggerUsage.ONLY_FOR_ERRORS) {
            this.prefix = "[ERROR] ";
        } else if (this.loggerUsage == LoggerUsage.ALL_USAGES) {
            this.prefix = "[" + this.loggerName + "] ";
        }

    }

    private String logColor;

    public void log(String log) {
        System.out.println(this.prefix + log);
    }

    public void warn(String warn) {
        System.out.println(this.prefix + warn);
    }

    public void error(String error) {
        System.out.println(this.prefix + error);
    }

    public String getLoggerName() {
        return this.loggerName;
    }

    public LoggerUsage getLoggerUsage() {
        return this.loggerUsage;
    }

}
