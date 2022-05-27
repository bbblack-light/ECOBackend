package ECOBackend.controllers.utils.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OperationResponse {
    private String message;

    public OperationResponse(String message) {
        this.message = message;
    }
}
