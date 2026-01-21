package org.varshitrajput.healer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.varshitrajput.healer.model.HealerData;

public interface HealerDataRepository extends JpaRepository<HealerData, Integer> {
}
