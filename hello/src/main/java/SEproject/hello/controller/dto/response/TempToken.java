package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempToken extends BaseResponse {

    private String accessToken;

    public TempToken(String msg, Integer status, String accessToken) {
        super(msg, status);
        this.accessToken = accessToken;
    }
}
