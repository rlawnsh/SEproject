package SEproject.hello.service;

import SEproject.hello.config.security.SecurityUtil;
import SEproject.hello.controller.dto.MbtiTestBookDto;
import SEproject.hello.controller.dto.request.PostTestReq;
import SEproject.hello.db.entity.MbtiTestBook;
import SEproject.hello.db.entity.Member;
import SEproject.hello.db.repository.MbtiTestBookRepository;
import SEproject.hello.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static SEproject.hello.common.model.BaseUrl.testBook_Base_URL;

@Service
@RequiredArgsConstructor
public class MbtiTestBookService {

    private final MemberRepository memberRepository;
    private final MbtiTestBookRepository mbtiTestBookRepository;

    public String saveThumbnail(MultipartFile multipartFile) throws IOException {
        String testBook_base_url = testBook_Base_URL;
        String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        String filePath = testBook_base_url + fileName;
        File dest = new File(filePath);
        multipartFile.transferTo(dest);
        return fileName;
    }

    public Long saveTest(PostTestReq postTestReq) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentUserId()).orElse(null);
        MbtiTestBook mbtiTestBook = new MbtiTestBook(member, postTestReq);
        MbtiTestBook save = mbtiTestBookRepository.save(mbtiTestBook);
        return save.getId();
    }

    public List<MbtiTestBookDto> getMemberTest(Integer page) {
        List<MbtiTestBookDto> mbtiTestBookDtos = new ArrayList<>();
        List<MbtiTestBook> testByMemberId = mbtiTestBookRepository.findTestByMemberId(SecurityUtil.getCurrentUserId(), PageRequest.of(page, 2));
        for (MbtiTestBook mbtiTestBook : testByMemberId) {
            MbtiTestBookDto mbtiTestBookDto = new MbtiTestBookDto();
            mbtiTestBookDto.setTestResult(mbtiTestBook.getTestResult());
            mbtiTestBookDto.setTestUrl(mbtiTestBook.getTestUrl());
            mbtiTestBookDto.setThumbnailUrl("/testBook/" + mbtiTestBook.getThumbnailUrl());
            mbtiTestBookDtos.add(mbtiTestBookDto);
        }
        return mbtiTestBookDtos;
    }

    @Transactional
    public void deleteByMemberId(Long mbtiTestBookId) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        mbtiTestBookRepository.deleteByIdAndMemberId(mbtiTestBookId, currentUserId);
    }

    @Transactional
    public void deleteAllByMemberId() {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        mbtiTestBookRepository.deleteAllByMemberId(currentUserId);
    }

    public Integer getMemberTestLength() {
        return mbtiTestBookRepository.findAllByMemberId(SecurityUtil.getCurrentUserId()).size();
    }

    public Boolean checkResultUrl(String testUrl) {
        if (mbtiTestBookRepository.findByTestUrl(testUrl) != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
