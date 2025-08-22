package com.telusko.SpringJDBCDemo.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.telusko.SpringJDBCDemo.model.Alien;

@Repository
public class AlienRepo {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Alien> findAll() {
		String sql = "select * from alien";
		//Since Rowmapper is a functional interface so we need to override the function inside an anonymous class like this
		RowMapper<Alien> mapper = new RowMapper<Alien>() {

			@Override
			public Alien mapRow(ResultSet rs, int rowNum) throws SQLException {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setTech(rs.getString(3));
				return a;
			}
			
		};
		List<Alien> aliens = jdbcTemplate.query(sql,mapper);
		return aliens;
	}

	public void save(Alien alien) {
		String sql="insert into alien(id, name, tech) values(?,?,?)";
		int rows=jdbcTemplate.update(sql,alien.getId(),alien.getName(),alien.getTech());
		System.out.println(rows+" Row/s affected");
	}

}
