package in.ankeshkr.foodiesapi.service;

import org.springframework.security.core.Authentication;

public interface AuttheneticationFacade {

    Authentication getAuthentication();
}
