package com.papas.controller;

import com.papas.follow.FollowUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class FollowApiController {

    private final FollowUsecase followUsecase;
    
}
