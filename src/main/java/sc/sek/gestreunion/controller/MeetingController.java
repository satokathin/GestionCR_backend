package sc.sek.gestreunion.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sc.sek.gestreunion.modele.Meeting;
import sc.sek.gestreunion.modele.MeetingType;
import sc.sek.gestreunion.service.MeetingService;
import sc.sek.gestreunion.service.MeetingTypeService;

import java.net.URI;
import java.util.List;

@RequestMapping("api/v1/")
@RestController
@CrossOrigin({"http://localhost:4200", "https://localhost:4200", "http://127.0.0.1:4200", "https://127.0.0.1:4200"})
@Api(tags = "Api for Prayer Meeting Management")
public class MeetingController {

    private MeetingService meetingService;
    private MeetingTypeService meetingTypeService;

    @PostMapping("meetings")
    public ResponseEntity<Meeting> saveMeeting(@RequestBody Meeting meeting) { //type Meeting
        Meeting meetingAdded = meetingService.save(meeting);
        if (meetingAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(meetingAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build(); //return mservice.save(m)
    }

    @PutMapping("meetings/{id}")
    public Meeting updateMeeting(@RequestBody Meeting meeting, @PathVariable long id) {
        return meetingService.update(meeting, id);
    }
    /*@PutMapping("reunions/{id}")
    public ResponseEntity<Meeting> updateMeeting(@RequestBody Meeting meeting, @PathVariable long id) {
        Meeting meetingUpdated = meetingService.update(meeting, id);
        if (meetingUpdated == null)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(meetingUpdated, HttpStatus.Ok);
    }*/

    @GetMapping("meetings/{id}")
    public Meeting getMeetingById(@PathVariable long id) {
        return meetingService.getOne(id);
    }

    @DeleteMapping("meetings/{id}")
    public void deleteMeetingById(@PathVariable long id) {
        meetingService.delete(id);
    }

    @DeleteMapping("meetings")
    public void deleteMeeting(@RequestBody Meeting meeting) {
        meetingService.delete(meeting);
    }

    @GetMapping("meetings")
    public List<Meeting> getAllMeeting() {
        return meetingService.getAll();
    }

    @GetMapping("meetingtypes/{meetingTypeId}/meetings")
    public List<Meeting> getAllMeetingByMeetingTypeId(@PathVariable long meetingTypeId) {
        return meetingService.getAllMeetingsByMeetingTypeId(meetingTypeId);
    }

    @PostMapping("meetingtypes/{meetingTypeId}/meetings")
    public ResponseEntity<Meeting> saveMeetingByMeetingTypeId(@PathVariable long meetingTypeId, @RequestBody Meeting meeting) {
        Meeting meetingAdded = meetingService.saveMeetingByMeetingTypeId(meetingTypeId, meeting);
        if (meetingAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(meetingAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("meetingtypes/{meetingTypeId}/meetings/{id}")
    public Meeting updateMeetingByMeetingTypeId(@PathVariable long meetingTypeId, @RequestBody Meeting meeting, @PathVariable long id) {
        return meetingService.updateByMeetingTypeId(meetingTypeId, meeting, id);
    }

    @DeleteMapping("meetingtypes/{meetingTypeId}/meetings/{id}")
    public void deleteMeetingByIdyMeetingTypeId(@PathVariable long id) {
        meetingService.delete(id);
    }

    @GetMapping("meetingtypes/{meetingTypeId}/meetings/{id}")
    public Meeting getMeetingByIdByMeetingTypeId(@PathVariable long id, @PathVariable long meetingTypeId) {
        return meetingService.getOneByMeetingTypeId(id,meetingTypeId);
    }

    @Autowired
    public MeetingController(MeetingService meetingService, MeetingTypeService meetingTypeService) {
        this.meetingService = meetingService;
        this.meetingTypeService = meetingTypeService;
    }
}
