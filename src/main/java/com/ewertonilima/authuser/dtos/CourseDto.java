package com.ewertonilima.authuser.dtos;

import com.ewertonilima.authuser.enums.CourseLevel;
import com.ewertonilima.authuser.enums.CourseStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {

    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private UUID userInstructor;
    private CourseLevel courseLevel;
}
