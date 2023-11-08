package com.jack.web.house_list.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.house_list.entity.HouseList;
import com.jack.web.house_list.entity.ListParm;
import com.jack.web.house_list.mapper.HouseListMapper;
import com.jack.web.house_list.service.HouseListService;
import org.springframework.stereotype.Service;

@Service
public class HouseListServiceImpl extends ServiceImpl<HouseListMapper, HouseList> implements HouseListService {

    @Override
    public IPage<HouseList> getList(ListParm parm) {

        IPage<HouseList> page = new Page<>();
        page.setSize(parm.getPageSize());
        page.setCurrent(page.getCurrent());
        return this.baseMapper.getList(page,parm.getBuildName(),
                parm.getUnitName(),parm.getHouseNum(),parm.getStatus());
    }
}
