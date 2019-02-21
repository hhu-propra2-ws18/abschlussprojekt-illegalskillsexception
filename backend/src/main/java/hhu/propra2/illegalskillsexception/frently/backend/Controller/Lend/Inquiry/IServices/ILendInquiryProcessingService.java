package hhu.propra2.illegalskillsexception.frently.backend.Controller.Lend.Inquiry.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;

public interface ILendInquiryProcessingService {
    Inquiry declineInquiry(Long inquiryId) throws Exception;

    Transaction acceptInquiry(Long inquiryId) throws Exception;
}
