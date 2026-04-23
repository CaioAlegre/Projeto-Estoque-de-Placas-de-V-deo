-- Remove a tabela antiga para evitar conflitos (opcional)
DROP TABLE IF EXISTS placavideo;

-- Cria a tabela do zero com o comando correto
CREATE TABLE placavideo (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    modelo VARCHAR(255) NOT NULL,
    marca VARCHAR(255),
    preco DECIMAL(10, 2)
);
-- [C]REATE: Inserindo dados iniciais
INSERT INTO placavideo (modelo, marca, preco) VALUES 
('RTX 4060', 'NVIDIA', 2500.00),
('RTX 4090', 'NVIDIA', 12000.00),
('RX 7600', 'AMD', 1900.00);

-- [R]EAD: Consultando todos os dados cadastrados
SELECT * FROM placavideo;

-- [U]PDATE: Atualizando o preço de um modelo específico
-- (Usamos o nome do modelo aqui, mas no dia a dia usa-se o ID)
UPDATE placavideo 
SET preco = 2350.00 
WHERE modelo = 'RTX 4060';

-- [D]ELETE: Removendo um registro
DELETE FROM placavideo 
WHERE modelo = 'RX 7600';

-- CONSULTA FINAL: Verificando como a tabela ficou após as alterações
SELECT * FROM placavideo;