package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;

public interface IProcessConflictService {
    Transaction releaseConflictingTransaction(long transactionId) throws Exception;

    Transaction punishConflictingTransaction(long transactionId) throws Exception;
}
