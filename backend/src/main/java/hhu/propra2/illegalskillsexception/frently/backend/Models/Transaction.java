package hhu.propra2.illegalskillsexception.frently.backend.Models;

import org.hibernate.annotations.ForeignKey;

public class Transaction {
    @ForeignKey()
    private Inquiry inquiry;
}
