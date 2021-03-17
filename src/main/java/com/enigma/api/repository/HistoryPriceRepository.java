package com.enigma.api.repository;

import com.enigma.api.dto.HistoryPriceDTO;
import com.enigma.api.entity.HistoryPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryPriceRepository extends JpaRepository<HistoryPrice, String> {

}
