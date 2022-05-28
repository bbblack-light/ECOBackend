package ECOBackend.controllers.utils.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class OperationObjectResponse extends OperationResponse {

    private Object object;

    public OperationObjectResponse(String message) {
        super(message);
    }
}
