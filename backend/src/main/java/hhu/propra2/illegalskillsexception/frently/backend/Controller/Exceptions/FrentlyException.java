package hhu.propra2.illegalskillsexception.frently.backend.Controller.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;

public class FrentlyException extends Exception {
    private final FrentlyErrorType type;

    public FrentlyException(String msg, FrentlyErrorType type) {
        super(msg);
        this.type = type;
    }

    public FrentlyErrorType getType() {
        return this.type;
    }
}
