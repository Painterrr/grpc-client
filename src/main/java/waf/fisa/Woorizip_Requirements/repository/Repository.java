package waf.fisa.Woorizip_Requirements.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import waf.fisa.Woorizip_Requirements.entity.Requirement;

public interface Repository extends JpaRepository<Requirement, String> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Requirement r WHERE r.accountId = :accountId")
    boolean existedByAccountId(String accountId);

    @Query("SELECT r FROM Requirement r WHERE r.accountId = :accountId")
    Requirement readMine(String accountId);
}
