package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices.IConflictAdminService;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.Security.Exceptions.NotAdminException;
import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ConflictAdminService implements IConflictAdminService {

    @Override
    public void isAdmin(Set<Role> set) throws Exception {
        for (Role r : set) {
            if (r.getName().equals("ADMIN")) {
                return;
            }
        }
        throw new NotAdminException();
    }

}
