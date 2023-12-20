package com.green.greengram3.feed;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.FeedInsDto;
import com.green.greengram3.feed.model.FeedSelDto;
import com.green.greengram3.feed.model.FeedSelVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.meta.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class) // 스프링 컨테이너
@Import({FeedService.class})

class FeedServiceTest {

    @MockBean
    private FeedMapper mapper;
    @MockBean
    private FeedPicsMapper picsMapper;
    @MockBean
    private FeedFavMapper favMapper;
    @MockBean
    private FeedCommentMapper commentMapper;
    @Autowired
    private FeedService service;

    @Test
    void postFeed() {
        when(mapper.insFeed(any())).thenReturn(1);
        when(picsMapper.insFeedPics(any())).thenReturn(3);

        FeedInsDto dto = new FeedInsDto();
        dto.setIfeed(100);
        ResVo vo = service.postFeed(dto);
        assertEquals(dto.getIfeed(), vo.getResult());

        verify(mapper).insFeed(any());
        verify(picsMapper).insFeedPics(any());

        FeedInsDto dto2 = new FeedInsDto();
        dto.setIfeed(200);
        ResVo vo2 = service.postFeed(dto2);
        assertEquals(dto2.getIfeed(), vo2.getResult());


    }

    @Test
    void feedSel() {
        FeedSelVo feedSelVo1 = new FeedSelVo();
        feedSelVo1.setIfeed(1);
        feedSelVo1.setContents("1번");

        FeedSelVo feedSelVo2 = new FeedSelVo();
        feedSelVo1.setIfeed(2);
        feedSelVo1.setContents("2번");

        List<FeedSelVo> list = new ArrayList<>();
        list.add(feedSelVo1);
        list.add(feedSelVo2);

        when(mapper.selFeedAll(any())).thenReturn(list);

        List<String> feed1Pics = Arrays.stream(new String[]{"1-1","1-2"}).toList();

        List<String> feed2Pics =new ArrayList<>();
        feed2Pics.add("2-1");
        feed2Pics.add("2-2");

        List<List<String>> picsList = new ArrayList<>();
        picsList.add(feed1Pics);
        picsList.add(feed2Pics);

        List<String>[] picsArr = new ArrayList[2];
        picsArr[0] = feed1Pics;
        picsArr[1] = feed2Pics;

        when(picsMapper.selFeedPicsAll(1)).thenReturn(feed1Pics);
        when(picsMapper.selFeedPicsAll(2)).thenReturn(feed2Pics);

        FeedSelDto dto = new FeedSelDto();
        List<FeedSelVo> result = service.getFeedAll(dto);

        assertEquals(list, result);

        for (FeedSelVo vo : list) {
            assertNotNull(vo.getPics());
        }
//        for (int i = 0; i < list.size(); i++) {
//            assertEquals(list.get(i).getPics(), picsList.get(i));
//        }
//        for (int i = 0; i < list.size(); i++) {
//            assertEquals(list.get(i).getPics(),picsArr[i]);
//        }
        assertEquals(list.get(0).getPics(),feed1Pics);
        assertEquals(list.get(1).getPics(),feed2Pics);
    }

    @Test
    void toggleFeedFav() {
    }

    @Test
    void delFeed() {
    }
}