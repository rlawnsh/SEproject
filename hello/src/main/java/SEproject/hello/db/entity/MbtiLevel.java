package SEproject.hello.db.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
public class MbtiLevel extends BaseEntity{

    private String level;

    @OneToOne(mappedBy = "mbtiLevel")
    private Member member;
}
