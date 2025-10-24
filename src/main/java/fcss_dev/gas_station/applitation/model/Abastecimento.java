package fcss_dev.gas_station.applitation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "abastecimento")
@RequiredArgsConstructor
@Getter
@Setter
public class Abastecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bomba_id", nullable = false)
    private BombaCombustivel bomba;

    @Column(nullable = false)
    private LocalDateTime dataAbastecimento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal litrosAbastecidos;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Override
    public String toString() {
        return "Abastecimento{" +
                "id=" + id +
                ", bomba=" + (bomba != null ? bomba.getNome() : "null") +
                ", dataAbastecimento=" + dataAbastecimento +
                ", litrosAbastecidos=" + litrosAbastecidos +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
