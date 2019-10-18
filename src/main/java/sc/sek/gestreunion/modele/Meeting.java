package sc.sek.gestreunion.modele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString // or @Data
@ApiModel(description = "All details about the prayer meeting. ")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated meeting ID")
    private long id;
    //@JsonFormat(/*shape = JsonFormat.Shape.STRING, */pattern = "MM-dd-yyyy HH:mm:ss") //LocalTime   HH:mm a Z
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)// ok //@DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")// ok
    @ApiModelProperty(notes = "The begining of the prayer meeting")
    private LocalDateTime begining;
    //@JsonFormat(/*shape = JsonFormat.Shape.STRING, */pattern = "MM-dd-yyyy HH:mm:ss") //"MM-dd-yyyy HH:mm:ss"
    //@DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")// ok //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)// ok
    @ApiModelProperty(notes = "The end of the prayer meeting")
    private LocalDateTime ending;
    @ApiModelProperty(notes = "The number of members present at the meeting")
    private int nbOfMember;
    @ApiModelProperty(notes = "The number of guests")
    private int nbOfGuest;
    @ApiModelProperty(notes = "The name of the moderator of the prayer meeting")
    private String moderator;
    @ApiModelProperty(notes = "The name of the speaker/orator of the prayer meeting")
    private String speaker;
    @ApiModelProperty(notes = "The them addressed at the meeting")
    private String theme;
    @ApiModelProperty(notes = "The teaching summary")
    private String summary;
    @ApiModelProperty(notes = "The remark on the meeting")
    private String remark;
    @ApiModelProperty(notes = "The type of the meeting")
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "meeting_type_id")
    // @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MeetingType meetingType;

    public Meeting(LocalDateTime begin, LocalDateTime end, int membersPresentNb, int guestsNb, String moderator,
                   String speaker, String theme, String summary) {
        this.begining = begin;
        this.ending = end;
        this.nbOfMember = membersPresentNb;
        this.nbOfGuest = guestsNb;
        this.moderator = moderator;
        this.speaker = speaker;
        this.theme = theme;
        this.summary = summary;
    }

    public Meeting() {
    }
}
