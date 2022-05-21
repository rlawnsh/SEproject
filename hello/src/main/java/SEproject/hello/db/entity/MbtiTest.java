package SEproject.hello.db.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class MbtiTest extends BaseEntity{

    private String title;
    private String link;
    private String thumbnail;

    @CreationTimestamp
    private LocalDateTime createdTime;
    private Integer views;

    @OneToMany(mappedBy = "mbtiTest")
    private List<Likes> likesList;

    public void upMbtiTestViews() {
        this.views++;
    }
}
