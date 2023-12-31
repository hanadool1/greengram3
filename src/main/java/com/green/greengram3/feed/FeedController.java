package com.green.greengram3.feed;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService service;

    @PostMapping
    public ResVo postFeed(@RequestBody FeedInsDto dto) {
        return service.postFeed(dto);
    }

    @GetMapping
    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
        log.info("dto : {}",dto);
        return service.getFeedAll(dto);
    }

    @GetMapping("/fav")
    public ResVo toggleFeedFav(FeedFavDto dto){
        return service.toggleFeedFav(dto);
    }

    // ifeed, iuser
    @DeleteMapping
    public ResVo delFeed(FeedDelDto dto) {
        return service.delFeed(dto);
    }

}
