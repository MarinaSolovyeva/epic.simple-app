package com.epic.counter.service;

import com.epic.counter.model.Counter;
import com.epic.counter.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CounterService {

    private final CounterRepository counterRepository;
    private final Map<Integer, Object> counterLocks = new ConcurrentHashMap<>();

    @Autowired
    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Transactional
    public List<Counter> getAllCounters() {
        return counterRepository.findAll();
    }

    @Transactional
    public void saveCounter(Counter counter) {
        counterRepository.save(counter);
    }

    @Transactional
    public Counter getCounter(int id) {
        Optional<Counter> foundCounter = counterRepository.findById(id);
        return foundCounter.orElse(null);
    }

    @Transactional
    public void deleteCounter(int id) {
        counterRepository.deleteById(id);
    }

    @Transactional
    public void incrementCounter(Integer counterId, Integer incrementCount) {
        Counter counter = getCounter(counterId);
        synchronized (counterLocks.computeIfAbsent(counterId, k -> counter)) {
            Integer initialValue = counter.getValue();
            counter.setValue(initialValue + incrementCount);
            saveCounter(counter);
        }
    }
}

