CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE post_detail (
    id INT NOT NULL PRIMARY KEY,
    detail VARCHAR(100) NOT NULL,
    CONSTRAINT post_details_id_FK FOREIGN KEY (id) REFERENCES post(id)
);

INSERT INTO post (title) VALUES
('Post 1'),
('POST 2');

INSERT INTO post_detail (id, detail) VALUES
(1, 'Post Detail 1'),
(2, 'POST Detail 2');