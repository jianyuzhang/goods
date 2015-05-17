package com.zjy.service;

import com.zjy.models.Wuliu;

public interface WuliuService {
public Wuliu selectByOid(String[] properties,Object[] values);

public void insert(Wuliu wuliu);
}
