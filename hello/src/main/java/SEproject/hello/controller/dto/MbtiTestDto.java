package SEproject.hello.controller.dto;

import SEproject.hello.db.entity.MbtiTest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MbtiTestDto {

    private Long id;
    private String title;
    private String link;
    private String thumbnail;

    private LocalDateTime createdTime;
    private Integer views;
    private Integer likes;

    public MbtiTestDto(MbtiTest test) {
        this.id = test.getId();
        this.title = test.getTitle();
        this.link = test.getLink();
        this.thumbnail = test.getThumbnail();
        this.createdTime = test.getCreatedTime();
        this.views = test.getViews();
        this.likes = test.getLikesList().size();
    }
}
