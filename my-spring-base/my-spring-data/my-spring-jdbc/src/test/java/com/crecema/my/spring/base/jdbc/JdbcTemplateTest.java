package com.crecema.my.spring.base.jdbc;

import com.crecema.my.spring.base.jdbc.config.BeansConfig;
import com.crecema.my.spring.base.jdbc.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeansConfig.class)
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSex(resultSet.getInt("sex"));
        user.setAge(resultSet.getInt("age"));
        user.setCreateTime(resultSet.getTimestamp("create_time").toLocalDateTime());
        user.setUpdateTime(resultSet.getTimestamp("update_time").toLocalDateTime());
        return user;
    };

    @Test
    public void testSelect() {
        String sql = "select * from user";
        List<User> users = jdbcTemplate.query(sql, userRowMapper);
        assertNotNull(users);
        assertTrue(users.size() != 0);
    }

}
