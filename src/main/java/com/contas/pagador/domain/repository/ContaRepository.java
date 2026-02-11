package com.contas.pagador.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.contas.pagador.domain.model.Conta;

public interface ContaRepository {

    Conta salvar(Conta conta);
    Page<Conta> totalAPagarPorDataEDescricao(LocalDate dataVencimento, String descricao, Pageable page);
    Conta buscarPorId(Long id);
    BigDecimal totalPagoPorPeriodo(LocalDate dataInicio, LocalDate dataLimite);
}
