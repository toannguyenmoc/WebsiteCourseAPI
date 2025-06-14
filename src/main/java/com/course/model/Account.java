package com.course.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Accounts")
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(unique = true)
    private String email;
	
    private String password;
    
    @Column(columnDefinition = "nvarchar(250)")
    private String fullname;
    
    private Boolean gender;
    
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column(columnDefinition = "nvarchar(250)")
    private String avatar;
    private Integer role;
    private Boolean active;
    
    @Temporal(TemporalType.DATE)
    private Date registeredDate = new Date();
    
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Course> courses;
    
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Payment> payments;
    
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Favorite> favorites;
    
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Comment> comments;
    
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Report> reports;
    
}
