package com.contas.pagador.infrastructure.persistence;

import java.time.LocalDate;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contas.pagador.domain.model.Conta;
import com.contas.pagador.domain.model.enums.SituacaoContaEnum;

public interface ContaJpaRepository extends JpaRepository<Conta, Long> {

	@Query("SELECT c FROM Conta c WHERE c.situacao = :situacaoContaEnum AND (:dataVencimento IS NULL OR c.dataVencimento = :dataVencimento) AND "
			+ "(LOWER(c.descricao) LIKE LOWER(CONCAT('%',:descricao,'%')) OR :descricao IS NULL)")
	Page<Conta> totalAPagarPorDataEDescricao(SituacaoContaEnum situacaoContaEnum, LocalDate dataVencimento, String descricao, Pageable pageable);

	@Query("SELECT COALESCE(SUM(c.valor), 0) FROM Conta c WHERE c.situacao = :situacao AND c.dataPagamento BETWEEN :dataInicio AND :dataFim")
    BigDecimal totalPagoPorPeriodo(SituacaoContaEnum situacao, LocalDate dataInicio, LocalDate dataFim);
}