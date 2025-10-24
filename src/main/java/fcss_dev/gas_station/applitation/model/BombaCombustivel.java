package fcss_dev.gas_station.applitation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bombas_combustivel")
@RequiredArgsConstructor
@Getter
@Setter
public class BombaCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipo_combustivel_id", nullable = false)
    private TipoCombustivel tipoCombustivel;

    @Override
    public String toString() {
        return "BombaCombustivel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoCombustivel=" + (tipoCombustivel != null ? tipoCombustivel.getNome() : "null") +
                '}';
    }
}
