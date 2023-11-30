package com.green.greengram3.feed;

import com.green.greengram3.common.ResVo;
import com.green.greengram3.feed.model.FeedInsDto;
import com.green.greengram3.feed.model.FeedSelDto;
import com.green.greengram3.feed.model.FeedSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;

    public ResVo postFeed(FeedInsDto dto) {
        int affectedFeed = mapper.insFeed(dto);
        // mapper의 메소드를 통해 피드 작성
        int affectedPic = picsMapper.insFeedPics(dto);
        // mapper의 메소드를 통해 피드사진 작성
        return new ResVo(dto.getIfeed());
        // dto의 pk인 ifeed값 리턴
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
        // FeedSelDto에 rowCount는 20 고정, startIdx는 생성자를 통해 미리 계산
        List<FeedSelVo> feedList = mapper.selFeedAll(dto);
        for (FeedSelVo vo : feedList) {
            // feedList 반복
            List<String> picList = picsMapper.selFeedPicsAll(vo.getIfeed());
            // selFeedPicsAll 실행(한 페이지에 있는 피드들의 pk값을 파라미터로 가져온다)
            vo.setPics(picList);
            // vo에 있는 pics에 picList를 (setter를 이용해서) 넣는다
        }
        return feedList;
    }
}
