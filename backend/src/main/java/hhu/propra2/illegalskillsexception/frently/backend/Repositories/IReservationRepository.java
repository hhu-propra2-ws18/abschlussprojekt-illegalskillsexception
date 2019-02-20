package hhu.propra2.illegalskillsexception.frently.backend.Repositories;

import hhu.propra2.illegalskillsexception.frently.backend.Models.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IReservationRepository extends CrudRepository<Reservation, Long> {
    Optional<Reservation> findByPropayId(long id);
}

