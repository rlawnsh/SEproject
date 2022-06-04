package SEproject.hello.controller.api;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.controller.dto.MbtiTestBookDto;
import SEproject.hello.controller.dto.MbtiTestDto;
import SEproject.hello.controller.dto.request.PostTestReq;
import SEproject.hello.controller.dto.response.BookmarkRes;
import SEproject.hello.controller.dto.response.MbtiTestBookRes;
import SEproject.hello.service.BookMarkService;
import SEproject.hello.service.MbtiTestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("info")
@RequiredArgsConstructor
public class MemberInfoApiController {

    private final MbtiTestBookService mbtiTestBookService;
    private final BookMarkService bookMarkService;

    @GetMapping(value = {"/bookmark/{page}", "/bookmark"})
    public ResponseEntity<? extends BaseResponse> getBookMark(@PathVariable(required = false) Integer page) {
        List<MbtiTestDto> bookMark;
        if (page == null) {
            bookMark = bookMarkService.getBookMark(0);
        } else {
            bookMark = bookMarkService.getBookMark(page);
        }
        return ResponseEntity.status(200).body(new BookmarkRes("사용자의 북마크를 불러왔습니다.", 200, bookMark));
    }

    @DeleteMapping("/delete/bookmark/all")
    public void deleteAllBookMark() {
        bookMarkService.deleteAll();
    }

    @DeleteMapping("/delete/bookmark/{mbtiTestId}")
    public void deleteBookMark(@PathVariable Long mbtiTestId) {
        bookMarkService.delete(mbtiTestId);
    }

    @PostMapping("/testThumbnail")
    public ResponseEntity<? extends BaseResponse> uploadThumbnail(@RequestParam("images")MultipartFile multipartFile) throws IOException {
        String fileName = mbtiTestBookService.saveThumbnail(multipartFile);
        return ResponseEntity.status(201).body(new BaseResponse(fileName, 201));
    }

    @PostMapping("/postMbtiTest")
    public ResponseEntity<? extends BaseResponse> postTest(@RequestBody PostTestReq postTestReq) {
        mbtiTestBookService.saveTest(postTestReq);
        return ResponseEntity.status(201).body(new BaseResponse("성공적으로 업로드 하였습니다.", 201));
    }

    @GetMapping(value = {"/memberTest", "/memberTest/{page}"})
    public ResponseEntity<? extends BaseResponse> getMemberTest(@PathVariable(required = false) Integer page) {
        List<MbtiTestBookDto> mbtiTestBookDtos;
        if (page == null) {
            mbtiTestBookDtos = mbtiTestBookService.getMemberTest(0);
        } else {
            mbtiTestBookDtos = mbtiTestBookService.getMemberTest(page);
        }
        return ResponseEntity.status(200).body(new MbtiTestBookRes("멤버에 대한 테스트 결과들을 불러왔습니다.", 200, mbtiTestBookDtos));
    }

    @DeleteMapping("/memberTest/delete/{mbtiTestBookId}")
    public void deleteMbtiTestBook(@PathVariable Long mbtiTestBookId) {
        mbtiTestBookService.deleteByMemberId(mbtiTestBookId);
    }

    @DeleteMapping("/memberTest/delete/all")
    public void deleteAllMbtiTestBook() {
        mbtiTestBookService.deleteAllByMemberId();
    }

}
