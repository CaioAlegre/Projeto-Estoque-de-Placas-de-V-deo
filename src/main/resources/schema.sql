-- Remove a tabela antiga para evitar conflitos (opcional)
DROP TABLE IF EXISTS placavideo;

-- Cria a tabela do zero com o comando correto
CREATE TABLE placavideo (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    modelo VARCHAR(255) NOT NULL,
    marca VARCHAR(255),
    preco DECIMAL(10, 2)
);