package carrot.market.Item.dto;

import carrot.market.Item.domain.Item;
import carrot.market.category.domain.Category;
import carrot.market.member.domain.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemCreateRequestDto {
    @ApiModelProperty(value = "상품 게시글 제목")
    private String title;

    @ApiModelProperty(value = "유저 id")
    private Long memberId;
    @ApiModelProperty(value = "상품 가격")
    private Integer price;

    @ApiModelProperty(value = "상품 게시글 내용")
    private String content;

    @ApiModelProperty(value = "나눔 여부")
    private Boolean isShare;

    @ApiModelProperty(value = "가격 제안 여부")
    private Boolean isPriceOffer;

    @ApiModelProperty(value = "카테고리 id")
    private Long categoryId;

    @ApiModelProperty(value = "거래 희망 장소")
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
