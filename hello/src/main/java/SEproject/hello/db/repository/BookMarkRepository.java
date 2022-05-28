package SEproject.hello.db.repository;

import SEproject.hello.db.entity.BookMark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    List<BookMark> findByMemberId(Long memberId, Pageable pageable);

    void deleteByMemberIdAndMbtiTestId(Long memberId, Long mbtiTestId);
}
