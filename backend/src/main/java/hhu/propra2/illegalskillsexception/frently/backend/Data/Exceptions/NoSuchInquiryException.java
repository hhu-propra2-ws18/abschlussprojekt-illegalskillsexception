package hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class NoSuchInquiryException extends FrentlyException {
    public NoSuchInquiryException() {
        super("The requested inquiry could not be found", FrentlyErrorType.INQUIRY_NOT_FOUND);
    }
}
