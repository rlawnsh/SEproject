package SEproject.hello.controller.dto.request;

import SEproject.hello.db.entity.Authority;
import SEproject.hello.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignUpUserReq {

    @NotEmpty(message = "아이디를 입력하세요!")
    private String memberId;

    @NotEmpty(message = "비밀번호를 입력하세요!")
    private String password;

    @NotEmpty(message = "이름을 입력하세요!")
    private String name;

    @NotEmpty(message = "이메일을 입력하세요!")
    private String email;

    @NotEmpty(message = "mbti를 입력하세요!")
    private String mbti;

    public Member toUserEntity(String encode) {
        return Member.builder()
                .name(name)
                .password(encode)
                .email(email)
                .memberId(memberId)
                .authority(Authority.ROLE_USER)
                .mbti(mbti)
                .build();
    }
}
