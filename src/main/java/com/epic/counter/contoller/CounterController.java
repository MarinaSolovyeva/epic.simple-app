package com.epic.counter.contoller;

import com.epic.counter.model.Counter;
import com.epic.counter.service.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@Tag(name = "Counter")
@RequestMapping("/counter")
public class CounterController {

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
        counter.setValue(null); // for correct display view
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Counter value returned successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")})
    @Operation(summary = "Get a counter by ID")
    @ResponseBody
    @GetMapping("/get-counter")
    public ResponseEntity<Integer> getCount(@RequestParam Integer counterId) {
        Counter counter = counterService.getCounter(counterId);
        if (counter!=null) {
            return ResponseEntity.ok(counter.getValue());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Counter value returned successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")})
    @Operation(summary = "Increment count")
    @PostMapping("/increment-counter")
    public ResponseEntity<Integer> incrementCount(@RequestBody Map<String, Integer> requestBody) {
        Integer counterId = requestBody.get("counterId");
        Integer incrementCount = requestBody.get("incrementCount");

        if (counterId == null || counterId >= 1_000_000_000 ||
                incrementCount == null || incrementCount <= 0 ) {
            return ResponseEntity.badRequest().build();
        }

        Counter counter = counterService.getCounter(counterId);
        if (counter != null) {
            counter.setValue(counter.getValue() + incrementCount);
            saveCounter(counter);
            return ResponseEntity.ok(counter.getValue());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

