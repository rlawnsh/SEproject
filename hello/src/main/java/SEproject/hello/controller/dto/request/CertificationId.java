package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CertificationId {

    @NotEmpty(message = "아이디를 입력하세요!")
    private String memberId;
}
