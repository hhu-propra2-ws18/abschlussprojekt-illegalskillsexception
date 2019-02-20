package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Inquiry;
import hhu.propra2.illegalskillsexception.frently.backend.Models.Transaction;
import hhu.propra2.illegalskillsexception.frently.backend.Models.TransactionResponseExport;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionRepository ITransactionRepository;

    @Autowired
    public TransactionService(ITransactionRepository pTransactionRepo) {
        this.ITransactionRepository = pTransactionRepo;
    }

    public void createTransaction(Transaction.Status status, Inquiry inquiry) {
        final Transaction temp = setTransaction(new Transaction(), inquiry);
        ITransactionRepository.save(temp);
    }

    public Transaction updateTransactionReturnDate(Transaction t, LocalDate date) {
        if (ITransactionRepository.existsById(t.getId())) {
            t.setReturnDate(date);
            return ITransactionRepository.save(t);
        }
        return null;
    }

    public Transaction updateTransactionStatus(Transaction t, Transaction.Status status) {
        if (ITransactionRepository.existsById(t.getId())) {
            t.setStatus(status);
            return ITransactionRepository.save(t);
        }
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return ITransactionRepository.findAll();
    }

    public Transaction getTransaction(long id) {
        if(ITransactionRepository.existsById(id)){
            return ITransactionRepository.findById(id).get();
        }
        return null;
    }

    public List<Transaction> getAllConflicts() {
        List<Transaction> transactions = ITransactionRepository.findAll();
        List<Transaction> conflicts = new ArrayList<>();
        for (Transaction t : transactions) {
            if(t.getStatus().equals(Transaction.Status.conflict)) {
                conflicts.add(t);
            }
        }
        return conflicts;
    }

    public List<Transaction> getAllTransactionsForUser(long id) {
        List<Transaction> transactions = ITransactionRepository.findAll();
        List<Transaction> temp = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getInquiry().getLender().getId() == id || t.getInquiry().getBorrower().getId() == id) {
                temp.add(t);
            }
        }
        return temp;
    }

    public List<TransactionResponseExport> exportTransactions(long id) {
        List<Transaction> transactions = getAllTransactionsForUser(id);
        List<TransactionResponseExport> temp = new ArrayList<>();
        for (Transaction t : transactions) {
            TransactionResponseExport tempExport = new TransactionResponseExport();
            if (t.getInquiry().getLender().getId() == id) {
                tempExport.setLender(true);
            } else {
                tempExport.setLender(false);
            }
            tempExport.setLocation(t.getInquiry().getArticle().getLocation());
            tempExport.setStatus("" + t.getStatus());
            tempExport.setTitle(t.getInquiry().getArticle().getTitle());
            tempExport.setReturnDate(t.getReturnDate());
            temp.add(tempExport);
        }
        return temp;
    }

    private Transaction setTransaction(Transaction temp, Inquiry inquiry) {
        temp.setInquiry(inquiry);
        temp.setStatus(Transaction.Status.open);
        temp.setReturnDate(null);
        return temp;
    }
}
