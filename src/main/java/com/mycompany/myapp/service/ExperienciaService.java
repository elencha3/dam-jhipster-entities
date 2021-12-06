package com.mycompany.myapp.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.mycompany.myapp.domain.Experiencia;
import com.mycompany.myapp.repository.ExperienciaRepository;
import com.mycompany.myapp.repository.search.ExperienciaSearchRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Experiencia}.
 */
@Service
@Transactional
public class ExperienciaService {

    private final Logger log = LoggerFactory.getLogger(ExperienciaService.class);

    private final ExperienciaRepository experienciaRepository;

    private final ExperienciaSearchRepository experienciaSearchRepository;

    public ExperienciaService(ExperienciaRepository experienciaRepository, ExperienciaSearchRepository experienciaSearchRepository) {
        this.experienciaRepository = experienciaRepository;
        this.experienciaSearchRepository = experienciaSearchRepository;
    }

    /**
     * Save a experiencia.
     *
     * @param experiencia the entity to save.
     * @return the persisted entity.
     */
    public Experiencia save(Experiencia experiencia) {
        log.debug("Request to save Experiencia : {}", experiencia);
        Experiencia result = experienciaRepository.save(experiencia);
        experienciaSearchRepository.save(result);
        return result;
    }

    /**
     * Partially update a experiencia.
     *
     * @param experiencia the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Experiencia> partialUpdate(Experiencia experiencia) {
        log.debug("Request to partially update Experiencia : {}", experiencia);

        return experienciaRepository
            .findById(experiencia.getId())
            .map(existingExperiencia -> {
                if (experiencia.getTitulo() != null) {
                    existingExperiencia.setTitulo(experiencia.getTitulo());
                }
                if (experiencia.getDescripcion() != null) {
                    existingExperiencia.setDescripcion(experiencia.getDescripcion());
                }
                if (experiencia.getLocalizacion() != null) {
                    existingExperiencia.setLocalizacion(experiencia.getLocalizacion());
                }
                if (experiencia.getFecha() != null) {
                    existingExperiencia.setFecha(experiencia.getFecha());
                }

                return existingExperiencia;
            })
            .map(experienciaRepository::save)
            .map(savedExperiencia -> {
                experienciaSearchRepository.save(savedExperiencia);

                return savedExperiencia;
            });
    }

    /**
     * Get all the experiencias.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Experiencia> findAll(Pageable pageable) {
        log.debug("Request to get all Experiencias");
        return experienciaRepository.findAll(pageable);
    }

    /**
     * Get one experiencia by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Experiencia> findOne(Long id) {
        log.debug("Request to get Experiencia : {}", id);
        return experienciaRepository.findById(id);
    }

    /**
     * Delete the experiencia by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Experiencia : {}", id);
        experienciaRepository.deleteById(id);
        experienciaSearchRepository.deleteById(id);
    }

    /**
     * Search for the experiencia corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Experiencia> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Experiencias for query {}", query);
        return experienciaSearchRepository.search(query, pageable);
    }
}
