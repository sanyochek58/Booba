package com.example.auth.Entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Setter
@Getter
public class UserDTO {
    private String email;
    private String password;
    private String username;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthOfDate;
}
