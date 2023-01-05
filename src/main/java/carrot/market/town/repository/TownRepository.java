package carrot.market.town.repository;

import carrot.market.town.domain.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {

}
