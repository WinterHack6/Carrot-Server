package carrot.market.Item.service;

import carrot.market.Item.domain.Item;
import carrot.market.Item.dto.ItemCreateRequestDto;
import carrot.market.Item.dto.ItemResponseDto;
import carrot.market.Item.exception.ItemNotFoundException;
import carrot.market.Item.repository.ItemRepository;
import carrot.market.category.exception.CategoryNotFoundException;
import carrot.market.category.repository.CategoryRepository;
import carrot.market.member.exception.MemberNotFoundException;
import carrot.market.member.repository.MemberRepository;
import carrot.market.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 상품 게시글 생성 메소드
     *
     * 추가할 것 : 상품이 추가될 때, 알림 서비스 호출
     */
    @Transactional
    public void create(ItemCreateRequestDto req) {
        Item item = itemRepository.save(ItemCreateRequestDto.toEntity(req,
                memberRepository.findByIdAndStatus(req.getMemberId(), Status.ACTIVE).orElseThrow(MemberNotFoundException::new),
                categoryRepository.findByIdAndStatus(req.getCategoryId(), Status.ACTIVE).orElseThrow(CategoryNotFoundException::new))
        );
    }

    /**
     * 상품 게시글 읽기 메소드
     * dto에 좋아요 수 추가
     */
    public ItemResponseDto read(Long id) {
        Item item = itemRepository.findByIdAndStatus(id, Status.ACTIVE).orElseThrow(ItemNotFoundException::new);

        return ItemResponseDto.toDto(item);
    }
}
