package ovcharka.conceptservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordUpdateRequest {

    private String word;
    private String definition;
    private Integer score;
}
