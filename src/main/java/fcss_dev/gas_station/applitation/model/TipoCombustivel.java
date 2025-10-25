package fcss_dev.gas_station.applitation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tipos_combustivel")
@RequiredArgsConstructor
@Getter
@Setter
public class TipoCombustivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoPorLitro;

    public TipoCombustivel(long l, String gasolina) {
    }

    @Override
    public String toString() {
        return "TipoCombustivel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", precoPorLitro=" + precoPorLitro +
                '}';
    }
}
