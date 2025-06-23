package com.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountToChangePassword {
	private String email;
    private String oldPassword;
    private String newPassword;
}
