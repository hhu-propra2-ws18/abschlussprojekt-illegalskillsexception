package hhu.propra2.illegalskillsexception.frently.backend.Controller.Conflict.IServices;

import hhu.propra2.illegalskillsexception.frently.backend.Data.Models.Role;

import java.util.Set;

public interface IConflictAdminService {
    void isAdmin(Set<Role> set) throws Exception;
}
