package hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class ProPayException extends FrentlyException {
    public ProPayException() {
        super("An error occured when communicating with the ProPay service.", FrentlyErrorType.PROPAY_CONNECTION);
    }
}
