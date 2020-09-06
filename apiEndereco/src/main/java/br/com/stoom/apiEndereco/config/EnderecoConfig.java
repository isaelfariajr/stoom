package br.com.stoom.apiEndereco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class EnderecoConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer{
	
	private static final List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("pt"));

	@Override
	@NonNull
	public Locale resolveLocale(final HttpServletRequest request) {
		final String headerLang = request.getHeader("Accept-Language");
	    return headerLang == null || headerLang.isEmpty() ? Locale.getDefault() 
	    		: Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		rs.setBasename("messageError/messages");
		rs.setDefaultEncoding("UTF-8");
	    rs.setUseCodeAsDefaultMessage(true);
	    return rs;
	}
}
