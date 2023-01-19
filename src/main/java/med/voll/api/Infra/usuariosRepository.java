package med.voll.api.Infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface usuariosRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}
