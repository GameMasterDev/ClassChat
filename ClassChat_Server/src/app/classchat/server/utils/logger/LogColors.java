package app.classchat.server.utils.logger;

public enum LogColors {

    BLACK("\\u001B[30m"),
    RED("\\u001B[31m"),
    GREEN("\\u001B[32m"),
    YELLOW("\\u001B[33m"),
    BLUE("\\u001B[34m"),
    MAGENTA("\\u001B[35m"),
    CYAN("\\u001B[36m"),
    WHITE("\\u001B[37m"),
    RESET_COLOR("\\u001B[0m");

    private String color_value;

    LogColors(String color_value) {
        this.color_value = color_value;
    }

    public String getValue() {
        return color_value;
    }

}
