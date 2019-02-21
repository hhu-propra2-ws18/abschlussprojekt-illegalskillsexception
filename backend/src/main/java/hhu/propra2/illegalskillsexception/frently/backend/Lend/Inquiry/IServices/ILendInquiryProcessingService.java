package hhu.propra2.illegalskillsexception.frently.backend.Lend.Inquiry.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;

public interface ILendInquiryProcessingService {
    Inquiry declineInquiry(Long inquiryId) throws Exception;

    Transaction acceptInquiry(Long inquiryId) throws Exception;
}
