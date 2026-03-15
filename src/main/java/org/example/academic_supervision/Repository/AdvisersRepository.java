package org.example.academic_supervision.Repository;

import org.example.academic_supervision.Model.Advisers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisersRepository extends JpaRepository<Advisers, Long> {
}