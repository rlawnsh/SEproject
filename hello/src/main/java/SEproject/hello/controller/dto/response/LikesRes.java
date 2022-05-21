package SEproject.hello.controller.dto.response;

import SEproject.hello.db.entity.Likes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikesRes {

    private Long mbtiTestId;

    public LikesRes(Likes likes) {
        this.mbtiTestId = likes.getMbtiTest().getId();
    }
}
