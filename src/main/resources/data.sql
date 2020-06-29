INSERT INTO address VALUES
(1, "Madrid", 22, 01103, "Miguel de Cervantes", 3),
(2, "Soria", 2, 02103, "Antonio Machado", 32),
(3, "Barcelona", 25, 04103, "Antoni Gaudí", 12),
(4, "Sevilla", 32, 05103, "Robert Redford", 84),
(5, "Palma", 29, 07003, "Antoni Maura", 34);
INSERT INTO account_holder VALUES
(1, "1995-11-04","Paco Fuertes", 1, null),
(2, "1899-12-31","Gloria Zambrano", 2, 3),
(3, "1985-07-07","Miguel Saavedra", 4, NULL),
(4, "2001-01-02","Paco Merlo", 5, NULL);
INSERT INTO account VALUES
("checking", 1, 40, 1000, "USD",null,current_timestamp(), 250, 12, 5467, "ACTIVE",  0.2, 1, null),
("checking", 2, 40, 20000, "USD",null,current_timestamp(), 250, 12, 4234, "ACTIVE",  0.2, 2, 1),
("savings", 3, 40, 2000, "USD",null,current_timestamp(), 250, null, 5942, "ACTIVE",  0.2, 3, null),
("student", 4, 40, 2000, "USD",null,current_timestamp(), null, null, 8593, "ACTIVE", 0.2, 4, null);



INSERT INTO transaction VALUES
(1, 20.3, "USD",current_timestamp(),2,1),
(2, 50, "USD",current_timestamp(),3,2),
(3, 5, "USD", current_timestamp(),4,1),
(4, 16.43, "USD", current_timestamp(),2,1);

INSERT INTO credit_card VALUES
(1, 200,"USD",null,current_timestamp(), 1000, 12,30,1, null),
(2, 0,"USD",null,current_timestamp(), 2000, 12,30,2, 1),
(3, 500,"USD",null,current_timestamp(), 10000, 12,30,3, null),
(4, 20,"USD",null,current_timestamp(), 600, 12,30,4, null);

INSERT INTO user VALUES
(1,  "$2a$10$08G9MoHmIElHEp33nrZxXehx1eJoDT5I9LMkGkTonZm3ca340s0Fe", "ADMIN","admin", null),
(2, "$2a$10$R4dULbfDzfISbmsj0HxlwOP5r7sFDTyrQ5sOpPKQgI2HKec0Blroq", "USER", "user1", 1),
(3, "$2a$10$R4dULbfDzfISbmsj0HxlwOP5r7sFDTyrQ5sOpPKQgI2HKec0Blroq", "USER", "user2", 2),
(4, "$2a$10$R4dULbfDzfISbmsj0HxlwOP5r7sFDTyrQ5sOpPKQgI2HKec0Blroq", "USER", "user3", 3),
(5, "$2a$10$R4dULbfDzfISbmsj0HxlwOP5r7sFDTyrQ5sOpPKQgI2HKec0Blroq", "USER", "user3", 4);
