package carrot.market.address.domain;

import carrot.market.member.domain.Member;
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
        name = "ADDRESS_SEQ_GENERATOR",
        sequenceName = "ADDRESS_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class Address extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false, foreignKey = @ForeignKey(name="fk_address_member"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="town_id", nullable = false, foreignKey = @ForeignKey(name="fk_address_town"))
    private Town town;

    @Column(nullable = false)
    private Integer scope;

    @Column(nullable = false)
    private String mainTown;

    @Column(nullable = false)
    private Boolean certification;

    @Column(nullable = false)
    private Integer certificationUpdated;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public Address(Member member, Town town, Integer scope, String mainTown, Boolean certification) {
        this.member = member;
        this.town = town;
        this.scope = scope;
        this.mainTown = mainTown;
        this.certification = certification;
        this.certificationUpdated = 0;
        this.status = Status.ACTIVE;
    }
}
