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
import com.piledriver.service.bean.ConstructionStatistic;
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
	public ResponseEntity<List<Construction>> getProjectByTimeRange(@RequestParam("start") Long starttimel,
			@RequestParam("end") Long endtimel,
			@RequestParam(value = "workregionid", required = false) Integer workregion,
			@RequestParam(value = "equipmentid", required = false) Integer equipmentid,
			@RequestParam(value = "ownerid", required = false) Integer ownerid) {
		System.out.println("getProjectByTimeRange start:" + starttimel + " end:" + endtimel);

		Integer starttime = (int) (starttimel / 1000);
		Integer endtime = (int) (endtimel / 1000);

		List<Construction> plist = new ArrayList<Construction>();
		try {
			if (starttime != null && endtime != null) {
				List<Object[]> select = null;
				if (workregion != null) {
					select = constructionDetail.queryByWorkRegionTimeRange(starttime, endtime, workregion);
					System.out.println("getProjectByTimeRange select:" + select.size());
				} else if (ownerid != null) {
					select = constructionDetail.queryByOwnerTimeRange(starttime, endtime, ownerid);
				} else if (equipmentid != null) {
					select = constructionDetail.queryByEquipmentTimeRange(starttime, endtime, equipmentid);
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

	@RequestMapping(value = "/construction/stat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ConstructionStatistic>> staticByTimeRange(@RequestParam("start") Long starttimel,
			@RequestParam("end") Long endtimel, @RequestParam("type") String statType,
			@RequestParam(value = "workregionid", required = false) Integer workregion,
			@RequestParam(value = "projectid", required = false) Integer projectid,
			@RequestParam(value = "equipmentid", required = false) Integer equipmentid,
			@RequestParam(value = "ownerid", required = false) Integer ownerid) {
		Integer starttime = (int) (starttimel / 1000);
		Integer endtime = (int) (endtimel / 1000);
		System.out.println("staticByTimeRange start:" + starttime + " end:" + endtime + " statType:" + statType
				+ " workregion:" + workregion + " equipmentid:" + equipmentid);

		List<ConstructionStatistic> plist = new ArrayList<ConstructionStatistic>();
		try {
			if (starttime != null && endtime != null && statType != null) {

				List<Object[]> select = null;
				if ("g_owner".equalsIgnoreCase(statType)) {

					select = constructionDetail.groupByOwner(starttime, endtime, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("groupByOwner result:" + plist);

				} else if ("g_project".equalsIgnoreCase(statType)) {

					select = constructionDetail.groupByProject(starttime, endtime, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());
					System.out.println("groupByProject result:" + plist);

				} else if ("g_workregion".equalsIgnoreCase(statType)) {

					select = constructionDetail.groupByWorkregion(starttime, endtime, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("groupByProject result:" + plist);

				} else if ("g_equipment".equalsIgnoreCase(statType)) {

					select = constructionDetail.groupByEquipment(starttime, endtime, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("groupByProject result:" + plist);

				} else if ("g_equipment_s_workregion".equalsIgnoreCase(statType) && workregion != null) {

					select = constructionDetail.groupByEquipMentOfWorkRegion(starttime, endtime, workregion, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("groupByEquipMentOfWorkRegion result:" + plist);

				} else if ("g_workregion_s_workregion".equalsIgnoreCase(statType) && workregion != null) {

					select = constructionDetail.groupByWorkRegionOfWorkRegion(starttime, endtime, workregion, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("groupByWorkRegionOfWorkRegion result:" + plist);
				} else if ("g_equipment_s_equipment".equalsIgnoreCase(statType) && equipmentid != null) {

					select = constructionDetail.groupByEquipMentOfEquipment(starttime, endtime, equipmentid, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("groupByEquipMentOfEquipment result:" + plist);

				} else if ("all".equalsIgnoreCase(statType) && workregion != null) {

					select = constructionDetail.queryStatByTimeRange(starttime, endtime, workregion, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("queryStatByTimeRange result:" + plist);

				} else if ("listByEquipment".equalsIgnoreCase(statType) && equipmentid != null) {

					select = constructionDetail.queryStatByTimeRangeEquipment(starttime, endtime, equipmentid, statType);
					plist = EntityUtils.castEntity(select, ConstructionStatistic.class, new ConstructionStatistic());

					System.out.println("queryStatByTimeRange result:" + plist);

				} else {
					return new ResponseEntity<List<ConstructionStatistic>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				System.out.println("staticByTimeRange empty:" + plist);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception:" + e);
			return new ResponseEntity<List<ConstructionStatistic>>(plist, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ConstructionStatistic>>(plist, HttpStatus.OK);
	}

	@RequestMapping(value = "/construction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> insertConstrcution(@RequestParam("date") Long starttimel,
			@RequestParam("pieces") Double pieces, @RequestParam("workregion") Integer workregion,
			@RequestParam(value = "reporterid", required = false) Integer reporterid,
			@RequestParam(value = "projectid") Integer projectid,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "reason", required = false) String reason,
			@RequestParam("equipmentid") Integer equipmentid,
			@RequestParam(value = "imageid", required = false) String imageid,
			@RequestParam(value = "weather", required = false) String weather,
			@RequestParam("ownerid") Integer ownerid) {
		Integer date = (int) (starttimel / 1000);

		System.out.println("insertConstrcution date:" + date + " pieces:" + pieces + " equipmentid:" + equipmentid
				+ " ownerid:" + ownerid);

		Construction entity = new Construction(date, pieces, workregion, 0, 0, reason, equipmentid, imageid, weather,
				ownerid, projectid);

		try {
			Construction ret = constructionDetail.save(entity);
			System.out.println("insertConstrcution result:" + ret.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}

	@RequestMapping(value = "/construction", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deleteConstrcution(@RequestParam("id") int id) {
		System.out.println("deleteConstrcution id:" + id);

		try {
			constructionDetail.delete(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}
}
