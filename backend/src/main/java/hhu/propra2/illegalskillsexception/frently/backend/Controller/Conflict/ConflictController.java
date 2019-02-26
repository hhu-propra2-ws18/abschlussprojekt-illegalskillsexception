package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.Services.ConflictService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyError;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Response.FrentlyResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/conflict")
public class ConflictController {

    private final ConflictService conflictService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public FrentlyResponse retrieveConflictTransction(Authentication authentication) {
        FrentlyResponse response = new FrentlyResponse();

        try {
            final List<Transaction> conflictingTransList = conflictService.retrieveAllConflictedTransacions();
            response.setData(conflictingTransList);
        } catch (Exception e) {
            FrentlyError error = new FrentlyError(e);
            response.setError(error);
        }
        return response;
    }

}
