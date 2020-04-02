package mainApp.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import mainApp.Model.JsonViews.View;

@Data
@AllArgsConstructor
@JsonView(View.Id.class)
public class WSEventDto {
    private ObjectType type;
    private EventType event;
    @JsonRawValue
    private String body;
}
