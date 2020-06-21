package com.alpha.cainiaoshop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.alpha.common.utils.PageUtils;
import com.alpha.cainiaoshop.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author guojun
 * @email 470798191@qq.com
 * @date 2020-05-26 13:25:07
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

