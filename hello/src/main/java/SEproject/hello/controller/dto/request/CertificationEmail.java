package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CertificationEmail {

    @NotEmpty(message = "이메일을 입력하세요!")
    private String email;
}
