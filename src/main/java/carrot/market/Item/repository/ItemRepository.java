package carrot.market.Item.repository;

import carrot.market.Item.domain.Item;
import carrot.market.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByIdAndStatus(Long itemId, Status active);
}
