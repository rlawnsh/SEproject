package SEproject.hello.service;

import SEproject.hello.config.security.SecurityUtil;
import SEproject.hello.controller.dto.request.PostTestReq;
import SEproject.hello.db.entity.MbtiTestBook;
import SEproject.hello.db.entity.Member;
import SEproject.hello.db.repository.MbtiTestBookRepository;
import SEproject.hello.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
}
