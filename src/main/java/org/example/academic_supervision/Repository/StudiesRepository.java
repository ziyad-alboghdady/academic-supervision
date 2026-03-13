package org.example.academic_supervision.Repository;

import org.example.academic_supervision.Model.Studies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiesRepository extends JpaRepository<Studies, Long> {
}