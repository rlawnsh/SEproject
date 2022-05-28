package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.controller.dto.MbtiTestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookmarkRes extends BaseResponse {

    private List<MbtiTestDto> mbtiTestDtos;

    public BookmarkRes(String msg, Integer status, List<MbtiTestDto> mbtiTestDtos) {
        super(msg, status);
        this.mbtiTestDtos = mbtiTestDtos;
    }
}
