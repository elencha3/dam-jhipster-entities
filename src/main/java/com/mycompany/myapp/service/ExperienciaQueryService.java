package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Experiencia;
import com.mycompany.myapp.repository.ExperienciaRepository;
import com.mycompany.myapp.repository.search.ExperienciaSearchRepository;
import com.mycompany.myapp.service.criteria.ExperienciaCriteria;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Experiencia} entities in the database.
 * The main input is a {@link ExperienciaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Experiencia} or a {@link Page} of {@link Experiencia} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ExperienciaQueryService extends QueryService<Experiencia> {

    private final Logger log = LoggerFactory.getLogger(ExperienciaQueryService.class);

    private final ExperienciaRepository experienciaRepository;

    private final ExperienciaSearchRepository experienciaSearchRepository;

    public ExperienciaQueryService(ExperienciaRepository experienciaRepository, ExperienciaSearchRepository experienciaSearchRepository) {
        this.experienciaRepository = experienciaRepository;
        this.experienciaSearchRepository = experienciaSearchRepository;
    }

    /**
     * Return a {@link List} of {@link Experiencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Experiencia> findByCriteria(ExperienciaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Experiencia> specification = createSpecification(criteria);
        return experienciaRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Experiencia} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Experiencia> findByCriteria(ExperienciaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Experiencia> specification = createSpecification(criteria);
        return experienciaRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ExperienciaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Experiencia> specification = createSpecification(criteria);
        return experienciaRepository.count(specification);
    }

    /**
     * Function to convert {@link ExperienciaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Experiencia> createSpecification(ExperienciaCriteria criteria) {
        Specification<Experiencia> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Experiencia_.id));
            }
            if (criteria.getTitulo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitulo(), Experiencia_.titulo));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), Experiencia_.descripcion));
            }
            if (criteria.getLocalizacion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocalizacion(), Experiencia_.localizacion));
            }
            if (criteria.getFecha() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFecha(), Experiencia_.fecha));
            }
        }
        return specification;
    }
}
