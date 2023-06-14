package gmail.astersoter.reqresin.models.lombok.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUsersModelRs {
    private int page, per_page, total, total_pages;
    private List<UsersModel> data;
}
