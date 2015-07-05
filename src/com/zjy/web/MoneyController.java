package com.zjy.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.models.Money;
import com.zjy.service.MoneyService;

@Controller
@RequestMapping("/operate/money")
public class MoneyController {

	@Resource
	private MoneyService moneyService;

	@RequestMapping("/showAllMoneys.do")
	public void selectAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Money> moneys = moneyService.selectAll();
		response.getWriter().print(JSONArray.fromObject(moneys));
	}

	@RequestMapping("/updaeMoney.do")
	public void updateMoney(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Money money = new Money();
		money.setMid(request.getParameter("mid"));
		money.setUid(request.getParameter("uid"));
		money.setNum(Double.parseDouble(request.getParameter("num")));
		moneyService.update(money);
	}
}
