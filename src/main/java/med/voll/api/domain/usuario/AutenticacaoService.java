package med.voll.api.domain.usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRespository usuarioRespository;

    public AutenticacaoService(UsuarioRespository usuarioRespository) {
        this.usuarioRespository = usuarioRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRespository.findByLogin(username);
    }
}
