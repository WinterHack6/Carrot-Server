package carrot.market.Item.domain;

import carrot.market.category.domain.Category;
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
        name = "ITEM_SEQ_GENERATOR",
        sequenceName = "ITEM_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false, foreignKey = @ForeignKey(name="fk_item_member"))
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isShare;

    @Column(nullable = false)
    private Boolean isPriceOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable = false, foreignKey = @ForeignKey(name="fk_item_category"))
    private Category category;

    @Column(nullable = false)
    private String hopePlace;

    @Column(nullable = false)
    private Integer viewCnt;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public Item(Member member, String title, Integer price, String content, Boolean isShare, Boolean isPriceOffer, Category category, String hopePlace) {
        this.member = member;
        this.title = title;
        this.price = price;
        this.content = content;
        this.isShare = isShare;
        this.isPriceOffer = isPriceOffer;
        this.category = category;
        this.hopePlace = hopePlace;
        this.viewCnt = 0;
        this.status = Status.ACTIVE;
    }
}
