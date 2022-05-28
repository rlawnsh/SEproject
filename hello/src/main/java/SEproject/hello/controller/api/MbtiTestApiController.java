package SEproject.hello.controller.api;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.controller.dto.MbtiTestDto;
import SEproject.hello.controller.dto.response.LikesRes;
import SEproject.hello.controller.dto.response.MbtiTestRes;
import SEproject.hello.service.BookMarkService;
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
    private final BookMarkService bookMarkService;

    @GetMapping(value = {"/list/{mbtiTestId}", "/list"})
    public ResponseEntity<? extends BaseResponse> testList(@PathVariable(required = false) Long mbtiTestId) {
        List<MbtiTestDto> collect;

        if (mbtiTestId == null) {
            collect = mbtiTestService.getRecentTest(0L).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        } else {
            collect = mbtiTestService.getRecentTest(mbtiTestId).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        }
        for (MbtiTestDto mbtiTestDto : collect) {
            mbtiTestDto.setThumbnail("/mbtiTestImg/" + mbtiTestDto.getThumbnail());
        }

        return ResponseEntity.status(200).body(new MbtiTestRes("모든 테스트를 가져왔습니다", 200, collect));
    }

    @GetMapping(value = {"/list/likes/{likes}", "/list/likes"})
    public ResponseEntity<? extends BaseResponse> testListByLikes(@PathVariable(required = false) Integer likes) {
        List<MbtiTestDto> collect;

        if (likes == null) {
            collect = mbtiTestService.getTestByLikes(0).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        } else {
            collect = mbtiTestService.getTestByLikes(likes).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        }
        for (MbtiTestDto mbtiTestDto : collect) {
            mbtiTestDto.setThumbnail("/mbtiTestImg/" + mbtiTestDto.getThumbnail());
        }
        return ResponseEntity.status(200).body(new MbtiTestRes("모든 테스트를 가져왔습니다", 200, collect));
    }

    @GetMapping(value = {"/list/views/{views}", "/list/views"})
    public ResponseEntity<? extends BaseResponse> testListByViews(@PathVariable(required = false) Integer views) {
        List<MbtiTestDto> collect;

        if (views == null) {
            collect = mbtiTestService.getTestByViews(0).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        } else {
            collect = mbtiTestService.getTestByViews(views).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        }
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

    @PostMapping("/bookmark/{mbtiTestId}")
    public void postBookMark(@PathVariable Long mbtiTestId) {
        bookMarkService.postBookMark(mbtiTestId);
    }

    @DeleteMapping("/unlikes/{mbtiTestId}")
    public void unlikes(@PathVariable Long mbtiTestId) {
        likesService.unlikes(mbtiTestId);
    }
}
