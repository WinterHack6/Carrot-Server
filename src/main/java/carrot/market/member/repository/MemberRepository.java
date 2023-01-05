package carrot.market.member.repository;

import carrot.market.member.domain.Member;
import carrot.market.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdAndStatus(Long memberId, Status active);
}
