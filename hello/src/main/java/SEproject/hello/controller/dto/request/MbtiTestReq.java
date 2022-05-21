package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MbtiTestReq {

    @NotNull
    private String title;
    @NotNull
    private String link;
    @NotNull
    private String thumbnail;
    @NotNull
    private Integer views;
}
