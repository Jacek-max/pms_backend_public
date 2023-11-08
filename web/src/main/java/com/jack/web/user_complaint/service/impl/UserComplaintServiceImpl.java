package com.jack.web.user_complaint.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.web.user_complaint.entity.UserComplaint;
import com.jack.web.user_complaint.mapper.UserComplaintMapper;
import com.jack.web.user_complaint.service.UserComplaintService;
import org.springframework.stereotype.Service;

@Service
public class UserComplaintServiceImpl extends ServiceImpl<UserComplaintMapper, UserComplaint> implements UserComplaintService {
}
