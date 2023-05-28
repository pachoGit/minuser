package com.minuser.MinUser.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "job")
public class JobEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_work_area")
    @JsonIgnore
    private WorkAreaEntity workArea;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<UserEntity> users;

    protected JobEntity() {}

    public JobEntity(String name, WorkAreaEntity workArea)
    {
        this.name = name;
        this.workArea = workArea;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public WorkAreaEntity getWorkArea()
    {
        return this.workArea;
    }

    public List<UserEntity> getUsers()
    {
        return this.users;
    }

    public void setName(String name)
    {
        this.name = name;
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
            JobEntity jobEntity = (JobEntity) other;
            return Objects.equals(name, jobEntity.name);
        }
    }

    @Override
    public String toString()
    {
        return "Job {" + "id=" + this.id + " name=" + this.name + " " + this.workArea.toString() + "}";
    }
}
