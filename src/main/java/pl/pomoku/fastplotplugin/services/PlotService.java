package pl.pomoku.fastplotplugin.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.fastplotplugin.entity.Plot;
import pl.pomoku.fastplotplugin.repository.PlotRepository;

import java.util.List;

@Service("plotService")
@RequiredArgsConstructor
public class PlotService {
    private final PlotRepository plotRepository;

    public int getMaxId(){
        return plotRepository.getMaxId();
    }
    public void addPlot(Plot plot) {
        plotRepository.save(plot);
    }
    public void removePlot(Plot plot) {
        plotRepository.delete(plot);
    }

    public void update(Plot plot) {
        plotRepository.save(plot);
    }
    public boolean existsByOwnerUUID(String ownerUUID) {
        return plotRepository.existsByOwnerUUID(ownerUUID);
    }
    public Plot findByOwnerUUID(String ownerUUID) {
        return plotRepository.findByOwnerUUID(ownerUUID);
    }

    public List<Plot> findAll() {
        return plotRepository.findAll();
    }
}
