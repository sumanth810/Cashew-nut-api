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

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Admin {

    @Id
    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;
}
