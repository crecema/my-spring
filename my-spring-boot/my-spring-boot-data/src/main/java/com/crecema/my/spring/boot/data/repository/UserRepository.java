package com.crecema.my.spring.boot.data.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.crecema.my.spring.boot.data.domain.entity.User;
import com.crecema.my.spring.boot.data.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * UserRepository 为业务提供持久层接口，每个 public 方法都对应一个 落库/查询 操作
 */
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    public User getOneByUid(Long uid) {
        User condition = User.builder().uid(uid).build();
        return selectOne(condition);
    }

    public User getOneByEmail(String email) {
        User condition = User.builder().email(email).build();
        return selectOne(condition);
    }

    private User selectOne(User condition) {
        return userMapper.selectOne(userQueryWrapper(condition));
    }

    private List<User> selectList(User condition) {
        return userMapper.selectList(userQueryWrapper(condition));
    }

    private LambdaQueryWrapper<User> userQueryWrapper(User condition) {
        Assert.notNull(condition, "condition must not be null");
        return new LambdaQueryWrapper<User>()
                .eq(condition.getId() != null, User::getId, condition.getId())
                .eq(condition.getUid() != null, User::getUid, condition.getUid())
                .eq(condition.getName() != null, User::getName, condition.getName())
                .eq(condition.getEmail() != null, User::getEmail, condition.getEmail())
                .eq(condition.getPhone() != null, User::getPhone, condition.getPhone())
                .eq(condition.getIdNumber() != null, User::getIdNumber, condition.getIdNumber())
                .eq(condition.getStatus() != null, User::getStatus, condition.getStatus());
    }

    private int insert(User entity) {
        Assert.notNull(entity, "user must not be null");
        Assert.hasLength(entity.getName(), "user.name must not be empty");
        Assert.hasLength(entity.getEmail(), "user.email must not be empty");
        return userMapper.insert(entity);
    }

    private int update(User entity, User condition) {
        Assert.notNull(entity, "user must not be null");
        Assert.notNull(condition, "condition must not be null");
        LambdaUpdateWrapper<User> queryWrapper = new LambdaUpdateWrapper<User>()
                .eq(true, User::getUid, condition.getUid())
                .eq(condition.getId() != null, User::getId, condition.getId())
                .eq(condition.getName() != null, User::getName, condition.getName());
        return userMapper.update(entity, queryWrapper);
    }

    @Deprecated
    private int delete(User condition) {
        Assert.notNull(condition, "condition must not be null");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(true, User::getUid, condition.getUid())
                .eq(condition.getId() != null, User::getId, condition.getId())
                .eq(condition.getName() != null, User::getName, condition.getName());
        return userMapper.delete(queryWrapper);
    }

}
