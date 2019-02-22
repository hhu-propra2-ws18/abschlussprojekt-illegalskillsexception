package hhu.propra2.illegalskillsexception.frently.backend.Controller.Borrow.Inquiry.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class InquiryNotFoundException extends FrentlyException {
    public InquiryNotFoundException() {
        super("The requested inquiry could not be found", FrentlyErrorType.INQUIRY_NOT_FOUND);
    }
}
