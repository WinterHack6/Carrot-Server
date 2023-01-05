package carrot.market.category.repository;

import carrot.market.category.domain.Category;
import carrot.market.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndStatus(Long categoryId, Status active);
}
