package carrot.market.itemTown.domain;

import carrot.market.Item.domain.Item;
import carrot.market.model.BaseTimeEntity;
import carrot.market.model.Status;
import carrot.market.town.domain.Town;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "ITEM_TOWN_SEQ_GENERATOR",
        sequenceName = "ITEM_TOWN__SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class ItemTown extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_TOWN_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="town_id", nullable = false, foreignKey = @ForeignKey(name="fk_item_town_town"))
    private Town town;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", nullable = false, foreignKey = @ForeignKey(name="fk_item_town_item"))
    private Item item;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public ItemTown(Town town, Item item) {
        this.town = town;
        this.item = item;
        this.status = Status.ACTIVE;
    }
}
