package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostTestReq {

    @NotNull
    private String testResult;
    @NotNull
    private String testUrl;
    @NotNull
    private String thumbnailUrl;
}
