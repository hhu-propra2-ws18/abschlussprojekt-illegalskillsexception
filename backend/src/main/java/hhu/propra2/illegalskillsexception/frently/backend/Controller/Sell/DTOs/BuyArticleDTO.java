package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

public class BuyArticleDTO {

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private ApplicationUser owner;
    private Double price;

    @Lob
    private String description;
    private String location;
}
