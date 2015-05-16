package com.zjy.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.MoneyDao;
import com.zjy.models.Money;
import com.zjy.service.MoneyService;
@Service("moneyService")
public class MoneyServiceImpl implements MoneyService{
 
	@Resource
	private MoneyDao moneyDao;
	@Override
	public List<Money> selectAll() {
		List<Money> moneys = new ArrayList<Money>();
		moneys = moneyDao.selectAll();
		return moneys;
	}

	@Override
	public void update(Money money) {
		moneyDao.update(money);
	}

}
