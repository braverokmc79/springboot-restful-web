CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255),
    birthDate timestamp,
    role VARCHAR(50)
);


CREATE TABLE todos (
    todo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    description VARCHAR(255) NOT NULL,
    target_date DATE,
    done BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
