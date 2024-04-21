package com.example.demo.dao;

import com.example.demo.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class ContactRowMapper implements RowMapper<Contact> {


    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact c = new Contact();
        c.setId(rs.getLong("id"));
        c.setFirstName(rs.getString("name"));
        c.setTel(rs.getString("tel"));
        c.setEmail(rs.getString("email"));

        return c;
    }
}

@RequiredArgsConstructor
@Component
public class JdbcContactDAO implements ContactDAO {

    private static final String SQL_SELECT_CONTACT =
            "SELECT id, name, tel, email FROM Contacts";
    private static final String SQL_SELECT_CONTACT_BY_ID =
            SQL_SELECT_CONTACT + " WHERE id = ?";
    private static final String SQL_SELECT_CONTACT_BY_NAME =
            SQL_SELECT_CONTACT + " WHERE name LIKE ?";
    private static final String SQL_DELETE_CONTACT_BY_ID =
            "DELETE FROM Contacts WHERE id = ?";
    private static final String SQL_INSERT_CONTACT =
            "INSERT INTO Contacts (name, tel, email) VALUES (?, ?, ?) returning id";
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
    public void insert(Contact contact) {
        //TODO в базе использовать bigserial для столбца id ; вернкть в запроcе
        //jdbcTemplate.execute(SQL_INSERT_CONTACT, );

    }

    @Override
    public void update(Contact contact) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT,
                contact.getFirstName(), contact.getTel(),
                contact.getEmail(), contact.getId());

    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_CONTACT_BY_ID, id);
    }

}

