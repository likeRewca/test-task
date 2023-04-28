INSERT INTO lector (id, first_name, last_name, degree, salary) VALUES (1, 'Ivan', 'Petrenko', 'assistant', 100),
                                                           (2, 'Petro', 'Ivanov', 'professor', 400),
                                                           (3, 'Harry', 'Potter', 'associate professor', 350),
                                                           (4, 'Ron', 'Weasley', 'assistant', 150);

INSERT INTO department (id, department_name, department_head) VALUES (1, 'Physics', 1),
                                                    (2, 'Chemistry', 2);

INSERT INTO lector_department (lector_id, department_id) VALUES (1, 1),
                                                                (1, 2),
                                                                (2, 2),
                                                                (3, 1),
                                                                (4, 1);