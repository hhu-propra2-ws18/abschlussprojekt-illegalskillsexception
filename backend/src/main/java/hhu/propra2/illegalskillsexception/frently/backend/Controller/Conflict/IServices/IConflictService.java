package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;

import java.util.List;

public interface IConflictService {
    List<Transaction> retrieveAllConflictingTransactions();
}
