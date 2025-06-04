package com.papas.controller;

import com.papas.Response;
import com.papas.follow.Follow;
import com.papas.follow.FollowUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowApiController {

    private final FollowUsecase followUsecase;

    @PostMapping("/{fromUser}/{toUser}/follow")
    public Response<Void> follow(@PathVariable("fromUser") Long fromUser, @PathVariable("toUser") Long toUser) {
        followUsecase.follow(fromUser, toUser);
        return Response.justOk();
    }

    @PostMapping("/{fromUser}/{toUser}/unfollow")
    public Response<Void> unfollow(@PathVariable("fromUser") Long fromUser, @PathVariable("toUser") Long toUser) {
        followUsecase.unfollowBy(fromUser, toUser);
        return Response.justOk();
    }

    @PostMapping("/{followId}/unfollow")
    public Response<Void> unfollow(@PathVariable("followId") Long followId) {
        followUsecase.unfollowBy(followId);
        return Response.justOk();
    }

    @GetMapping("/{fromUser}/following-list")
    public Response<List<Follow>> followingList(@PathVariable("fromUser") Long fromUser) {
        return Response.success(followUsecase.getFollowsByFromUserId(fromUser));
    }

    @GetMapping("/{toUser}/follower-list")
    public Response<List<Follow>> followerList(@PathVariable("toUser") Long toUser) {
        return Response.success(followUsecase.getFollowsByToUserId(toUser));
    }
}
