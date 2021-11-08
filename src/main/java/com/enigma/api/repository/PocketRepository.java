package com.enigma.api.repository;

import com.enigma.api.entity.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PocketRepository extends JpaRepository<Pocket, String> {

    @Query(value="select * from mst_pocket where customer_id=:customerId AND product_id=:productId", nativeQuery=true)
    Set<Pocket> getPocketsByCustomerId(@Param("customerId") String customerId, @Param("productId") Integer productId);

}
