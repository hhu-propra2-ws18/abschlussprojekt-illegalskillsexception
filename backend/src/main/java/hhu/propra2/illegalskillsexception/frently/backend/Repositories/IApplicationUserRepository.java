package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {
    List<ApplicationUser> findAll();

    Optional<ApplicationUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
