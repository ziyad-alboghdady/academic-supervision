package org.example.academic_supervision.Repository;

import org.example.academic_supervision.Model.Supervises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisesRepository extends JpaRepository<Supervises, Long> {
}
