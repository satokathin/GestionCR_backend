package sc.sek.gestreunion.modele;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@ApiModel(description = "All types of meeting. ")
public class MeetingType {
    @Id
    @GeneratedValue
    private long id;
    @ApiModelProperty(notes = "The name of meeting type")
    private String name;
    /*@OneToMany(mappedBy = "meetingType")
    private List<Meeting> allMeetings;*/
}
