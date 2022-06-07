package SEproject.hello.service;

import SEproject.hello.config.security.SecurityUtil;
import SEproject.hello.controller.dto.MbtiTestBookDto;
import SEproject.hello.controller.dto.request.PostTestReq;
import SEproject.hello.db.entity.MbtiTest;
import SEproject.hello.db.entity.MbtiTestBook;
import SEproject.hello.db.entity.Member;
import SEproject.hello.db.repository.MbtiTestBookRepository;
import SEproject.hello.db.repository.MbtiTestRepository;
import SEproject.hello.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MbtiTestBookService {

    private final MemberRepository memberRepository;
    private final MbtiTestRepository mbtiTestRepository;
    private final MbtiTestBookRepository mbtiTestBookRepository;
    private final S3Service s3Service;

    public String saveThumbnail(MultipartFile multipartFile) throws IOException {
        String fileName = s3Service.uploadThumbnail(multipartFile);
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
        List<MbtiTestBook> testByMemberId = mbtiTestBookRepository.findTestByMemberId(SecurityUtil.getCurrentUserId(), PageRequest.of(page, 10));
        for (MbtiTestBook mbtiTestBook : testByMemberId) {
            MbtiTestBookDto mbtiTestBookDto = new MbtiTestBookDto();
            mbtiTestBookDto.setTestResult(mbtiTestBook.getTestResult());
            mbtiTestBookDto.setTestUrl(mbtiTestBook.getTestUrl());
            mbtiTestBookDto.setThumbnailUrl(mbtiTestBook.getThumbnailUrl());
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

    public boolean checkDuplicateResultUrl(String testUrl) {
        if (mbtiTestBookRepository.findByTestUrl(testUrl) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkResultUrl(String testUrl) {
        List<MbtiTest> all = mbtiTestRepository.findAll();
        for (MbtiTest mbtiTest : all) {
            int length = mbtiTest.getCheckLink().length();
            if (testUrl.length() < length) {
                continue;
            }
            String substring = testUrl.substring(0, length);
            if (substring.equals(mbtiTest.getCheckLink())) {
                return true;
            }
        }
        return false;
    }
}
