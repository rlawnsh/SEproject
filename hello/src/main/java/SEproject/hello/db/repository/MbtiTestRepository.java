package SEproject.hello.db.repository;

import SEproject.hello.db.entity.MbtiTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MbtiTestRepository extends JpaRepository<MbtiTest, Long> {

    @EntityGraph(attributePaths = {"likesList"})
    List<MbtiTest> findAll();

    @EntityGraph(attributePaths = {"likesList"})
    @Query("select m from MbtiTest m order by m.id desc")
    List<MbtiTest> findByOrderByIdDescWithList(Pageable pageable);

// #TODO 커서적용
    @EntityGraph(attributePaths = {"likesList"})
    @Query("select m from MbtiTest m where m.id < :mbtiTestId order by m.id desc")
    List<MbtiTest> findByOrderByIdDesc(@Param("mbtiTestId") Long mbtiTestId, Pageable pageable);

    @EntityGraph(attributePaths = {"likesList"})
    @Query("select m from MbtiTest m order by m.likesList.size desc")
    List<MbtiTest> findByOrderByLikesDescWithList(Pageable pageable);

    @EntityGraph(attributePaths = {"likesList"})
    @Query("select m from MbtiTest m order by m.views desc")
    List<MbtiTest> findByOrderByViewsDescWithList(Pageable pageable);

}
