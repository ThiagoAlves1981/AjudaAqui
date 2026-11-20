-- Adiciona coluna de estoque no Produto (caso ainda não exista no V1)
ALTER TABLE tb_produto ADD COLUMN quantidade_estoque INT NOT NULL DEFAULT 0;

-- Adiciona coluna de valor total no Pedido
ALTER TABLE tb_pedido ADD COLUMN valor_total DECIMAL(10, 2) DEFAULT 0.00;