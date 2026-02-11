CREATE TABLE conta (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor NUMERIC(15,2) NOT NULL,
    descricao VARCHAR(255),
    situacao VARCHAR(20) NOT NULL
);