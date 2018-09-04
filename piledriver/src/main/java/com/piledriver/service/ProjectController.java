package com.piledriver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import com.piledriver.service.bean.MachineConfig;
import com.piledriver.service.bean.ProjectInfo;
import com.piledriver.service.dao.MachineConfigDao;
import com.piledriver.service.dao.ProjectDao;

@Controller
@RestController
@RequestMapping("/api/v1")
public class ProjectController {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private MachineConfigDao machineDao;

	@RequestMapping(value = "/config", method = RequestMethod.POST)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> insertConfig(@RequestParam("name") String name,
			@RequestParam("delay") Integer delay, @RequestParam("duration") Integer duration,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			machineDao.insertConfig(name, delay, duration);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "fail");
			map.put("detail", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		map.put("result", "success");

		System.out.println(map);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/config", method = RequestMethod.PUT)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<Map<String, Object>> updateConfig(@RequestParam("name") String name,
			@RequestParam("delay") Integer delay, @RequestParam("duration") Integer duration) {
		System.out.println("name:" + name + ",delayt:" + delay + ",duration:" + duration);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int result = machineDao.upateConfig(delay, duration, name);
			if (result == 0) {
				throw new Exception("update pk not exist");
			}
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "fail");
			map.put("detail", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		map.put("result", "success");

		System.out.println(map);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<MachineConfig> getConfig(@RequestParam("name") String name) {
		System.out.println("getConfig");
		MachineConfig machineList = new MachineConfig();
		try {
			machineList = machineDao.findByMachineName(name);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MachineConfig>(machineList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println(machineList);
		return new ResponseEntity<MachineConfig>(machineList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/project", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<ProjectInfo>> getProject() {
		System.out.println("getConfig");
		List<ProjectInfo> plist = new ArrayList<ProjectInfo>();
		try {
			plist = (List<ProjectInfo>) projectDao.findAll();

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProjectInfo>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ProjectInfo>>(plist, HttpStatus.OK);
	}
}
