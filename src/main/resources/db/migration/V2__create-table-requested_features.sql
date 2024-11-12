CREATE TABLE requested_features (
    id INT AUTO_INCREMENT PRIMARY KEY,
    feedback_id INT,
    code TEXT NOT NULL,
    reason TEXT NOT NULL,
    FOREIGN KEY (feedback_id) REFERENCES feedback_classifications(id) ON DELETE CASCADE
);