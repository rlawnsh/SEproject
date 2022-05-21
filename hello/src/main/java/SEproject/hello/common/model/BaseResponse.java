package SEproject.hello.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    private String msg;
    private Integer status;

    public BaseResponse(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }
}
