package hhu.propra2.illegalskillsexception.frently.backend.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;

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
