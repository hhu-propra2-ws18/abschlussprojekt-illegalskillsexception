package hhu.propra2.illegalskillsexception.frently.backend.ProPay.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.ApplicationUser;

public interface IProPayApplicationUserService {
    ApplicationUser getApplicationUserByUsername(String userName);
}
