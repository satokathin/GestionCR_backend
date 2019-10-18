package sc.sek.gestreunion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sc.sek.gestreunion.modele.MeetingType;

@Repository
public interface MeetingTypeRepository extends JpaRepository<MeetingType,Long> {
}
