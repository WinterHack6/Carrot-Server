package carrot.market.member.domain;

import carrot.market.model.BaseTimeEntity;
import carrot.market.model.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 사용자 Entity
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String profileImg;

    @Column(nullable = false)
    private Float mannerTemp;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public Member(String nickname, String phoneNumber, String email, String profileImg) {
        this.nickName = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.profileImg = profileImg;
        this.mannerTemp = 36.5F;
        this.status = Status.ACTIVE;
    }
}
