package hr.unist.lab6.controller;

import hr.unist.lab6.model.Member;
import hr.unist.lab6.repository.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberRepository repo;

    public MemberController(MemberRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        Member saved = repo.save(member);
        return ResponseEntity.created(URI.create("/api/members/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Member> getAll() {
        return repo.findAll();
    }
}
