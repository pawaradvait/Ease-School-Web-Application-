package com.easeschool.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "AuditAwareImpl")
public class AuditAwareImpl  implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {


        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
