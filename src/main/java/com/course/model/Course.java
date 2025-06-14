package com.course.model;

import java.util.Date;
import java.util.List;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Courses")
public class Course {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(columnDefinition = "nvarchar(250)", unique = true)
    private String title;
	
	@Column(unique = true)
    private String slug;
	
    private String image;

    @Column(columnDefinition = "nvarchar(max)")
    private String description;

    private Integer price;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    private Boolean status;
    
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
    
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Report> reports;
    
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Payment> payments;
    
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Favorite> favorites;
    
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Comment> comments;
    
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;
    
    @ManyToOne
    @JoinColumn(name = "courseTypeId", nullable = false)
    private CourseType courseType;
    
    @ManyToOne
    @JoinColumn(name = "commissionId", nullable = false)
    private Commission commission;

}
