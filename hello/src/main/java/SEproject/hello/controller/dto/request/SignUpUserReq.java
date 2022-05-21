package SEproject.hello.controller.dto.request;

import SEproject.hello.db.entity.Authority;
import SEproject.hello.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignUpUserReq {

    @NotNull
    private String memberId;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
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
