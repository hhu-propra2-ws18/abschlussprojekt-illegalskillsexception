package hhu.propra2.illegalskillsexception.frently.backend.Controller.Sell.DTOs;

import lombok.Data;

@Data
public class BuyArticleUpdate {
    private long buyArticleId;
    private String title;
    private String description;
    private double price;
    private String location;
}
