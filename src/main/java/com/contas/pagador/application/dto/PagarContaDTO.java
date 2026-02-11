package com.contas.pagador.application.dto;

import java.time.LocalDate;

public class PagarContaDTO {

    private LocalDate dataPagamento;

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}