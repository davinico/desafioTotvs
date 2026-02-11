package com.contas.pagador.application.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.contas.pagador.application.dto.PagarContaDTO;
import com.contas.pagador.application.service.ContaService;
import com.contas.pagador.domain.model.Conta;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

	private final ContaService contaService;

	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Conta> cadastrar(@RequestBody @Valid Conta conta) {
		Conta contaNova = this.contaService.cadastrarConta(conta);
		return ResponseEntity.status(HttpStatus.CREATED).body(contaNova);
	}

	@PostMapping(value = "/importar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Void> importar(MultipartHttpServletRequest request) {
		Iterator<MultipartFile> files = request.getFileMap().values().iterator();

		if (!files.hasNext()) {
			return ResponseEntity.badRequest().build();
		}

		MultipartFile file = files.next();
		this.contaService.importarCsv(file);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}/atualizar")
	public ResponseEntity<Conta> atualizar(@PathVariable Long id, @RequestBody @Valid Conta conta) {
		Conta atualizada = this.contaService.atualizarConta(id, conta);
		return ResponseEntity.ok(atualizada);
	}

	@PatchMapping("/{id}/pagar")
	public ResponseEntity<Void> pagar(@PathVariable Long id, @RequestBody @Valid PagarContaDTO request) {
		this.contaService.pagarConta(id, request.getDataPagamento());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/total-a-pagar-por-data-e-descricao")
	public ResponseEntity<Page<Conta>> totalAPagarPorDataEDescricao(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataVencimento, @RequestParam(required = false) String descricao, Pageable pageable) {
		return ResponseEntity.ok(this.contaService.totalAPagarPorDataEDescricao(dataVencimento, descricao, pageable));
	}

	@GetMapping("/{id}/filtrar-por-id")
	public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(this.contaService.buscarPorId(id));
	}

	@GetMapping("/total-pago")
	public ResponseEntity<BigDecimal> totalPago(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
		return ResponseEntity.ok(this.contaService.totalPago(dataInicio, dataFim));
	}

}