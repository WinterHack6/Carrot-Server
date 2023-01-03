package carrot.market.town.domain;

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
        name = "TOWN_ETC_SEQ_GENERATOR",
        sequenceName = "TOWN_ETC_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class TownEtc extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOWN_ETC_SEQ_GENERATOR")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="town_id", nullable = false, foreignKey = @ForeignKey(name="fk_townEtc_town"))
    private Town town;

    @Column(nullable = false)
    private String etc;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public TownEtc(Town town, String etc) {
        this.town = town;
        this.etc = etc;
    }
}
