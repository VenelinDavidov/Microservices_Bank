package com.eazybytes.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware <String> {



    // this is auditing method to get the current user
    @Override
    public Optional <String> getCurrentAuditor() {

        return Optional.of("ACCOUNT_MS");
    }
}
