package hhu.propra2.illegalskillsexception.frently.backend.Controller.Response;


public class FrentlyException extends Exception {
    private final FrentlyErrorType type;

    public FrentlyException(String msg, FrentlyErrorType type) {
        super(msg);
        System.err.println(msg);
        this.type = type;
    }

    FrentlyErrorType getType() {
        return this.type;
    }
}
