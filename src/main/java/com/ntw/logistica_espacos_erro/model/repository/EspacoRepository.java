package com.ntw.logistica_espacos_erro.model.repository;

import com.ntw.logistica_espacos_erro.model.entity.Espaco;
import com.ntw.logistica_espacos_erro.model.entity.enuns.StatusEspaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Long> {
    Optional<Espaco> findByNome(String nome);
    List<Espaco> findByStatus(StatusEspaco status);
}

