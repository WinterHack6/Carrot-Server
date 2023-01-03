package carrot.market.category.domain;

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
        name = "CATEGORY_SEQ_GENERATOR",
        sequenceName = "CATEGORY_SEQ",
        initialValue = 1,
        allocationSize = 50
)
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ_GENERATOR")
    private Long id;

    private String categoryName;

    /*
    fetch join vs inner join
    실제 서비스 메소드에서 구현 시 결정
     */
    //@OneToMany(fetch = FetchType.LAZY)
    //private List<Item> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;


    public void inActive() {
        this.status = Status.INACTIVE;
    }

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.status = Status.ACTIVE;
    }
}
