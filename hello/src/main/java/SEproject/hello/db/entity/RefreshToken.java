package SEproject.hello.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class RefreshToken extends BaseEntity{

    private String refreshKey;
    private String refreshValue;

    public RefreshToken updateValue(String token) {
        this.refreshValue = token;
        return this;
    }

    @Builder
    public RefreshToken(String key, String value) {
        this.refreshKey = key;
        this.refreshValue = value;
    }
}
