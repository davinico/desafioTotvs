package com.contas.pagador.domain.exceptions;

public class ContaJaPagaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public ContaJaPagaException() {
        super("Conta já está paga!");
    }
}
