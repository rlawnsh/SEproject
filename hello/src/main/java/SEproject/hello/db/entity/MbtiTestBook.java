package SEproject.hello.db.entity;

import SEproject.hello.controller.dto.request.PostTestReq;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
public class MbtiTestBook extends BaseEntity{

    private String testResult;
    private String testUrl;
    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;


    public MbtiTestBook(Member member, PostTestReq postTestReq) {
        this.testResult = postTestReq.getTestResult();
        this.testUrl = postTestReq.getTestUrl();
        this.thumbnailUrl = postTestReq.getThumbnailUrl();
        this.member = member;
    }
}
