package com.contas.pagador.domain.exceptions;

public class ContaNaoEncontradaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public ContaNaoEncontradaException() {
        super("Conta não encontrada!");
    }
}
