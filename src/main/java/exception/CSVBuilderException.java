package exception;

public class CSVBuilderException extends Exception {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE
    }

    public IPLCSVException.ExceptionType type;

    public CSVBuilderException(String message, IPLCSVException.ExceptionType type) {
        super(message);
        this.type=type;
    }
}
