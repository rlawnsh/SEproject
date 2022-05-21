package SEproject.hello.service;

import SEproject.hello.db.entity.MbtiTest;
import SEproject.hello.db.repository.MbtiTestRepository;
import lombok.RequiredArgsConstructor;
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

}
