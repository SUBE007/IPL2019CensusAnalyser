package exception;

public class IPLCSVException extends Exception {
    public IPLCSVException(String message, String name) {
        super(name);
        this.type = ExceptionType.valueOf(name);
    }
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,NO_CENSUS_DATA,ISSUE_IN_FILE,NO_SUCH_FILE,
        INTERNAL_FILE_PROBLEM,DELIMETER_PROBLEM,HEADER_PROBLEM;
    }
    public ExceptionType type;

    public IPLCSVException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
    public IPLCSVException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
