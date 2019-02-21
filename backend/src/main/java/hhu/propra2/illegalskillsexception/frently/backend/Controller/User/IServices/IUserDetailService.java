package hhu.propra2.illegalskillsexception.frently.backend.Controller.User.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Controller.Exceptions.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.ForeignUserDetailResponse;
import hhu.propra2.illegalskillsexception.frently.backend.Controller.User.DTOs.UserDetailResponse;
import org.springframework.security.core.Authentication;

public interface IUserDetailService {

    UserDetailResponse getUserDetails(Authentication auth) throws FrentlyException;

    ForeignUserDetailResponse getForeignUserDetails(String username) throws FrentlyException;

}
