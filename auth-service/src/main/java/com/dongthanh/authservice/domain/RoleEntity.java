package com.dongthanh.authservice.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "role")
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "role_id")
    private String roleId;

    @NotNull
    @Column(name = "role", unique = true)
    private String role;

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private List<UserEntity> users;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    public RoleEntity() {
    }

    public RoleEntity(@NotNull String role, @NotNull String description) {
        this.role = role;
        this.description = description;
    }

    @PrePersist
    public void onCreated(){
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void onUpdated(){
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }
}
