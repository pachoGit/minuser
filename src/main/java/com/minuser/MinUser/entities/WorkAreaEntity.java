package com.minuser.MinUser.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "work_area")
public class WorkAreaEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "workArea")
    @JsonIgnore
    private List<JobEntity> jobs;

    protected WorkAreaEntity() {}

    public WorkAreaEntity(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public List<JobEntity> getJobs()
    {
        return this.jobs;
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
        else if (!(other instanceof WorkAreaEntity)) {
            return false;
        }
        else {
            WorkAreaEntity workAreaEntity = (WorkAreaEntity) other;
            return Objects.equals(name, workAreaEntity.name);
        }
    }

    @Override
    public String toString() {
        return "WorkArea {" + "id=" + this.id + " name=" + this.name + "}";
    }
}
