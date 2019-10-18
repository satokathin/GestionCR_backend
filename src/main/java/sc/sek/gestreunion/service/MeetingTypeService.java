package sc.sek.gestreunion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sc.sek.gestreunion.exceptions.MeetingTypeNotFoundException;
import sc.sek.gestreunion.modele.MeetingType;
import sc.sek.gestreunion.repository.MeetingTypeRepository;

import java.util.List;

@Service
public class MeetingTypeService {

    private MeetingTypeRepository meetingTypeRepository;

    public MeetingType getOne(long id) {
        //boolean test = meetingTypeRepository.findById(id).isPresent();
        MeetingType result = meetingTypeRepository.findById(id).get();
        //MeetingType result2 = meetingTypeRepository.getOne(id);
        return result;
    }

    public List<MeetingType> getAll() {
        return meetingTypeRepository.findAll();
    }

    public MeetingType create(MeetingType meetingType) {
        return meetingTypeRepository.save(meetingType);
    }

    public MeetingType update(MeetingType meetingTypeUpdate, long id) {
        meetingTypeUpdate.setId(id);
        meetingTypeRepository.findById(id).
                orElseThrow(() -> new MeetingTypeNotFoundException("MeetingType with id : "+ id + " was not found !"));
        return meetingTypeRepository.save(meetingTypeUpdate);
    }

    public void delete(long id) {
        meetingTypeRepository.findById(id).
                orElseThrow(() -> new MeetingTypeNotFoundException("MeetingType with id : "+ id + " was not found !"));
        meetingTypeRepository.deleteById(id);;
    }

    @Autowired
    public MeetingTypeService(MeetingTypeRepository meetingTypeRepository) {
        this.meetingTypeRepository = meetingTypeRepository;
    }
}
