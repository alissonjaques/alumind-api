CREATE TABLE request_feature (
    id INT AUTO_INCREMENT PRIMARY KEY,
    feedback_id INT,
    code TEXT NOT NULL,
    reason TEXT NOT NULL,
    FOREIGN KEY (feedback_id) REFERENCES feedback_classification(id) ON DELETE CASCADE
);