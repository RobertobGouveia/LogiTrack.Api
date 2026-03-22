CREATE TABLE IF NOT EXISTS veiculos (
    id BIGSERIAL PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    modelo VARCHAR(255),
    tipo VARCHAR(20) NOT NULL,
    ano INTEGER,
    CONSTRAINT uk_veiculos_placa UNIQUE (placa),
    CONSTRAINT ck_veiculos_tipo CHECK (tipo IN ('LEVE', 'PESADO'))
);
CREATE TABLE IF NOT EXISTS manutencoes (
    id BIGSERIAL PRIMARY KEY,
    veiculo_id BIGINT NOT NULL,
    data_inicio DATE,
    data_finalizacao DATE,
    tipo_servico VARCHAR(255),
    custo_estimado NUMERIC(12, 2),
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_manutencoes_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculos (id),
    CONSTRAINT ck_manutencoes_status CHECK (status IN ('PENDENTE', 'CONCLUIDA'))
);
CREATE INDEX IF NOT EXISTS idx_manutencoes_veiculo_id ON manutencoes (veiculo_id);
CREATE INDEX IF NOT EXISTS idx_manutencoes_data_inicio ON manutencoes (data_inicio);
CREATE TABLE IF NOT EXISTS viagens (
    id BIGSERIAL PRIMARY KEY,
    veiculo_id BIGINT,
    data_saida TIMESTAMP,
    data_chegada TIMESTAMP,
    origem VARCHAR(255),
    destino VARCHAR(255),
    km_percorrida NUMERIC(12, 2),
    CONSTRAINT fk_viagens_veiculo FOREIGN KEY (veiculo_id) REFERENCES veiculos (id)
);
CREATE INDEX IF NOT EXISTS idx_viagens_veiculo_id ON viagens (veiculo_id);
CREATE INDEX IF NOT EXISTS idx_viagens_data_saida ON viagens (data_saida);
