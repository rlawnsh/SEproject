package SEproject.hello.db.repository;

import SEproject.hello.db.entity.MbtiTest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MbtiTestRepository extends JpaRepository<MbtiTest, Long> {

    @EntityGraph(attributePaths = {"likesList"})
    List<MbtiTest> findAll();

}
