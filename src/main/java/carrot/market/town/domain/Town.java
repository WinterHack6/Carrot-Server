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
        name = "TOWN_SEQ_GENERATOR",
        sequenceName = "TOWN_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class Town extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOWN_SEQ_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String townName;

    @Column(nullable = false)
    private Float lat;

    @Column(nullable = false)
    private Float lng;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public Town(String city, String district, String townName, Float lat, Float lng) {
        this.city = city;
        this.district = district;
        this.townName = townName;
        this.lat = lat;
        this.lng = lng;
        this.status = Status.ACTIVE;
    }
}
