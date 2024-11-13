CREATE TABLE feedback_classifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    feedback TEXT NOT NULL,
    sentiment ENUM('POSITIVO', 'NEGATIVO', 'INCONCLUSIVO') NOT NULL,
    custom_response TEXT NOT NULL
);
