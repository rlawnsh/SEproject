package SEproject.hello.db.repository;

import SEproject.hello.db.entity.MbtiLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiLevelRepository extends JpaRepository<MbtiLevel, Long> {
    MbtiLevel findByLevel(String level);
}
