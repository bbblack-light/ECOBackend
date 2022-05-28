package ECOBackend.controllers.utils.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OperationResponse {
    private String message;

    public OperationResponse(String message) {
        this.message = message;
    }
}
