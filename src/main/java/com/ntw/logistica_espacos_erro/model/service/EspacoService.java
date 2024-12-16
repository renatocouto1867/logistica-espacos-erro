package com.ntw.logistica_espacos_erro.model.service;

import com.ntw.logistica_espacos_erro.model.entity.Espaco;
import com.ntw.logistica_espacos_erro.model.entity.enuns.StatusEspaco;
import com.ntw.logistica_espacos_erro.model.repository.EspacoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class EspacoService {
    @Autowired
    private EspacoRepository espacoRepository;

    public Espaco criarEspaco(Espaco espaco) {
        if (espacoRepository.findByNome(espaco.getNome()).isPresent()) {
            //Aqui eu verifico se jé existe espaço com o mesmo nome
            throw new IllegalArgumentException("Espaço já cadastrado.");
        }
        espaco.setDataCadastro(LocalDate.now());
        return espacoRepository.save(espaco);
    }

    public Espaco atualizarEspaco(Long id, Espaco novoEspaco) {
        Espaco espaco = espacoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado."));
        espaco.setNome(novoEspaco.getNome());
        espaco.setDescricao(novoEspaco.getDescricao());
        return espacoRepository.save(espaco);
    }

    public void desativarEspaco(Long id) {
        Espaco espaco = espacoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado."));
        espaco.setStatus(StatusEspaco.INATIVO);
        espacoRepository.save(espaco);
    }

    public void rativarEspaco(Long id) {
        Espaco espaco = espacoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado."));
        espaco.setStatus(StatusEspaco.ATIVO);
        espacoRepository.save(espaco);
    }

    public List<Espaco> listarTodos() {
        return espacoRepository.findAll();
    }

    public Espaco buscarPorId(Long id) {
        return espacoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado."));
    }
}



