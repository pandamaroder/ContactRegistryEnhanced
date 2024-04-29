package com.example.demo.dao;

import com.example.demo.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ContactRowMapper implements RowMapper<Contact> {


    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact c = new Contact();
        c.setId(rs.getLong("id"));
        c.setFirstname(rs.getString("firstname"));
        c.setTel(rs.getString("tel"));
        c.setEmail(rs.getString("email"));

        return c;
    }
}

@RequiredArgsConstructor
@Component
public class JdbcContactDAO implements ContactDAO {

    private static final String SQL_SELECT_CONTACT =
            "SELECT * FROM contacts";
    private static final String SQL_SELECT_CONTACT_BY_ID =
            SQL_SELECT_CONTACT + " WHERE id = ?";
    private static final String SQL_SELECT_CONTACT_BY_NAME =
            SQL_SELECT_CONTACT + " WHERE name LIKE ?";
    private static final String SQL_DELETE_CONTACT_BY_ID =
            "DELETE FROM contacts WHERE id = ?";
    private static final String SQL_INSERT_CONTACT =
            "INSERT INTO contacts (firstname, tel, email) VALUES (?, ?, ?) returning id";

    private static final String SQL_UPDATE_CONTACT =
            "UPDATE Contacts SET name = ?, tel = ?, email = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Contact findById(int id) {
        Contact Contact = jdbcTemplate.queryForObject(
                SQL_SELECT_CONTACT_BY_ID, new Object[]{id},
                new ContactRowMapper());
        return Contact;
    }

    @Override
    public List<Contact> findAll() {


        List<Contact> Contacts = jdbcTemplate.query(SQL_SELECT_CONTACT,
                new ContactRowMapper());
        //new BeanPropertyRowMapper(Contact.class));
        return Contacts;
    }

    @Override
    public List<Contact> findByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_CONTACT_BY_NAME,
                new Object[]{"%" + name + "%"},
                new ContactRowMapper());
    }

    @Override
    public long insert(Contact contact) {

        long id = jdbcTemplate.queryForObject(SQL_INSERT_CONTACT,
                new Object[] {contact.getFirstname(), contact.getEmail(), contact.getTel()}, Long.class);

   /*     SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate) //overengineering
                .withTableName("contacts")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstname", contact.getFirstname());
        parameters.put("tel", contact.getTel());
        parameters.put("email", contact.getEmail());
        Number id2 = jdbcInsert.executeAndReturnKey(parameters);*/
         //возвращает кол-во измененных строк
        //int update = jdbcTemplate.update(SQL_INSERT_CONTACT, contact.getFirstname(), contact.getEmail(), contact.getTel());
        return id;


    }

    @Override
    public void update(Contact contact) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT,
                contact.getFirstname(), contact.getTel(),
                contact.getEmail(), contact.getId());

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_CONTACT_BY_ID, id);
    }

}

