package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostTestReq {

    @NotEmpty(message = "테스트 결과를 입력하세요!")
    private String testResult;
    @NotEmpty(message = "테스트 결과 url을 입력하세요!")
    private String testUrl;
    @NotEmpty(message = "테스트 썸네일 url을 입력하세요!")
    private String thumbnailUrl;
}
