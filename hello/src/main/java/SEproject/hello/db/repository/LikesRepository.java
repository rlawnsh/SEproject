package SEproject.hello.db.repository;

import SEproject.hello.db.entity.Likes;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query(value = "INSERT INTO likes(mbti_test_id, member_id) VALUES(:mbtiTestId, :memberId)", nativeQuery = true)
    void likes(Long mbtiTestId, Long memberId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE mbti_test_id = :mbtiTestId AND member_id = :memberId", nativeQuery = true)
    void unlikes(Long mbtiTestId, Long memberId);

    List<Likes> findByMemberId(Long memberId);
}
