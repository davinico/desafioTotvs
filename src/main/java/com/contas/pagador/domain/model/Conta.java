package com.contas.pagador.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.contas.pagador.domain.model.enums.SituacaoContaEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	private BigDecimal valor;
	private String descricao;

	@Enumerated(EnumType.STRING)
	private SituacaoContaEnum situacao;

	public Conta(Long id, LocalDate dataVencimento, LocalDate dataPagamento, BigDecimal valor, String descricao, SituacaoContaEnum situacao) {
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.descricao = descricao;
		this.situacao = situacao;
	}

	public Conta() {
	}

	public void pagar(LocalDate dataPagamento) {
		if (this.situacao == SituacaoContaEnum.PAGO) {
			throw new IllegalStateException("A conta já está paga!");
		}

		this.dataPagamento = dataPagamento;
		this.situacao = SituacaoContaEnum.PAGO;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
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