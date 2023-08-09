package com.epic.counter.service;

import com.epic.counter.model.Counter;
import com.epic.counter.repository.CounterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class CounterServiceTest {
    @Mock
    private CounterRepository counterRepository;

    @InjectMocks
    private CounterService counterService;

    @Test
    public void testGetCounter() {
        Integer counterId = 4;
        Counter counter = new Counter();
        counter.setId(counterId);
        when(counterRepository.findById(counterId)).thenReturn(Optional.of(counter));
        Counter result = counterService.getCounter(counterId);
        Assertions.assertEquals(counter, result);
    }

    @Test
    void testGetAllCounters() {
        List<Counter> counterList = new ArrayList<>();
        Counter counter_1 = new Counter();
        counter_1.setId(1);
        Counter counter_2 = new Counter();
        counter_2.setId(2);
        Counter counter_3 = new Counter();
        counter_3.setId(3);
        when(counterRepository.findAll()).thenReturn(counterList);
        List<Counter> result = counterService.getAllCounters();
        Assertions.assertEquals(counterList, result);
        verify(counterRepository, times(1)).findAll();
    }

    @Test
    public void testSaveCounter() {
        Counter counter = new Counter();
        counter.setId(1);
        counter.setValue(2);
        counterService.saveCounter(counter);
        verify(counterRepository, times(1)).save(counter);
    }

    @Test
    public void testDeleteCounter() {
        int counterId = 123;
        doNothing().when(counterRepository).deleteById(counterId);
        counterService.deleteCounter(counterId);
        verify(counterRepository, times(1)).deleteById(counterId);
    }
}