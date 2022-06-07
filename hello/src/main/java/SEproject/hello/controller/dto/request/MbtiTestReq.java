package SEproject.hello.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MbtiTestReq {

    @NotEmpty
    private String title;
    @NotEmpty
    private String link;
    @NotEmpty
    private String thumbnail;
    @NotEmpty
    private Integer views;
    @NotEmpty
    private String checkLink;
}
