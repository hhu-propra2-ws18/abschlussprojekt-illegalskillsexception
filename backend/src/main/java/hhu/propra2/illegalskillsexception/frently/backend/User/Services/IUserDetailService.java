package hhu.propra2.illegalskillsexception.frently.backend.User.Services;

import hhu.propra2.illegalskillsexception.frently.backend.Exceptions.FrentlyException;
import hhu.propra2.illegalskillsexception.frently.backend.User.DTOs.UserDetailResponse;
import org.springframework.security.core.Authentication;

public interface IUserDetailService {

    UserDetailResponse getUserDetailService(Authentication auth) throws FrentlyException;

}
