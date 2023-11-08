package com.jack.web.park_list.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.park_list.entity.ParkList;
import com.jack.web.park_list.entity.ParkListParm;
import com.jack.web.park_list.mapper.ParkListMapper;
import com.jack.web.park_list.service.ParkListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ParkListServiceImpl extends ServiceImpl<ParkListMapper, ParkList> implements ParkListService {

    @Override
    public IPage<ParkList> getList(ParkListParm parkListParm) {

        QueryWrapper<ParkList> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parkListParm.getParkName())){
            query.lambda().like(ParkList::getParkName,parkListParm.getParkName());
        }

        if (StringUtils.isNotEmpty(parkListParm.getParkStatus())){
            query.lambda().eq(ParkList::getParkStatus,parkListParm.getParkStatus());
        }

        if (StringUtils.isNotBlank(parkListParm.getParkType())){
            query.lambda().eq(ParkList::getParkType,parkListParm.getParkType());
        }
        query.lambda().orderByAsc(ParkList::getParkNum);

        IPage<ParkList> page = new Page<>();
        page.setCurrent(parkListParm.getCurrentPage());
        page.setSize(parkListParm.getPageSize());
        return this.baseMapper.selectPage(page,query);
    }
}
