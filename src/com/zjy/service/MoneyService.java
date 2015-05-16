package com.zjy.service;

import java.util.List;

import com.zjy.models.Money;

public interface MoneyService {
  public List<Money> selectAll();
  public void update(Money money);
}
