package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    List<ApplicationUser> findAll();
    ApplicationUser findUserById(Long id);
    ApplicationUser findByUsername(String username);
}
