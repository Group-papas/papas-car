package com.papas.controller;

import com.papas.Response;
import com.papas.follow.Follow;
import com.papas.car.FollowUsecase;
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

    /**
     * 팔로우하는 메서드
     */
    @PostMapping("/{fromUser}/{toUser}/follow")
    public Response<Void> follow(@PathVariable("fromUser") Long fromUser, @PathVariable("toUser") Long toUser) {
        followUsecase.follow(fromUser, toUser);
        return Response.justOk();
    }

    /**
     * 언팔로우 메서드1
     * fromUser 와 toUser 로 언팔로우함.
     */
    @PostMapping("/{fromUser}/{toUser}/unfollow")
    public Response<Void> unfollow(@PathVariable("fromUser") Long fromUser, @PathVariable("toUser") Long toUser) {
        followUsecase.unfollowBy(fromUser, toUser);
        return Response.justOk();
    }

    /**
     * 언팔로우 메서드2,
     * 팔로우 고유 아이디로 언팔로우함
     */
    @PostMapping("/{followId}/unfollow")
    public Response<Void> unfollow(@PathVariable("followId") Long followId) {
        followUsecase.unfollowBy(followId);
        return Response.justOk();
    }

    /**
     * 특정 사용자가 팔로우하는 사용자를 조회하는 메서드
     */
    @GetMapping("/{fromUser}/following-list")
    public Response<List<Follow>> followingList(@PathVariable("fromUser") Long fromUser) {
        return Response.success(followUsecase.getFollowsByFromUserId(fromUser));
    }

    /**
     * 특정 사용자를 팔로우하는 팔로워를 조회하는 메서드
     */
    @GetMapping("/{toUser}/follower-list")
    public Response<List<Follow>> followerList(@PathVariable("toUser") Long toUser) {
        return Response.success(followUsecase.getFollowsByToUserId(toUser));
    }
}
