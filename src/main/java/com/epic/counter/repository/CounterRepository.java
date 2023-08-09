package com.epic.counter.repository;

import com.epic.counter.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository <Counter, Integer> {
}
