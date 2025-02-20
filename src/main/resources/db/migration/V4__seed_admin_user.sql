-- Password is: Admin123 (BCrypt encoded)
INSERT INTO users (email, password, role)
VALUES ('admin@gmail.com', '$2a$10$ye4RJ2S6hidRReINcwd6JOT5IACSVR3Gt51AzhkG.xmoHLGSdJpQ6', 'ADMIN')
ON CONFLICT (email) DO NOTHING; 