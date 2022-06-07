package SEproject.hello.controller.api;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.controller.dto.MbtiTestDto;
import SEproject.hello.controller.dto.request.MbtiTestReq;
import SEproject.hello.controller.dto.response.LikesRes;
import SEproject.hello.controller.dto.response.MbtiTestRes;
import SEproject.hello.service.BookMarkService;
import SEproject.hello.service.LikesService;
import SEproject.hello.service.MbtiTestService;
import SEproject.hello.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class MbtiTestApiController {

    private final MbtiTestService mbtiTestService;
    private final LikesService likesService;
    private final BookMarkService bookMarkService;
    private final S3Service s3Service;

    @GetMapping(value = {"/list/{page}", "/list"})
    public ResponseEntity<? extends BaseResponse> testList(@PathVariable(required = false) Integer page) {
        List<MbtiTestDto> collect;

        if (page == null) {
            collect = mbtiTestService.getRecentTest(0).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        } else {
            collect = mbtiTestService.getRecentTest(page).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        }
        for (MbtiTestDto mbtiTestDto : collect) {
            mbtiTestDto.setThumbnail(mbtiTestDto.getThumbnail());
        }

        return ResponseEntity.status(200).body(new MbtiTestRes("모든 테스트를 가져왔습니다", 200, collect));
    }

    @GetMapping(value = {"/list/likes/{page}", "/list/likes"})
    public ResponseEntity<? extends BaseResponse> testListByLikes(@PathVariable(required = false) Integer likes) {
        List<MbtiTestDto> collect;

        if (likes == null) {
            collect = mbtiTestService.getTestByLikes(0).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        } else {
            collect = mbtiTestService.getTestByLikes(likes).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        }
        for (MbtiTestDto mbtiTestDto : collect) {
            mbtiTestDto.setThumbnail(mbtiTestDto.getThumbnail());
        }
        return ResponseEntity.status(200).body(new MbtiTestRes("모든 테스트를 가져왔습니다", 200, collect));
    }

    @GetMapping(value = {"/list/views/{page}", "/list/views"})
    public ResponseEntity<? extends BaseResponse> testListByViews(@PathVariable(required = false) Integer views) {
        List<MbtiTestDto> collect;

        if (views == null) {
            collect = mbtiTestService.getTestByViews(0).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        } else {
            collect = mbtiTestService.getTestByViews(views).stream().map(test -> new MbtiTestDto(test)).collect(Collectors.toList());
        }
        for (MbtiTestDto mbtiTestDto : collect) {
            mbtiTestDto.setThumbnail(mbtiTestDto.getThumbnail());
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

    @PostMapping("/thumbnail")
    public ResponseEntity<? extends BaseResponse> testThumbnail(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        String thumbnail = s3Service.uploadThumbnail(multipartFile);
        return ResponseEntity.status(201).body(new BaseResponse(thumbnail, 201));
    }

    @PostMapping("/upload")
    public ResponseEntity<? extends BaseResponse> uploadTest(@RequestBody MbtiTestReq mbtiTestReq) {
        mbtiTestService.saveTest(mbtiTestReq);
        return ResponseEntity.status(201).body(new BaseResponse("테스트 등록을 완료했습니다.", 201));
    }
}
