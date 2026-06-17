CREATE TABLE IF NOT EXISTS placavideo (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    modelo VARCHAR(255) NOT NULL,
    marca VARCHAR(255),
    preco DECIMAL(10, 2)
);

INSERT INTO placavideo (modelo, marca, preco)
SELECT 'RTX 4060', 'NVIDIA', 2500.00
WHERE NOT EXISTS (SELECT 1 FROM placavideo WHERE modelo = 'RTX 4060');

INSERT INTO placavideo (modelo, marca, preco)
SELECT 'RTX 4090', 'NVIDIA', 12000.00
WHERE NOT EXISTS (SELECT 1 FROM placavideo WHERE modelo = 'RTX 4090');

INSERT INTO placavideo (modelo, marca, preco)
SELECT 'RX 7600', 'AMD', 1900.00
WHERE NOT EXISTS (SELECT 1 FROM placavideo WHERE modelo = 'RX 7600');
