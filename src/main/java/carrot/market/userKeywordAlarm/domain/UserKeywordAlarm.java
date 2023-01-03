package carrot.market.userKeywordAlarm.domain;

import carrot.market.member.domain.Member;
import carrot.market.model.BaseTimeEntity;
import carrot.market.model.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "ALARM_SEQ_GENERATOR",
        sequenceName = "ALARM_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class UserKeywordAlarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALARM_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false, foreignKey = @ForeignKey(name="fk_alarm_member"))
    private Member member;

    @Column(nullable = false)
    private String keyword;

    private String content;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public UserKeywordAlarm(Member member, String keyword, String content) {
        this.member = member;
        this.keyword = keyword;
        this.content = content;
        this.status = Status.ACTIVE;
    }
}
