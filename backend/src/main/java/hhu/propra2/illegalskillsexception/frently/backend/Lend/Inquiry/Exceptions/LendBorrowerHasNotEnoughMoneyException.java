package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.Exceptions;

public class LendBorrowerHasNotEnoughMoneyException extends Exception {
    public LendBorrowerHasNotEnoughMoneyException() {
        super("Borrower has not enough money");
    }
}
