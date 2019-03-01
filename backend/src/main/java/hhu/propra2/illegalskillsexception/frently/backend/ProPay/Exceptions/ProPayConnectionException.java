package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class ProPayConnectionException extends FrentlyException {
    public ProPayConnectionException() {
        super("An error occurred when communicating with the ProPay service.", FrentlyErrorType.PROPAY_CONNECTION);
    }
}
