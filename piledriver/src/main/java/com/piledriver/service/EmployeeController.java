package com.piledriver.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.piledriver.service.bean.Employee;
import com.piledriver.service.dao.EmployeeDao;

@Controller
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeDao stuffDao;

	@RequestMapping(value = "/stuff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Integer> addStuff(@RequestParam("name") String name, @RequestParam("sex") String sex,
			@RequestParam("phone") String phone, @RequestParam("birth") String birth,
			@RequestParam("title") String title, @RequestParam("pwd") String pwd) {
		System.out.println("add stuff of " + name);
		int ret = 0;
		try {
			ret = stuffDao.insertStuff(name, sex, pwd, phone, Date.valueOf(birth), Integer.valueOf(title));

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(ret, HttpStatus.OK);
	}

	@RequestMapping(value = "/list/stuff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Employee>> getStuff() {
		System.out.println("get stuff by ");
		List<Employee> plist = new ArrayList<Employee>();
		try {
			plist = (List<Employee>) stuffDao.findAll();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Employee>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Employee>>(plist, HttpStatus.OK);
	}

	@RequestMapping(value = "/stuff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Employee> getStuffByPhone(@RequestParam("phone") String phone) {
		System.out.println("get stuff by " + phone);
		Employee stuff = new Employee();
		try {
			stuff = stuffDao.findByPhone(phone);
			System.out.println("get stuff by " + stuff);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Employee>(stuff, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (stuff.getName().equals("")) {
			return new ResponseEntity<Employee>(stuff, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Employee>(stuff, HttpStatus.OK);
	}
}
