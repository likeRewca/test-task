package com.test.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table
public class Department {
    @Id
    private Integer id;

    @Column(name = "department_name")
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "department_head", referencedColumnName = "id")
    private Lector departmentHead;

    @ManyToMany(mappedBy = "departments")
    private Set<Lector> lectors;
}
