package fcss_dev.gas_station.applitation.repository;

import fcss_dev.gas_station.applitation.model.BombaCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BombaCombustivelRepository extends JpaRepository<BombaCombustivel, Long> {
    boolean existsByNome(String nome);
}
