package SEproject.hello.service;

import SEproject.hello.db.entity.MbtiTest;
import SEproject.hello.db.repository.MbtiTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MbtiTestService {

    private final MbtiTestRepository mbtiTestRepository;


    public List<MbtiTest> getTestList() {
        return mbtiTestRepository.findAll();
    }

    @Transactional
    public void upTestViews(Long testId) {
        MbtiTest mbtiTest = mbtiTestRepository.findById(testId).orElse(null);
        mbtiTest.upMbtiTestViews();
    }

    public List<MbtiTest> getRecentTest(Long mbtiTestId) {
        return mbtiTestId.equals(0L)? mbtiTestRepository.findByOrderByIdDescWithList(PageRequest.of(0, 2))
                : mbtiTestRepository.findByOrderByIdDesc(mbtiTestId, PageRequest.of(0, 2));
    }

    public List<MbtiTest> getTestByLikes(Integer page) {
        return page.equals(0) ? mbtiTestRepository.findByOrderByLikesDescWithList(PageRequest.of(0, 2))
                : mbtiTestRepository.findByOrderByLikesDescWithList(PageRequest.of(page, 2));
    }

    public List<MbtiTest> getTestByViews(Integer page) {
        return page.equals(0) ? mbtiTestRepository.findByOrderByViewsDescWithList(PageRequest.of(0, 2))
                : mbtiTestRepository.findByOrderByViewsDescWithList(PageRequest.of(page, 2));
    }

}
