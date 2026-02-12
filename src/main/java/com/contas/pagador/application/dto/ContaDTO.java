package com.contas.pagador.application.dto;

import java.time.LocalDate;

import com.contas.pagador.domain.model.Conta;
import com.contas.pagador.domain.model.enums.SituacaoContaEnum;

public class ContaDTO {
	private Long id;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private String descricao;
	private SituacaoContaEnum situacao;

	public ContaDTO(Long id, LocalDate dataVencimento, LocalDate dataPagamento, String descricao, SituacaoContaEnum situacao) {
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.descricao = descricao;
		this.situacao = situacao;
	}

	public static ContaDTO parseContaDTO(Conta conta) {
		ContaDTO contaDTO = new ContaDTO(conta.getId(), conta.getDataVencimento(), conta.getDataPagamento(), conta.getDescricao(), conta.getSituacao());

		return contaDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SituacaoContaEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoContaEnum situacao) {
		this.situacao = situacao;
	}
}
