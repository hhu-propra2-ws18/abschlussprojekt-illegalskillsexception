package hhu.propra2.illegalskillsexception.frently.backend.Controller.Security;

import lombok.Data;

@Data
public class ApplicationUserDTO {

    private String email;

    private String username;

    private String password;
}