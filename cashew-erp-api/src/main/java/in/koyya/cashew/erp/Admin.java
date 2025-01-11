package in.koyya.cashew.erp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity representing an Admin user in the system.
 */
@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Admin {

    // Primary key for the Admin entity, validated as an email
    @Id
    @Email
    @NotNull
    private String email;

    // Password field for the Admin, cannot be null
    @NotNull
    private String password;
}
