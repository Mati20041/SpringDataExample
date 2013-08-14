package com.mati.spring.springdataexample;

import com.mati.spring.springdataexample.domain.Human;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HelloJDBCDao extends JdbcDaoSupport{

    private final String HUMANS_TABLE_NAME = "Humans";

    private final String CREATE_TABLE_HUMANS = "CREATE TABLE IF NOT EXISTS "+HUMANS_TABLE_NAME+"(" +
            "ID INT IDENTITY PRIMARY KEY," +
            "USERNAME VARCHAR(255) NOT NULL );" ;


    private String INSERT_HUMAN_COMMAND = "INSERT INTO "+HUMANS_TABLE_NAME+" (USERNAME) VALUES (?);";


    public void createTable() {
        getJdbcTemplate().execute(CREATE_TABLE_HUMANS);
    }

    public void insertHuman(Human human){
        getJdbcTemplate().update(INSERT_HUMAN_COMMAND,human.getName(),human.getCity().getId());
    }

    public List<Human> getHumans(){
        return getJdbcTemplate().query("SELECT * FROM "+HUMANS_TABLE_NAME+";",new RowMapper<Human>() {
            @Override
            public Human mapRow(ResultSet resultSet, int i) throws SQLException {
                Human human = new Human();
                human.setName(resultSet.getString("USERNAME"));
                human.setId(resultSet.getLong("ID"));
                return human;
            }
        });
    }

    public Human getHumanByID(Long id){
        return getJdbcTemplate().query("SELECT * FROM"+HUMANS_TABLE_NAME+"WHERE "+HUMANS_TABLE_NAME+".ID = ?",new Object[]{id},new ResultSetExtractor<Human>() {
            @Override
            public Human extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Human human = new Human();
                human.setName(resultSet.getString("USERNAME"));
                human.setId(resultSet.getLong("ID"));
                return human;
            }
        });
    }

    public void drop(){
        getJdbcTemplate().execute("DROP TABLE IF EXISTS " + HUMANS_TABLE_NAME + ";");
    }
}
