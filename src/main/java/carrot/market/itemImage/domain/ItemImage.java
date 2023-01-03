package carrot.market.itemImage.domain;

import carrot.market.Item.domain.Item;
import carrot.market.model.BaseTimeEntity;
import carrot.market.model.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "ITEM_IMAGE_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class ItemImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_IMAGE_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", nullable = false, foreignKey = @ForeignKey(name="fk_itemImg_item"))
    private Item item;

    private String imgUrl;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public ItemImage(Item item, String imgUrl) {
        this.item = item;
        this.imgUrl = imgUrl;
        this.status = Status.ACTIVE;
    }
}
