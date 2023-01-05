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
public class ItemResponseDto {
    private Long id;

    private Member member;

    private String title;

    private Integer price;

    private String content;

    private Boolean isShare;

    private Boolean isPriceOffer;

    private Category category;

    private String hopePlace;

    private Integer viewCnt;

    public static ItemResponseDto toDto(Item item) {
        return new ItemResponseDto(
                item.getId(),
                item.getMember(),
                item.getTitle(),
                item.getPrice(),
                item.getContent(),
                item.getIsShare(),
                item.getIsPriceOffer(),
                item.getCategory(),
                item.getHopePlace(),
                item.getViewCnt()
        );
    }
}
