package SEproject.hello.controller.dto.response;

import SEproject.hello.common.model.BaseResponse;
import SEproject.hello.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoRes extends BaseResponse {

    private String mbti;
    private String name;
    private String level;
    private Integer testSize;

    public MemberInfoRes(String msg, Integer status, Member member, Integer testSize) {
        super(msg, status);
        this.mbti = member.getMbti();
        this.name = member.getName();
        this.level = member.getMbtiLevel().getLevel();
        this.testSize = testSize;
    }
}
