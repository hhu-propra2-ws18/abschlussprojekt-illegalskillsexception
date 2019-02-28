package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Article.Exceptions;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyErrorType;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyException;

public class PendingInquiryException extends FrentlyException {
    public PendingInquiryException() {
        super("This Article has pending Inquiries", FrentlyErrorType.PENDING_INQUIRIES);
    }
}
