package com.jack.web.park_list.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.web.park_list.entity.ParkList;
import com.jack.web.park_list.entity.ParkListParm;

public interface ParkListService extends IService<ParkList> {

    IPage<ParkList> getList(ParkListParm parkListParm);
}
