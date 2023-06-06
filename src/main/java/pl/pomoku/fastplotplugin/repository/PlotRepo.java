package pl.pomoku.fastplotplugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.fastplotplugin.entity.Plot;

public interface PlotRepo extends JpaRepository<Plot, Long> {
}
