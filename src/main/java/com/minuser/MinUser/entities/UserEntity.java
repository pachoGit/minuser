package com.minuser.MinUser.entities;

import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    @NotEmpty(message = "The field name is required")
    @Size(max = 50, message = "The field name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field name only accepts letters")
    private String name;

    @Column(length = 50)
    @NotEmpty(message = "The field lastname is required")
    @Size(max = 50, message = "The field lastname must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field lastname only accepts letters")
    private String lastname;

    @Column(length = 8)
    @NotEmpty(message = "The field document is required")
    @Size(max = 8, message = "The field lastname must be less than 8 digits")
    @Pattern(regexp = "^[0-9]+$", message = "The field lastname only accepts digits")
    private String document;

    @Column(length = 9)
    @NotEmpty(message = "The field phone is required")
    @Size(max = 9, message = "The field phone must be less than 9 digits")
    @Pattern(regexp = "9[0-9]*", message = "The field phone only accepts digits and starts with 9")
    private String phone;

    @Column(length = 70)
    @NotEmpty(message = "The field email is required")
    @Size(max = 70, message = "The field phone must be less than 70 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.]+@herlyncarlos\\.com\\.pe$", message = "The field email only accepts alphanumeric, underscore and end with @herlyncarlos.com.pe")
    private String email;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    // Relations

    @ManyToOne
    @JoinColumn(name = "id_job")
    @Valid
    @NotNull(message = "The field id_job is required")
    private JobEntity job;

    public UserEntity() { }

    public UserEntity(String name, String lastname, String document, String phone, String email, JobEntity job)
    {
        this.name = name;
        this.lastname = lastname;
        this.document = document;
        this.phone = phone;
        this.email = email;
        this.job = job;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public String getDocument()
    {
        return this.document;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public String getEmail()
    {
        return this.email;
    }

    public JobEntity getJob() {
        return job;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public void setDocument(String document)
    {
        this.document = document;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setJob(JobEntity job)
    {
        this.job = job;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) {
            return true;
        }
        else if (!(other instanceof JobEntity)) {
            return false;
        }
        else {
            UserEntity userEntity = (UserEntity) other;
            return Objects.equals(name, userEntity.name) &&
                Objects.equals(lastname, userEntity.lastname) &&
                Objects.equals(document, userEntity.document) &&
                Objects.equals(phone, userEntity.phone) &&
                Objects.equals(email, userEntity.email) &&
                Objects.equals(job, userEntity.job);
        }
    }

    @Override
    public String toString()
    {
        return "User { " + "id=" + this.id + " name=" + this.name + " lastname=" + this.lastname + " document=" + this.document + " phone=" + this.phone + " email=" + this.email + " " + this.job.toString();
    }
}
