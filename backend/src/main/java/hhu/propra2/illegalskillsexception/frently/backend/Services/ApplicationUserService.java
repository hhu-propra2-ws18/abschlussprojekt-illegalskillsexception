package hhu.propra2.illegalskillsexception.frently.backend.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import hhu.propra2.illegalskillsexception.frently.backend.Repositories.IApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserService {

    private IApplicationUserRepository userRepo;

    @Autowired
    public ApplicationUserService(IApplicationUserRepository userRepo) {
        this.userRepo = userRepo;
    }


    public ApplicationUser getUserById(Long userId) {
        Optional<ApplicationUser> userOpt = userRepo.findById(userId);

        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepo.findAll();
    }

    public void deleteUser(long userId) {
        userRepo.deleteById(userId);
    }

    public ApplicationUser updateUser(ApplicationUser updateUser) {
        userRepo.save(updateUser);
        return updateUser;
    }

    public void createUser(String email, String username, String password, String bankAccount) {
        ApplicationUser temp = new ApplicationUser();
        temp.setEmail(email);
        temp.setUsername(username);
        temp.setPassword(password);
        temp.setBankAccount(bankAccount);

        userRepo.save(temp);
    }
}
