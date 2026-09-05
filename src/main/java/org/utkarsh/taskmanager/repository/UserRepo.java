package org.utkarsh.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utkarsh.taskmanager.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {

}
