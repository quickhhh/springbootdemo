package com;

import com.dao.DepartmentRepository;
import com.dao.RolesRepository;
import com.dao.UserRepository;
import com.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootdemoApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringbootdemoApplication.class);

	@Autowired
	UserRepository userRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	RolesRepository roleRepository;

	@RequestMapping("/")
	String hello(){
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
		Page<User> page = userRepository.findAll(pageable);
		Assert.notNull(page);
		for(User user : page.getContent()) {
			logger.info("====user==== user name:{}, department name:{}, role name:{}",
					user.getName(), user.getDepartment().getName(), user.getRoles().get(0).getName());
		}
		return "hello aa";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}
}
