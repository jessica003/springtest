package com.example.complyindia.repository.Auth;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.complyindia.model.Auth.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	
	User findByToken(String token);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	/*select all users except admin*/
	@Query("select u from User u inner join u.roles r where r.id !=:role" )
	List<User> findAllUsers(@Param("role") Integer userRole);

	/*fetching subdomain*/
	@Query( "select domain_name from Company c where c.id =:comp_id" )
	String getsubdomains(Long comp_id);

////	@Query( "select company_id from User c where c.email =:email_id" )
////	String getsubdomain(String email_id);
	
	/*select subdomain name for currently logged in user-with email id*/
	@Query("select c.domain_name FROM Company c, User u where u.company_id=c.id and u.email=:email")
	String getsubdomain(@Param("email") String email);

	@Query( "select id from User u  where u.username =:res" )
	Long findByUsername(@Param("res") Optional<String> res);
	
	/*fetch user based on role-id*/
	@Query("select u from User u inner join u.roles r where r.id =:role")
	User findUsers(@Param("role") int userRole);

	@Query("select u from User u inner join u.roles r where r.id =:role")
	List<User> getRelationsHead(@Param("role") int userRole);

}
