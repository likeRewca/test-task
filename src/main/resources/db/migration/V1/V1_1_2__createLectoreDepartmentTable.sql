CREATE TABLE lector_department (
lector_id INT NOT NULL,
department_id INT NOT NULL,
PRIMARY KEY(lector_id, department_id),
FOREIGN KEY (lector_id) REFERENCES LECTOR (id),
FOREIGN KEY (department_id) REFERENCES DEPARTMENT (id)
);