/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.repository
 * File name: UserRepository.java
 * Author: ptphuong.
 * Created date: Feb 19, 2019
 * Created time: 2:40:45 PM
 */

package com.cmcglobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cmcglobal.entity.User;

/*
 * @author ptphuong.
 * Created date: Feb 19, 2019
 * Created time: 2:40:45 PM
 * Description: TODO - 
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
