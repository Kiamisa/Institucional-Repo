-- Tabela para armazenar os programas da instituição
CREATE TABLE programas (
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
                          setor_id BIGINT REFERENCES programas(id),
                          perfil VARCHAR(50) NOT NULL, -- Ex: 'ADMIN', 'GESTOR', 'USUARIO','FUNCIONARIO', 'PROFESOR', 'ALUNO'
                          created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabela central para armazenar os metadados dos documentos
CREATE TABLE documentos (
                            id BIGSERIAL PRIMARY KEY,
                            titulo VARCHAR(255) NOT NULL,
                            descricao TEXT,
                            tipo VARCHAR(100) NOT NULL, -- Ex: 'EDITAL_SELECAO', 'DISSERTACAO', 'ARTIGO'
                            caminho_arquivo VARCHAR(255) NOT NULL,
                            setor_id BIGINT NOT NULL REFERENCES programas(id),
                            usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
                            created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
                            --TODO : Adicionar colunas adicionais conforme necessário
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

-- Inserindo dados iniciais na tabela de programas conforme definido no projeto
INSERT INTO programas (nome, sigla) VALUES
    ('Programa de Pós-Graduação profissional em Gestão e Regulação de Recursos hídricos', 'PROFÁGUA'),
    ('Programa de Pós-Graduação em Produção Animal', 'PPGPA'),
    ('Programa de Pós-Graduação em Processos e Tecnologias Educacionais', 'PPGPTE'),
    ('Programa de Pós-Graduação em Letras', 'PPGLETRAS'),
    ('Programa de Pós-Graduação em História', 'PPGHIST'),
    ('Programa de Pós-Graduação em Geografia, Natureza e Dinâmica do Espaço', 'PPGGEO'),
    ('Programa de Pós-Graduação em Estudos da Linguagem', 'PPGEL'),
    ('Programa de Pós-Graduação em Estudos Criminológicos', 'PPGEC'),
    ('Programa de Pós-Graduação em Ensino', 'PPGEN'),
    ('Programa de Pós-Graduação em Engenharia da Computação e Sistemas', 'PECS'),
    ('Programa de Pós-Graduação em Engenharia Aeroespacial', 'PPGAERO'),
    ('Programa de Pós-Graduação em Educação Inclusiva', 'PROFEI'),
    ('Programa de Pós-Graduação em Educação', 'PPGE'),
    ('Programa de Pós-Graduação em Ecologia e Conservação da Biodiversidade', 'PPGECB'),
    ('Programa de Pós-Graduação em Desenvolvimento Socioespacial e Regional', 'PPGDSR'),
    ('Programa de Pós-Graduação em Defesa Sanitária Animal', 'PPGPDSA'),
    ('Programa de Pós-Graduação em Ciências Agrárias', 'PPGCIAG'),
    ('Programa de Pós-Graduação em Ciência Animal', 'PPGCA'),
    ('Programa de Pós-Graduação em Cartografia Social e Política da Amazônia', 'PPGCSPA'),
    ('Programa de Pós-Graduação em Biodiversidade, Ambiente e Saúde', 'PPGBAS'),
    ('Programa de Pós-Graduação em Biodiversidade e Biotecnologia', 'BIONORTE'),
    ('Programa de Pós-Graduação Profissional em Saúde da Família', 'PROFSAÚDE'),
    ('Programa de Mestrado Profissional em Matemática em Rede Nacional', 'PROFMAT');
