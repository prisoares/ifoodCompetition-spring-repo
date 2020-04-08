CREATE TABLE ifoodCompetition.prato (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    prato VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    valor VARCHAR(100) NOT NULL,
    restaurant_id INTEGER NOT NULL
);