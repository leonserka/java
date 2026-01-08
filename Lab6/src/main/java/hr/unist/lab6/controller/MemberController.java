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

    private final MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        Member created = repository.save(member);
        return ResponseEntity
                .created(URI.create("/api/members/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@PathVariable Long id) {
        return ResponseEntity.ok(repository.findById(id).orElseThrow());
    }
}
