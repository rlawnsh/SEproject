package SEproject.hello.service;

import SEproject.hello.config.security.SecurityUtil;
import SEproject.hello.controller.dto.MbtiTestDto;
import SEproject.hello.db.entity.BookMark;
import SEproject.hello.db.entity.MbtiTest;
import SEproject.hello.db.entity.Member;
import SEproject.hello.db.repository.BookMarkRepository;
import SEproject.hello.db.repository.MbtiTestRepository;
import SEproject.hello.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMarkService {

    private final BookMarkRepository bookMarkRepository;
    private final MemberRepository memberRepository;
    private final MbtiTestRepository mbtiTestRepository;

    public void postBookMark(Long testId) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentUserId()).orElse(null);
        MbtiTest mbtiTest = mbtiTestRepository.findById(testId).orElse(null);
        BookMark bookMark = new BookMark();
        bookMark.setMbtiTest(mbtiTest);
        bookMark.setMember(member);
        bookMarkRepository.save(bookMark);
    }

    @Transactional
    public List<MbtiTestDto> getBookMark(Integer page) {
        List<MbtiTestDto> mbtiTestDtos = new ArrayList<>();
        List<BookMark> byMemberId = bookMarkRepository.findByMemberId(SecurityUtil.getCurrentUserId(), PageRequest.of(page, 10));
        for (BookMark bookMark : byMemberId) {
            MbtiTestDto mbtiTestDto = new MbtiTestDto(bookMark.getMbtiTest());
            mbtiTestDto.setThumbnail(mbtiTestDto.getThumbnail());
            mbtiTestDtos.add(mbtiTestDto);
        }
        return mbtiTestDtos;
    }

    @Transactional
    public void delete(Long mbtiTestId) {
        Long memberId = SecurityUtil.getCurrentUserId();
        bookMarkRepository.deleteByMemberIdAndMbtiTestId(memberId, mbtiTestId);
    }

    @Transactional
    public void deleteAll() {
        Long memberId = SecurityUtil.getCurrentUserId();
        bookMarkRepository.deleteAllByMemberId(memberId);
    }
}
