package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.controller.dto.MbtiTestBookDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MbtiTestBookRes extends BaseResponse {

    private List<MbtiTestBookDto> mbtiTestBookDtos;

    public MbtiTestBookRes(String msg, Integer status, List<MbtiTestBookDto> mbtiTestBookDtos) {
        super(msg, status);
        this.mbtiTestBookDtos = mbtiTestBookDtos;
    }
}
