package pl.pomoku.fastplotplugin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int topLeftX;
    private int topLeftZ;
    private int size;
    private String ownerName;
    private String ownerUUID;
    private String plotName;
}
