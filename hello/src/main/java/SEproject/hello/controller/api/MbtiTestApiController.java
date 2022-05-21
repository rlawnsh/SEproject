package SEproject.hello.controller.api;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.controller.dto.MbtiTestDto;
import SEproject.hello.controller.dto.response.LikesRes;
import SEproject.hello.controller.dto.response.MbtiTestRes;
import SEproject.hello.service.LikesService;
import SEproject.hello.service.MbtiTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class MbtiTestApiController {

    private final MbtiTestService mbtiTestService;
    private final LikesService likesService;

    @GetMapping("/list")
    public ResponseEntity<? extends BaseResponse> testList() {
        List<MbtiTestDto> collect = mbtiTestService.getTestList().stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        for (MbtiTestDto mbtiTestDto : collect) {
            mbtiTestDto.setThumbnail("/mbtiTestImg/" + mbtiTestDto.getThumbnail());
        }
        return ResponseEntity.status(200).body(new MbtiTestRes("모든 테스트를 가져왔습니다", 200, collect));
    }

    @GetMapping("/likeListByUser")
    public ResponseEntity likeByUser() {
        return ResponseEntity.status(200).body(likesService.getLikesCountByMemberId().stream().map(likes -> new LikesRes(likes)).collect(Collectors.toList()));
    }

    @PostMapping("/views/{mbtiTestId}")
    public void upViews(@PathVariable Long mbtiTestId) {
        mbtiTestService.upTestViews(mbtiTestId);
    }

    @PostMapping("/likes/{mbtiTestId}")
    public void likes(@PathVariable Long mbtiTestId) {
        likesService.likes(mbtiTestId);
    }

    @DeleteMapping("/unlikes/{mbtiTestId}")
    public void unlikes(@PathVariable Long mbtiTestId) {
        likesService.unlikes(mbtiTestId);
    }
}
