package org.acme.hibernate.orm.panache.rest.models;


import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.persistence.*;


@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String login;
    @Column(nullable = false)
    public String password;
    @Transient
    public String token;

    public User() {}

    public User(String login, String password) {
        this.login = login;
        this.password = BcryptUtil.bcryptHash(password);
    }

    public static User findByLogin(String login) {
        return find("login", login).firstResult();
    }

}
