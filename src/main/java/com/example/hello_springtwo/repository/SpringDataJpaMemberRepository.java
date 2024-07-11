package com.example.hello_springtwo.repository;

import com.example.hello_springtwo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //JPOL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
