package carrot.market.Item.dto;

import carrot.market.Item.domain.Item;
import carrot.market.category.domain.Category;
import carrot.market.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequestDto {
    private String title;

    private Long memberId;

    private Integer price;

    private String content;

    private Boolean isShare;

    private Boolean isPriceOffer;

    private Long categoryId;

    private String hopePlace;

    public static Item toEntity(ItemCreateRequestDto req, Member member, Category category) {
        return Item.builder()
                .title(req.title).
                member(member)
                .price(req.price)
                .content(req.content)
                .isShare(req.isShare)
                .isPriceOffer(req.isPriceOffer)
                .category(category)
                .hopePlace(req.hopePlace)
                .build();
    }
}
