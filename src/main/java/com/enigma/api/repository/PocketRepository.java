package com.enigma.api.repository;

import com.enigma.api.entity.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PocketRepository extends JpaRepository<Pocket, String> {
}
