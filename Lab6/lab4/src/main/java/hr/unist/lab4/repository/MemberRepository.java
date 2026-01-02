package hr.unist.lab4.repository;

import hr.unist.lab4.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
