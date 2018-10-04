package com.piledriver.service;

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
	public ResponseEntity<List<WorkRegion>> getProject(@RequestParam("projectid") int projectid) {
		System.out.println("get work region");
		List<WorkRegion> plist = new ArrayList<WorkRegion>();
		try {
			plist = (List<WorkRegion>) workRegionDao.findByProjectid(projectid);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<WorkRegion>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<WorkRegion>>(plist, HttpStatus.OK);
	}
}
