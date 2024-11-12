CREATE TABLE feedback_classification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    feedback TEXT NOT NULL,
    sentiment ENUM('POSITIVO', 'NEGATIVO', 'INCONCLUSIVO') NOT NULL
);
