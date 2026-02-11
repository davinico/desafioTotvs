package com.contas.pagador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.contas.pagador.application.service.ContaService;
import com.contas.pagador.domain.model.Conta;
import com.contas.pagador.domain.model.enums.SituacaoContaEnum;
import com.contas.pagador.domain.repository.ContaRepository;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {
    @Mock
    ContaRepository contaRepository;

    @InjectMocks
    ContaService contaService;

    @Test
    void deveBuscarContaPorIdComSucesso() {
        Conta conta = new Conta();
        conta.setId(1L);

        when(contaRepository.buscarPorId(1L)).thenReturn(conta);

        Conta resultado = contaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(contaRepository).buscarPorId(1L);
    }

    @Test
    void deveCadastrarContaComSituacaoPendente() {
        Conta conta = new Conta();
        conta.setDescricao("Conta de luz");
        conta.setDataVencimento(LocalDate.now());

        when(contaRepository.salvar(any(Conta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Conta resultado = contaService.cadastrarConta(conta);

        assertNotNull(resultado);
        assertEquals(SituacaoContaEnum.PENDENTE, resultado.getSituacao());

        verify(contaRepository).salvar(conta);
    }
}
