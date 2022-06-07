package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangePwdReq {

    @NotEmpty(message = "비밀번호를 입력하세요!")
    private String password;
}
