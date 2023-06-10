package com.crecema.my.spring.boot.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crecema.my.spring.boot.data.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper 只对外提供最基础的（业务无关的） CRUD 操作，具体业务相关的操作由 UserRepository 提供
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
