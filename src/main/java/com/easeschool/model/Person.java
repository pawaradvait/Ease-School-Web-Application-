package com.easeschool.model;

import com.easeschool.annotation.FiledsValaueMatch;
import com.easeschool.annotation.PasswordStrengthValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@FiledsValaueMatch.List(
        {

                @FiledsValaueMatch(
                        message = "password and confirm password not matching.",
                        field = "pwd",
                        field2 = "confirmPwd"
                ),
                @FiledsValaueMatch(
                        message = "email not matching",
                        field = "email",
                        field2 = "confirmEmail"
                )
        }
)
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int personId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @NotBlank(message="Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address" )

    @Transient
    private String confirmEmail;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordStrengthValidator
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPwd;




}
