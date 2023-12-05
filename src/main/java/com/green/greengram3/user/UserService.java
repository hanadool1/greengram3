package com.green.greengram3.user;

import com.green.greengram3.common.Const;
import com.green.greengram3.common.ResVo;
import com.green.greengram3.user.model.*;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public ResVo signup(UserSignupDto dto) {
        // 비밀번호 암호화
        String encodingpw = BCrypt.hashpw(dto.getUpw(),BCrypt.gensalt());
        // 암호화된 비밀번호를 encodingpw에 저장
        UserSignupProcDto pDto = UserSignupProcDto.builder()
                .uid(dto.getUid())
                // dto(입력받은)에 있는 uid를 pDto의 uid에 넣는다
                .upw(encodingpw)
                // 암호화된 비밀번호를 pDto의 upw에 넣는다
                .nm(dto.getNm())
                // dto(입력받은)에 있는 nm를 pDto의 nm에 넣는다
                .pic(dto.getPic())
                // dto(입력받은)에 있는 pic를 pDto의 pic에 넣는다
                .build();
        int affectedRowCnt = mapper.insUser(pDto);
        // mapper의 메서드를 통해 회원가입
        if (affectedRowCnt == 0) {
            return new ResVo(0);
        }
        return new ResVo(pDto.getIuser()); // 회원가입한 iuser pk값이 리턴
    }

    // result 1:성공, 2:아이디없음, 3:비밀번호틀림
    public UserSigninVo signin(UserSigninDto dto) {
        UserSigninProcVo pVo = mapper.selUserById(dto.getUid());
        // mapper의 메서드를 통해 pVo에 iuser,nm,pic,upw 저장
        UserSigninVo vo = new UserSigninVo();
        // 프론트에 보낼 정보를 Vo 객체 생성
        if (pVo == null) {
            // DB에 저장된 아이디가 없을 때
            vo.setResult(Const.LOGIN_NO_UID);
            // 2:아이디없음
            return vo;
        } else if (!BCrypt.checkpw(dto.getUpw(),pVo.getUpw())) {
            // 입력받은 비번과 DB에 저장된 비밀번호가 일치하지 않을 때
            // 입력받은 비번, DB에 저장된 암호화비번 순서로 작성
            vo.setResult(Const.LOGIN_DIFF_UPW);
            // 3:비밀번호틀림
            return vo;
        }
        vo.setResult(Const.SUCCESS);
        // 1:성공
        vo.setIuser(pVo.getIuser());
        // pVo에 저장된 iuser(getter)를 vo에 있는 iuser에 넣는다(setter).
        vo.setNm(pVo.getNm());
        // pVo에 저장된 nm(getter)를 vo에 있는 nm에 넣는다(setter).
        vo.setPic(pVo.getPic());
        // pVo에 저장된 pic(getter)를 vo에 있는 pic에 넣는다(setter).
        return vo;
    }

    public ResVo toggleFollow(UserFollowDto dto) {
        int delfollow = mapper.delFollow(dto);
        if (delfollow == 1) {
            return new ResVo(0);
        }
        return new ResVo(mapper.insFollow(dto));
    }
}
