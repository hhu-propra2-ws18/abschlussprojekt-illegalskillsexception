package hhu.propra2.illegalskillsexception.frently.backend.Exceptions;

public class InquiryNotFoundException extends Exception {
    public InquiryNotFoundException() {
        super("The requested inquiry could not be found");
    }
}
