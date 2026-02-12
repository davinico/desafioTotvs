package com.contas.pagador.infrastructure.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.contas.pagador.application.dto.ContaDTO;
import com.contas.pagador.domain.model.Conta;
import com.contas.pagador.domain.model.enums.SituacaoContaEnum;
import com.contas.pagador.domain.repository.ContaRepository;

@Repository
public class ContaRepositoryImpl implements ContaRepository {

	private final ContaJpaRepository jpaRepository;

    public ContaRepositoryImpl(ContaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Conta salvar(Conta contaNova) {
        Conta conta = this.jpaRepository.save(contaNova);
    	return conta;
    }

    @Override
    public Conta buscarPorId(Long id) {
    	return this.jpaRepository.findById(id).orElse(null);
    }

    @Override
    public Page<ContaDTO> totalAPagarPorDataEDescricao(LocalDate dataVencimento, String descricao, Pageable pageable) {
        return this.jpaRepository.totalAPagarPorDataEDescricao(SituacaoContaEnum.PENDENTE, dataVencimento, descricao, pageable);
    }

    @Override
    public BigDecimal totalPagoPorPeriodo(LocalDate dataInicio, LocalDate dataLimite) {
    	BigDecimal total = this.jpaRepository.totalPagoPorPeriodo(SituacaoContaEnum.PAGO, dataInicio, dataLimite);
        return total;
    }
}
