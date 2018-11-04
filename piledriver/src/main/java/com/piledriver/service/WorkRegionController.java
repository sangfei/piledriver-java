package com.piledriver.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.piledriver.service.bean.WorkRegion;
import com.piledriver.service.dao.WorkRegionDao;

@Controller
@RestController
@RequestMapping("/api/v1")
public class WorkRegionController {

	@Autowired
	private WorkRegionDao workRegionDao;

	@RequestMapping(value = "/workregion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<WorkRegion>> getProject(
			@RequestParam(value = "projectid", required = false) Integer projectid) {
		System.out.println("get work region");
		List<WorkRegion> plist = new ArrayList<WorkRegion>();
		try {
			if (projectid != null) {
				plist = (List<WorkRegion>) workRegionDao.findByProjectid(projectid);

			} else {
				plist = (List<WorkRegion>) workRegionDao.findAll();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<WorkRegion>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<WorkRegion>>(plist, HttpStatus.OK);
	}

	@RequestMapping(value = "/workregion", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deleteProject(@RequestParam("id") int id) {
		System.out.println("deleteworkregiont which id=" + id);
		try {
			workRegionDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}

	@RequestMapping(value = "/workregion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<Integer> addWorkRegion(HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("desc") String desc, @RequestParam("projectid") int projectid)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		System.out.println("add addWorkRegion name:" + name + " desc:" + desc + " projectid:" + projectid);
		if (!(name == null || name.isEmpty())) {
			try {
				WorkRegion workRegion = workRegionDao.findworkregion(name, projectid);
				// 如果有值说明名称重复
				if (workRegion != null && workRegion.getId() != 0) {
					return new ResponseEntity<Integer>(-2, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				workRegionDao.insertworkregion(name, desc, projectid);
				return new ResponseEntity<Integer>(0, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
