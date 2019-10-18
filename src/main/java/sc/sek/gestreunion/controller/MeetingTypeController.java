package sc.sek.gestreunion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sc.sek.gestreunion.modele.MeetingType;
import sc.sek.gestreunion.service.MeetingTypeService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin({"http://localhost:4200", "https://localhost:4200", "http://127.0.0.1:4200", "https://127.0.0.1:4200"})
@RequestMapping("api/v1/")
public class MeetingTypeController {

    private MeetingTypeService meetingTypeService;

    @PostMapping("meetingtypes")
    public ResponseEntity<MeetingType> saveMeeting(@RequestBody MeetingType meetingType) { //type Meeting
        MeetingType meetingTypeAdded = meetingTypeService.create(meetingType);
        if (meetingTypeAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(meetingTypeAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build(); //return mservice.save(m)
    }

    @PutMapping("meetingtypes/{id}")
    public MeetingType updateMeeting(@RequestBody MeetingType meetingType, @PathVariable long id) {
        return meetingTypeService.update(meetingType, id);
    }

    @GetMapping("meetingtypes/{id}")
    public MeetingType getMeetingById(@PathVariable long id) {
        return meetingTypeService.getOne(id);
    }

    @DeleteMapping("meetingtypes/{id}")
    public void deleteMeetingTypeById(@PathVariable long id) {
        meetingTypeService.delete(id);
    }

    @GetMapping("meetingtypes")
    public List<MeetingType> getAllMeetingTypes() {
        return meetingTypeService.getAll();
    }

    @Autowired
    public MeetingTypeController(MeetingTypeService meetingTypeService) {
        this.meetingTypeService = meetingTypeService;
    }
}
