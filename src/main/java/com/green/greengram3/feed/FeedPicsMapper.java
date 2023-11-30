package com.green.greengram3.feed;

import com.green.greengram3.feed.model.FeedInsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedPicsMapper {
    int insFeedPics(FeedInsDto dto);
    List<String> selFeedPicsAll(int ifeed);
}
