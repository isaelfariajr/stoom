package br.com.stoom.apiEndereco.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import br.com.stoom.apiEndereco.error.ErrorMessage;

@Component
@RequiredArgsConstructor
public class Dictionary {

	private final MessageSource messageSource;

    public ErrorMessage getMessage(@NonNull final String key, @NonNull final Object... args) {
        final String message = messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
        return new ErrorMessage(key, message);
    }
}
