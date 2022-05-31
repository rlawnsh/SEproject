package SEproject.hello.db.repository;

import SEproject.hello.db.entity.MbtiTestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiTestBookRepository extends JpaRepository<MbtiTestBook, Long> {

}
