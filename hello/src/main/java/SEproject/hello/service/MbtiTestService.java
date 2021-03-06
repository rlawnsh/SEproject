package SEproject.hello.service;

import SEproject.hello.controller.dto.request.MbtiTestReq;
import SEproject.hello.db.entity.MbtiTest;
import SEproject.hello.db.repository.MbtiTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    public List<MbtiTest> getRecentTest(Integer page) {
        return page.equals(0)? mbtiTestRepository.findByOrderByIdDescWithList(PageRequest.of(0, 16))
                : mbtiTestRepository.findByOrderByIdDescWithList(PageRequest.of(page, 16));
    }

    public List<MbtiTest> getTestByLikes(Integer page) {
        return page.equals(0) ? mbtiTestRepository.findByOrderByLikesDescWithList(PageRequest.of(0, 16))
                : mbtiTestRepository.findByOrderByLikesDescWithList(PageRequest.of(page, 16));
    }

    public List<MbtiTest> getTestByViews(Integer page) {
        return page.equals(0) ? mbtiTestRepository.findByOrderByViewsDescWithList(PageRequest.of(0, 16))
                : mbtiTestRepository.findByOrderByViewsDescWithList(PageRequest.of(page, 16));
    }

    public void saveTest(MbtiTestReq mbtiTestReq) {
        MbtiTest mbtiTest = new MbtiTest();
        BeanUtils.copyProperties(mbtiTestReq, mbtiTest);
        mbtiTestRepository.save(mbtiTest);
    }
}
