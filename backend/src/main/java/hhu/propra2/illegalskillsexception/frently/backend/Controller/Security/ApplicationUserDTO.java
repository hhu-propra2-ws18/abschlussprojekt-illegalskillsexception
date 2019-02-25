package hhu.propra2.illegalskillsexception.frently.backend.Controller.Security;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ApplicationUserDTO {

    private String email;

    private String username;

    private String password;
}