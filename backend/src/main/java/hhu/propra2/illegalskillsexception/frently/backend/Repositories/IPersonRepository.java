package hhu.propra2.illegalskillsexception.frently.backend.Controllers.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPersonRepository extends CrudRepository {
    public List<User> findAll();
    public User findUserById(Long id);
}
