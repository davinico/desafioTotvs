# desafioTotvs

* CADASTRAR CONTA
  http://localhost:8080/api/contas/cadastrar
  body:  {"descricao": "Contasss de SddddddSddawdawdadawdawdawdwadKY", "valor": 200.50, "dataVencimento": "2027-03-24"}
  auth: username(admin),  password(admin123)

*FILTRAR POR ID 
 http://localhost:8080/api/contas/{id}/filtrar-por-id
 auth: username(admin),  password(admin123)

*PAGAR POR ID
 http://localhost:8080/api/contas/{id}/pagar
 body  {	"dataPagamento": "yyyy-mm-dd"}
 auth: username(admin),  password(admin123)

*ATUALIZAR DADOS
http://localhost:8080/api/contas/{id}/atualizar
body {"dataVencimento": "2027-06-21", "dataPagamento": "2026-02-09", "valor": 191.50, "descricao": "Conta de águas"}
auth: username(admin),  password(admin123)

*TOTAL PAGA POR PERIODO
http://localhost:8080/api/contas/total-pago?dataInicio={yyyy-mm-dd}&dataFim={yyyy-mm-dd}
auth: username(admin),  password(admin123)

*PENDENTES POR FILTROS
http://localhost:8080/api/contas/total-a-pagar-por-data-e-descricao
http://localhost:8080/api/contas/total-a-pagar-por-data-e-descricao?dataVencimento={yyyy-mm-dd}&descricao={descricao}
http://localhost:8080/api/contas/total-a-pagar-por-data-e-descricao?dataVencimento={yyyy-mm-dd}
http://localhost:8080/api/contas/total-a-pagar-por-data-e-descricao?descricao={descricao}
auth: username(admin),  password(admin123)

*IMPORTAR CSV
http://localhost:8080/api/contas/importar
auth: username(admin),  password(admin123)
(adicionar os CSV pelo insomnia ou postman)
CSV no formato
descrição - dd/mm/yyyy - valor



