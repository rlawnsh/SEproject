package SEproject.hello.db.repository;

import SEproject.hello.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByMemberId(String userId);

    Optional<Member> findByEmail(String email);
}
