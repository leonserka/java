package hr.unist.lab6.repository;

import hr.unist.lab6.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
