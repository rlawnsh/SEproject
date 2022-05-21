package SEproject.hello.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity{

    @NotNull
    @Column(unique = true)
    private String memberId;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String mbti;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member")
    private List<Likes> likesList;

    public void updatePassword(String password) {
        this.password = password;
    }
}