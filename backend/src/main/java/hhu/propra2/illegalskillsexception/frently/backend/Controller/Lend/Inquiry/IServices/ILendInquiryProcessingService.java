package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Transaction.Exceptions.InsuffientFundsException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Exceptions.NoSuchInquiryException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.ProPay.Exceptions.ProPayConnectionException;

public interface ILendInquiryProcessingService {
    Inquiry declineInquiry(Long inquiryId) throws NoSuchInquiryException;

    Transaction acceptInquiry(Long inquiryId)
            throws NoSuchInquiryException, ProPayConnectionException, InsuffientFundsException;
}
