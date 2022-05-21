package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes extends BaseResponse {

    private String accessToken;
    private String refreshToken;

    public LoginRes(String msg, Integer status, String accessToken, String refreshToken) {
        super(msg, status);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
