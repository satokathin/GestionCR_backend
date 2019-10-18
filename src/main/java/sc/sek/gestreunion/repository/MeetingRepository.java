package sc.sek.gestreunion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sc.sek.gestreunion.modele.Meeting;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    List<Meeting> findAllByMeetingTypeId(Long meetingTypeId);
    Optional<Meeting> findMeetingByIdAndMeetingTypeId(Long id, Long meetingTypeId);
}
