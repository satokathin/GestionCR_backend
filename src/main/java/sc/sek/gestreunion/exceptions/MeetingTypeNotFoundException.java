package sc.sek.gestreunion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MeetingTypeNotFoundException extends RuntimeException {
    public MeetingTypeNotFoundException(String s) {
        super(s);
    }
}
