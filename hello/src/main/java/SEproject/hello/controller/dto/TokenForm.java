package SEproject.hello.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenForm {

    private String accessToken;
    private String refreshToken;

    public TokenForm(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
