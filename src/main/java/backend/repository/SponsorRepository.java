package backend.repository;

import backend.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query("SELECT r.userID FROM Sponsor r where r.username = :username")
    Long findIDbyUsername(@Param("username") String username);

    @Query("SELECT r.userID FROM Sponsor r where r.name = :name")
    Long findIDByName(@Param("name") String name);
}
