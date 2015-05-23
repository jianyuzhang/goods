package com.zjy.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zjy.models.CD;
import com.zjy.service.CDService;

@Controller
@RequestMapping("/operate/CD")
public class CDController {

	@Resource
	private CDService cdService;

	/*
	 * 展示全部的CD
	 */
	@RequestMapping("/showAllCDs.do")
	public void showAllCDs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<CD> cds = cdService.showAllCDs();
		response.getWriter().print(JSONArray.fromObject(cds));
	}

	/*
	 * 展示点击菜单显示对应的菜单
	 */
	@RequestMapping("/showSomeCDs.do")
	public void showSomeCDs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] properties = { "mid" };
		String mid = request.getParameter("mid");
		Object[] propertyValues = { mid };
		List<CD> cds = cdService.showSomeCDs(properties, propertyValues);
		response.getWriter().print(JSONArray.fromObject(cds));
	}

	/*
	 * 获取总数
	 */
	@RequestMapping("/count.do")
	public void count(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String propertyName = "mid";
		Object propertyValue = request.getParameter("mid");
		int i = cdService.countCDs(propertyName, propertyValue);
		response.getWriter().print(JSONArray.fromObject(i));
	}

	/*
	 * 分页展示
	 */
	@RequestMapping("/showListByPage.do")
	public void showCDsByPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] properties = { "mid", "pageNo" };
		int pageNo = (Integer.parseInt(request.getParameter("pageNo")) - 1) * 18;
		Object[] propertyValues = { request.getParameter("mid"), pageNo };
		List<CD> cdListPages = cdService.showSomeCDsByPage(properties,
				propertyValues);
		response.getWriter().print(JSONArray.fromObject(cdListPages));
	}

	/*
	 * 分页展示查询内容
	 */
	@RequestMapping("/searchListByPage.do")
	public void searchCDsByPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] properties = { "cname", "singer" };
		// int pageNo= (Integer.parseInt(request.getParameter("pageNo"))-1)*18;
		String cname = request.getParameter("cname");
		String singer = request.getParameter("singer");
		Object[] propertyValues = { cname, singer };
		List<CD> cdListPages = cdService.searchSomeCDsByPage(properties,
				propertyValues);
		response.getWriter().print(JSONArray.fromObject(cdListPages));
	}

	/*
	 * 展示CD的具体内容
	 */
	@RequestMapping("/showCDDetial.do")
	public void showCDDetial(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String cid = request.getParameter("cid");
		System.out.println(cid);
		CD cd = cdService.showCDDetial(cid);
		System.out.println(cd.toString());
		response.getWriter().print(JSONArray.fromObject(cd));
	}

	/*
	 * 跟新库中的商品数量
	 */
	@RequestMapping("/updateCD.do")
	public void updateCD(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int sum = Integer.parseInt(request.getParameter("sum"));
		String cid = request.getParameter("cid");
		CD cd = new CD();
		cd.setSum(sum);
		cd.setCid(cid);
		cdService.updateCD(cd);
	}

	/*
	 * 跟新库中的商品信息
	 */
	@RequestMapping("/editCD.do")
	public void editCD(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int sum = Integer.parseInt(request.getParameter("sum"));
		String cid = request.getParameter("cid");
		String mid = request.getParameter("mid");
		String cname = request.getParameter("cname");
		String language = request.getParameter("language");
		String singer = request.getParameter("singer");
		Double currPrice = Double
				.parseDouble(request.getParameter("currPrice"));
		Double price = Double.parseDouble(request.getParameter("price"));
		String printtime = request.getParameter("printtime");
		CD cd = new CD();
		cd.setSum(sum);
		cd.setCid(cid);
		cd.setCname(cname);
		cd.setCurrPrice(currPrice);
		cd.setLanguage(language);
		cd.setMid(mid);
		cd.setPrice(price);
		cd.setSinger(singer);
		cd.setPrinttime(printtime);
		cdService.updateCD(cd);
	}

	/*
	 * 图片上传
	 */
	@RequestMapping(value = "/uploadImg.do", method = RequestMethod.POST)
	public void uploadImg(@RequestParam("fileUpload") MultipartFile image_b,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		byte[] bytes = image_b.getBytes();

		if (!image_b.isEmpty()) {
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("/cds")));
			stream.write(bytes);
			stream.close();
		}
	}
/*
 * 音乐上传
 */
	@RequestMapping(value = "/uploadMusic.do", method = RequestMethod.POST)
	public void uploadMusic(@RequestParam("fileUpload") MultipartFile image_w,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(image_w);
	}
}
