package org.iwms.driver.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void updateTableString(int id, String table, String column, String newValue) {
        // 使用数据库的行级锁来锁住指定记录
        String sql = "SELECT * FROM " + table + " WHERE id = ? FOR UPDATE";
        jdbcTemplate.query(sql, rs -> {
            // 更新记录的逻辑
            // rs 是查询结果集，您可以在这里进行更新操作
            // 例如，执行 UPDATE 语句来更新记录的值

            String updateSql = "UPDATE " + table + " SET " + column + " = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, newValue, id);
        }, id);
    }

    @Transactional
    public void updateTableInt(int id, String table, String column, int value) {


        String sql = "UPDATE " + table + " SET " + column + " = " + column + " + ? WHERE id = ?";
        jdbcTemplate.update(sql, value, id);
    }






}
