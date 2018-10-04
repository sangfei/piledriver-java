package com.piledriver.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.piledriver.service.bean.Image;
import com.piledriver.service.dao.ImageDao;

@Controller
@RequestMapping(value = "/image/")
public class ImageCacheController {
	@Autowired
	private ImageDao imageDao;

	// for restful http://localhost:8888/rest/image/load?imageName=a.jpg
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public void imageLoad(@RequestParam(value = "imageid", required = true) String imageid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Image img = imageDao.findByImageid(imageid);
		ServletOutputStream outputStream = null;
		byte[] buffer = img.getImage();
		try {
			response.setHeader("Content-Type", "image/jpg;charset=UTF-8");
			outputStream = response.getOutputStream();
			outputStream.write(buffer);
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}

		}

	}

	// for restful http://localhost:8888/rest/image/a.jpg
	@RequestMapping(value = "/load/{imageid}", method = RequestMethod.GET)
	public void helloMan(@PathVariable String imageid, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("imageid " + imageid);
		Image img = imageDao.findByImageid(imageid);
		ServletOutputStream outputStream = null;
		byte[] buffer = img.getImage();
		try {
			response.setHeader("Content-Type", "image/jpg;charset=UTF-8");
			outputStream = response.getOutputStream();
			outputStream.write(buffer);
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}

		}
	}

	@RequestMapping(value = "/uploadimage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<Integer> addRecommend(HttpServletRequest request, @RequestParam("id") String id,
			@RequestParam("img") MultipartFile img) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		int ret = -1;
		List<String> fileTypes = new ArrayList<String>();
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("bmp");
		fileTypes.add("png");
		String fileName = img.getOriginalFilename();
		if (!(fileName == null || "".equals(fileName))) {
			String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
			if (fileTypes.contains(extensionName)) {
				try {
					byte[] content = img.getBytes();
					System.out.println("content " + content);
					ret = imageDao.saveImage(content, id);
					System.out.println("return " + ret);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return new ResponseEntity<Integer>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Integer>(ret, HttpStatus.OK);
	}

}