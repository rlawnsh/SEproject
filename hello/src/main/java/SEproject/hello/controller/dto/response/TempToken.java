package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempToken extends BaseResponse {

    private Long memberId;
    private String accessToken;

    public TempToken(String msg, Integer status,Long id, String accessToken) {
        super(msg, status);
        this.memberId = id;
        this.accessToken = accessToken;
    }
}
