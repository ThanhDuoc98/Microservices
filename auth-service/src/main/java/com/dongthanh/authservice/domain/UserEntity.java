package com.dongthanh.authservice.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "user_id")
    private String userId;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private List<RoleEntity> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private UserEntity createdBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by", referencedColumnName = "user_id")
    private UserEntity modifiedBy;

    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    public UserEntity(@NotNull String username, @NotNull String password, @NotNull boolean activated) {
        this.username = username;
        this.password = password;
        this.activated = activated;
    }

    public UserEntity() {
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
