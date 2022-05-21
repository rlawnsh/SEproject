package SEproject.hello.db.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Likes extends BaseEntity{

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MbtiTest mbtiTest;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
