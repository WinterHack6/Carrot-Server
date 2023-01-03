package carrot.market.userItemLike.domain;

import carrot.market.Item.domain.Item;
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
        name = "LIKE_SEQ_GENERATOR",
        sequenceName = "LIKE_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class UserItemLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIKE_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false, foreignKey = @ForeignKey(name="fk_like_member"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", nullable = false, foreignKey = @ForeignKey(name="fk_like_item"))
    private Item item;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public UserItemLike(Member member, Item item) {
        this.member = member;
        this.item = item;
        this.status = Status.ACTIVE;
    }
}
