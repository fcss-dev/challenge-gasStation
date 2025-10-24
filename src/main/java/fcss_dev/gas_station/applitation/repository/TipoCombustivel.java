package fcss_dev.gas_station.applitation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCombustivel extends JpaRepository<TipoCombustivel, Long> {

}
