package fcss_dev.gas_station.applitation.repository;

import fcss_dev.gas_station.applitation.model.TipoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCombustivelRepository extends JpaRepository<TipoCombustivel, Long> {
}
