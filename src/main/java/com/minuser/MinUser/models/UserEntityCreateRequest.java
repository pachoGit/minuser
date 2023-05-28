package com.minuser.MinUser.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

/**
 * UserEntityCreateRequest
 */
public class UserEntityCreateRequest
{

    @NotEmpty(message = "The field name is required")
    @Size(max = 50, message = "The field name must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field name only accepts letters")
    private String name;

    @NotEmpty(message = "The field lastname is required")
    @Size(max = 50, message = "The field lastname must be less than 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field lastname only accepts letters")
    private String lastname;

    @NotEmpty(message = "The field document is required")
    @Size(max = 8, message = "The field lastname must be less than 8 digits")
    @Pattern(regexp = "^[0-9]+$", message = "The field lastname only accepts digits")
    private String document;

    @NotEmpty(message = "The field phone is required")
    @Size(max = 9, message = "The field phone must be less than 9 digits")
    @Pattern(regexp = "9[0-9]*", message = "The field phone only accepts digits and starts with 9")
    private String phone;

    @NotEmpty(message = "The field email is required")
    @Size(max = 70, message = "The field phone must be less than 70 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.]+@herlyncarlos\\.com\\.pe$", message = "The field email only accepts alphanumeric, underscore and end with @herlyncarlos.com.pe")
    private String email;

    @JsonProperty("id_job")
    @Positive(message = "The field id_job is a number required")
    private Long idJob;

    public UserEntityCreateRequest() {}

    public UserEntityCreateRequest(String name, String lastname, String document, String phone, String email, Long idJob)
    {
        this.name = name;
        this.lastname = lastname;
        this.document = document;
        this.phone = phone;
        this.email = email;
        this.idJob = idJob;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDocument() {
        return document;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getIdJob() {
        return idJob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdJob(Long idJob) {
        this.idJob = idJob;
    }
}
