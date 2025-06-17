package com.course.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private Integer id;
    private String email;
    private String fullname;
    private Boolean gender;
    private Date birthday;
    private String avatar;
    private Integer role;
    private Boolean active;
    private Date registeredDate;
}
