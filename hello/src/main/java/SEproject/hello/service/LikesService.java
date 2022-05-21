package SEproject.hello.service;

import SEproject.hello.config.security.SecurityUtil;
import SEproject.hello.db.entity.Likes;
import SEproject.hello.db.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void likes(Long mbtiTestId) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        likesRepository.likes(mbtiTestId, currentUserId);
    }

    @Transactional
    public void unlikes(Long mbtiTestId) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        likesRepository.unlikes(mbtiTestId, currentUserId);
    }

    public List<Likes> getLikesCountByMemberId() {
        return likesRepository.findByMemberId(SecurityUtil.getCurrentUserId());
    }
}
