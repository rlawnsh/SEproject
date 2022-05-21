package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import lombok.Getter;

@Getter
public class FindIdRes extends BaseResponse {

    private String userId;

    public FindIdRes(String msg, Integer status, String userId) {
        super(msg, status);
        this.userId = userId;
    }
}
