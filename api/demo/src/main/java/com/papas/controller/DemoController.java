package com.papas.controller;

import com.papas.DemoUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DemoController {

    private final DemoUsecase demoUsecase;

    @GetMapping("/demo/{id}")
    public String getDemoName(@PathVariable("id") Long id) {
        return demoUsecase.getDemoNameBy(id);
    }
}
