package com.piledriver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.piledriver.service.bean.Construction;
import com.piledriver.service.dao.ConstructionDao;
import com.piledriver.service.utils.EntityUtils;

@Controller
@RestController
@RequestMapping("/api/v1")
public class ConstructionController {

	@Autowired
	private ConstructionDao constructionDetail;

	@RequestMapping(value = "/construction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Construction>> getProjectByTimeRange(@RequestParam("start") Integer starttime,
			@RequestParam("end") Integer endtime, @RequestParam(value = "workregion", required = false) Integer workregion,
			@RequestParam(value = "ownerid", required = false) Integer ownerid) {
		System.out.println("getProjectByTimeRange start:" + starttime + " end:" + endtime);

		List<Construction> plist = new ArrayList<Construction>();
		try {
			if (starttime != null && endtime != null) {
				List<Object[]> select = null;
				if (workregion != null) {
					select = constructionDetail.queryByWorkRegionTimeRange(starttime, endtime, workregion);
					System.out.println("getProjectByTimeRange select:" + select.size());
				} else if (ownerid != null) {
					select = constructionDetail.queryByOwnerTimeRange(starttime, endtime, ownerid);
				} else {
					select = constructionDetail.queryByTimeRange(starttime, endtime);
				}

				plist = EntityUtils.castEntity(select, Construction.class, new Construction());
				System.out.println("getProjectByTimeRange result:" + plist);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Construction>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Construction>>(plist, HttpStatus.OK);
	}

}
