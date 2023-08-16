package com.epic.counter.contoller;

import DTO.IncrementRequest;
import DTO.InlineResponse200;
import api.GetCounterApi;
import api.IncrementOunterApi;
import com.epic.counter.model.Counter;
import com.epic.counter.service.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/counter")
public class CounterController implements GetCounterApi, IncrementOunterApi {

    @Autowired
    private CounterService counterService;

    @Operation(summary = "Get counters")
    @GetMapping("/counters")
    public String showAllCounters(Model model) {
        List<Counter> allCounters = counterService.getAllCounters();
        model.addAttribute("allCounters", allCounters);
        return "all-counters";
    }

    @Operation(summary = "Create a new counter")
    @GetMapping("/add-new-counter")
    public String createCounter(Model model) {
        Counter counter = new Counter();
        counter.setValue(null);
        model.addAttribute("counter", counter);
        return "create-counter";
    }

    @Operation(summary = "Save a counter")
    @PostMapping("/counters")
    public String saveCounter(@ModelAttribute("counter") Counter counter) {
        counterService.saveCounter(counter);
        return "redirect:/counter/counters";
    }

    @Operation(summary = "Delete a counter")
    @GetMapping("/delete-counter")
    public String deleteCounter(@RequestParam("counterId") int id) {
        counterService.deleteCounter(id);
        return "redirect:/counter/counters";
    }

    @Override
    public ResponseEntity<InlineResponse200> getCounterGet(String counterId) {
        InlineResponse200 response = new InlineResponse200();
        if (counterId != null) {
            response.setSuccess(true);
            response.setData(Integer.valueOf(counterId));
            return ResponseEntity.ok(response);
        } else {
            response.setSuccess(false);
            response.setData(0);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<InlineResponse200> incrementOunterPost(IncrementRequest body) {
        Integer counterId = Integer.valueOf(body.getCounterId());
        Integer incrementCount = body.getIncrementCount();
        if (counterId >= 1_000_000_000 || incrementCount <= 0) {
            InlineResponse200 response = new InlineResponse200();
            response.setSuccess(false);
            response.setData(0);
            return ResponseEntity.badRequest().body(response);
        }
        Counter counter = counterService.getCounter(counterId);
        if (counter!=null) {
            counterService.incrementCounter(counterId, incrementCount);

            InlineResponse200 response = new InlineResponse200();
            response.setSuccess(true);
            response.setData(counter.getValue());
            return ResponseEntity.ok(response);
        }
        else {
            InlineResponse200 response = new InlineResponse200();
            response.setSuccess(false);
            response.setData(0);
            return ResponseEntity.notFound().build();
        }
    }
}

