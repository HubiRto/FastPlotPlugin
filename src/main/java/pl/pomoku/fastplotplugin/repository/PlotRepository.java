package pl.pomoku.fastplotplugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pomoku.fastplotplugin.entity.Plot;

public interface PlotRepository extends JpaRepository<Plot, Long> {
    @Query(value = "select max(id) from Plot")
    int getMaxId();
    boolean existsByOwnerUUID(String ownerUUID);
    Plot findByOwnerUUID(String ownerUUID);
}
