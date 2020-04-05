package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.jxufe.entity.dto.NormalResult;
import cn.jxufe.service.SubscriptionService;

/**
 * @author hsw
 * @date 2020/4/5 16:00
 */
@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping(value = "/user/{userId}/fans")
    public NormalResult<?> getFans(@PathVariable Integer userId) {
        return NormalResult.successWithData(subscriptionService.findFans(userId));
    }

    @GetMapping(value = "/user/{userId}/subs")
    private NormalResult<?> getSubscribedUser(@PathVariable Integer userId) {
        return NormalResult.successWithData(subscriptionService.findSubscribedUser(userId));
    }
}
