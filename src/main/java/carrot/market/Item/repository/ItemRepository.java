package carrot.market.Item.repository;

import carrot.market.Item.domain.Item;
import carrot.market.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i join fetch i.category c join fetch i.member m where i.id = :itemId and i.status = :active")
    Optional<Item> findByIdAndStatus(@Param("itemId") Long itemId, @Param("active") Status active);
}
