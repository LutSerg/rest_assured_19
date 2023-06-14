package gmail.astersoter.reqresin.models.lombok.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    private UserData data;
    private UserSupport support;
}
