package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Experiencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Experiencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long>, JpaSpecificationExecutor<Experiencia> {}
