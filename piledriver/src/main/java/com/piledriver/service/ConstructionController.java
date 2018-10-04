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

import com.piledriver.service.bean.ConstructionDetailInfo;
import com.piledriver.service.dao.ConstructionDetail2;
import com.piledriver.service.utils.EntityUtils;

@Controller
@RestController
@RequestMapping("/api/v1")
public class ConstructionController {

	@Autowired
	private ConstructionDetail2 constructionDetail;

	@RequestMapping(value = "/construction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<ConstructionDetailInfo>> getProject(@RequestParam("workregionId") int workregionId) {
		System.out.println("get construction detail  abc.");
		System.out.println("================>" + workregionId);

		List<ConstructionDetailInfo> plist = new ArrayList<ConstructionDetailInfo>();
		try {
			List<Object[]> select = constructionDetail.queryDetails(workregionId);

			plist = EntityUtils.castEntity(select, ConstructionDetailInfo.class, new ConstructionDetailInfo());
			System.out.println("================>" + plist);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ConstructionDetailInfo>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ConstructionDetailInfo>>(plist, HttpStatus.OK);
	}

}
