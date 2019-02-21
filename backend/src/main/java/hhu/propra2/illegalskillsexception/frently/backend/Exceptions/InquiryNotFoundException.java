package hhu.propra2.illegalskillsexception.frently.backend.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controllers.Response.FrentlyErrorType;

public class InquiryNotFoundException extends FrentlyException {
    public InquiryNotFoundException() {
        super("The requested inquiry could not be found", FrentlyErrorType.INQUIRY_NOT_FOUND);
    }
}
