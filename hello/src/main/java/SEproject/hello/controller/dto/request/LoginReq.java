package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginReq {

    @NotEmpty(message = "아이디를 입력하세요!")
    private String memberId;
    @NotEmpty(message = "비밀번호를 입력하세요!")
    private String password;

    public LoginReq(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
