package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginReq {

    @NotNull
    private String memberId;
    @NotNull
    private String password;

    public LoginReq(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
