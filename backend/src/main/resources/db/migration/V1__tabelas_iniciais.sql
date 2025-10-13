-- Tabela para armazenar os setores da instituição
CREATE TABLE setores (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         sigla VARCHAR(20) NOT NULL UNIQUE,
                         created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabela para gerenciar os usuários do sistema
CREATE TABLE usuarios (
                          id BIGSERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL,
                          setor_id BIGINT REFERENCES setores(id),
                          perfil VARCHAR(50) NOT NULL, -- Ex: 'ADMIN', 'GESTOR', 'USUARIO'
                          created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabela central para armazenar os metadados dos documentos
CREATE TABLE documentos (
                            id BIGSERIAL PRIMARY KEY,
                            titulo VARCHAR(255) NOT NULL,
                            descricao TEXT,
                            tipo VARCHAR(100) NOT NULL, -- Ex: 'Edital', 'Relatório', 'Ata'
                            caminho_arquivo VARCHAR(255) NOT NULL,
                            setor_id BIGINT NOT NULL REFERENCES setores(id),
                            usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
                            created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabela para controle de versionamento dos documentos
CREATE TABLE versoes_documento (
                                   id BIGSERIAL PRIMARY KEY,
                                   documento_id BIGINT NOT NULL REFERENCES documentos(id) ON DELETE CASCADE,
                                   versao BIGINT NOT NULL,
                                   caminho_arquivo VARCHAR(255) NOT NULL,
                                   motivo_alteracao TEXT,
                                   usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
                                   created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                   UNIQUE(documento_id, versao)
);

-- Tabela para logs de acesso e modificações, garantindo rastreabilidade
CREATE TABLE logs (
                      id BIGSERIAL PRIMARY KEY,
                      acao VARCHAR(255) NOT NULL, -- Ex: 'UPLOAD', 'DOWNLOAD', 'LOGIN'
                      descricao TEXT,
                      usuario_id BIGINT REFERENCES usuarios(id),
                      documento_id BIGINT REFERENCES documentos(id),
                      "timestamp" TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabela para gerenciar o workflow de aprovação de documentos
CREATE TABLE fluxo_aprovacao (
                                 id BIGSERIAL PRIMARY KEY,
                                 documento_id INTEGER NOT NULL REFERENCES documentos(id),
                                 usuario_aprovador_id INTEGER REFERENCES usuarios(id),
                                 status VARCHAR(50) NOT NULL, -- Ex: 'PENDENTE', 'APROVADO', 'REJEITADO'
                                 comentarios TEXT,
                                 created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Inserindo dados iniciais na tabela de setores conforme definido no projeto
--INSERT INTO setores (nome, sigla) VALUES
                                      --('Pró-Reitoria de Gestão de Pessoas', 'PROGEP'),
                                      --('Pró-Reitoria de Planejamento e Administração', 'PROPLAD'),
                                      --('Protocolo Geral', 'Protocolo'),
                                      --('Pró-Reitoria de Extensão e Assuntos Estudantis', 'PROEXAE'),
                                      --('Programa de Pós-Graduação', 'PPG'),
                                      --('Pró-Reitoria de Graduação', 'PROG');