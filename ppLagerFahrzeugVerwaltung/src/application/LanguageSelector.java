package application;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * @author Marcus Zitzelsberger 
 */

public class LanguageSelector {
	
	void selectLanguage(Language lan){
		Locale locale;
		switch(lan) {
		case EN: locale = Locale.ENGLISH; break;
		case DE: locale = Locale.GERMAN; break;
		default: locale = Locale.ENGLISH; break;
		}
		Specifications.getInstance().setLocale(locale);
		Specifications.getInstance().setResources(ResourceBundle.getBundle(Specifications.getInstance().getResourceBundleName(), locale));
	}
}
