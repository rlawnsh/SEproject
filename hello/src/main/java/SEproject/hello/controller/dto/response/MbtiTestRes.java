package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.controller.dto.MbtiTestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MbtiTestRes extends BaseResponse {

    private List<MbtiTestDto> mbtiTestDtos;

    public MbtiTestRes(String msg, Integer status, List<MbtiTestDto> mbtiTestDtos) {
        super(msg, status);
        this.mbtiTestDtos = mbtiTestDtos;
    }
}
