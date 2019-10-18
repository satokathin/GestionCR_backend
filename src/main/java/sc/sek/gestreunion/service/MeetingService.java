package sc.sek.gestreunion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sc.sek.gestreunion.exceptions.MeetingNotFoundException;
import sc.sek.gestreunion.modele.Meeting;
import sc.sek.gestreunion.modele.MeetingType;
import sc.sek.gestreunion.repository.MeetingRepository;
import sc.sek.gestreunion.repository.MeetingTypeRepository;

import java.util.List;

@Service
public class MeetingService {
    private MeetingRepository meetingRepository;
    private MeetingTypeRepository meetingTypeRepository;

    public Meeting save(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Meeting update(Meeting meetingUpdated, long id) {
        //if (meetingRepository.existsById(id))
        meetingUpdated.setId(id);
        meetingRepository.findById(id).
                orElseThrow(() -> new MeetingNotFoundException("Meeting with id : "+ id + " was not found !"));
        return meetingRepository.save(meetingUpdated);
        //return null;
    }

    public void delete(Meeting meeting) {
        meetingRepository.findById(meeting.getId()).
                orElseThrow(() -> new MeetingNotFoundException("Meeting with id : "+ meeting.getId() + " was not found !"));
        meetingRepository.delete(meeting);
    }

    public void delete(long id) {
        meetingRepository.findById(id).
                orElseThrow(() -> new MeetingNotFoundException("Meeting with id : "+ id + " was not found !"));
        meetingRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "meeting")
    public Meeting getOne(long id) {//ResponseEntity.ok().body(employee)
        return meetingRepository.findById(id).
                orElseThrow(() -> new MeetingNotFoundException("Meeting with id : "+ id + " was not found !"));
    }

    //@Cacheable(cacheNames = "meetings")
    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    public List<Meeting> getAllMeetingsByMeetingTypeId(long meetingTypeId){
        return meetingRepository.findAllByMeetingTypeId(meetingTypeId);
    }

    public Meeting saveMeetingByMeetingTypeId(long meetingTypeId, Meeting meeting) {
        MeetingType meetingType = meetingTypeRepository.getOne(meetingTypeId);
        meeting.setMeetingType(meetingType);
        return save(meeting);
    }

    public Meeting updateByMeetingTypeId(long meetingTypeId, Meeting meetingUpdated, long id) {
        MeetingType meetingType = meetingTypeRepository.getOne(meetingTypeId);
        meetingUpdated.setMeetingType(meetingType);
        return update(meetingUpdated, id);
    }

    public Meeting getOneByMeetingTypeId(long id, long meetingTypeId) {
        return meetingRepository.findMeetingByIdAndMeetingTypeId(id, meetingTypeId).
                orElseThrow(() -> new MeetingNotFoundException("Meeting with id : "+ id + " was not found !"));
    }

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, MeetingTypeRepository meetingTypeRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingTypeRepository = meetingTypeRepository;
    }
}
