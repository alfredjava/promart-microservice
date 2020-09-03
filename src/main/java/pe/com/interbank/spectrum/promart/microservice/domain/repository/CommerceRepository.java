package pe.com.interbank.spectrum.promart.microservice.domain.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import pe.com.interbank.spectrum.promart.microservice.model.Commerce;

public interface CommerceRepository extends Repository<Commerce, Long>{
    @Query("select c from Commerce c where c.id = :id")
    Stream<Commerce> findByIdReturnStream(@Param("id") Long id);
}
