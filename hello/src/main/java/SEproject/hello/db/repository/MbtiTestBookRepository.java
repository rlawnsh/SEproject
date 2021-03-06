package SEproject.hello.db.repository;

import SEproject.hello.db.entity.MbtiTestBook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MbtiTestBookRepository extends JpaRepository<MbtiTestBook, Long> {

    List<MbtiTestBook> findTestByMemberId(Long currentUserId, Pageable pageable);

    void deleteByIdAndMemberId(Long Id, Long memberId);

    void deleteAllByMemberId(Long memberId);

    List<MbtiTestBook> findAllByMemberId(Long memberId);

    MbtiTestBook findByTestUrl(String testUrl);
}
