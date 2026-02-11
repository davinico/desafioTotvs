package com.contas.pagador.application.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.contas.pagador.domain.model.Conta;
import com.contas.pagador.domain.model.enums.SituacaoContaEnum;
import com.contas.pagador.domain.repository.ContaRepository;

@Service
public class ContaService {
    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta cadastrarConta(Conta novaConta) {
    	novaConta.setSituacao(SituacaoContaEnum.PENDENTE);
    	Conta conta = this.contaRepository.salvar(novaConta);
        return conta;
    }

    public Conta atualizarConta(Long id, Conta novosDados) {
        Conta conta = this.contaRepository.buscarPorId(id);

        if(conta == null) {
        	throw new RuntimeException("Conta não encontrada!");
        }

        if(novosDados.getDataPagamento() != null) {
        	conta.setDataPagamento(novosDados.getDataPagamento());
        }

        if(novosDados.getDataVencimento() != null) {
        	conta.setDataVencimento(novosDados.getDataVencimento());
        }

        if(novosDados.getValor() != null) {
        	conta.setValor(novosDados.getValor());
        }

        if(novosDados.getDescricao() != null) {
        	conta.setDescricao(novosDados.getDescricao());
        }

        if(novosDados.getSituacao() != null) {
        	conta.setSituacao(novosDados.getSituacao());
        }

        return this.contaRepository.salvar(conta);
    }

    public void pagarConta(Long id, LocalDate dataPagamento) {
        Conta conta = this.contaRepository.buscarPorId(id);

        if(conta == null) {
        	throw new RuntimeException("Conta não encontrada!");
        }

        if(dataPagamento == null) {
        	dataPagamento = LocalDate.now();
        }

        conta.pagar(dataPagamento);
        this.contaRepository.salvar(conta);
    }

    public Conta buscarPorId(Long id) {
    	return this.contaRepository.buscarPorId(id);
    }

    public Page<Conta> totalAPagarPorDataEDescricao(LocalDate dataVencimento, String descricao, Pageable pageable) {
    	return this.contaRepository.totalAPagarPorDataEDescricao(dataVencimento, descricao, pageable);
    }

    public BigDecimal totalPago(LocalDate dataInicio, LocalDate dataFim) {
        return this.contaRepository.totalPagoPorPeriodo(dataInicio, dataFim);
    }

    public void importarCsv(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo CSV não informado");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] colunas = linha.split(";");

                Conta conta = new Conta();
                conta.setDescricao(colunas[0].trim());

                String dataString = colunas[1].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(dataString, formatter);
                conta.setDataVencimento(data);

                conta.setValor(new BigDecimal(colunas[2].trim()));

                this.cadastrarConta(conta);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar arquivo CSV", e);
        }
    }

}