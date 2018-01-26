package guiLager;

import application.Language;
import application.LanguageSelector;
import javafx.scene.control.Button;

/**
 * 
 * @author Marcus Zitzelsberger
 *
 */

public class LanguageButton extends Button {
	
	Language language;
	
	public LanguageButton(Language language) {
		this.language = language;
		this.setText(language.name());
		setOnAction(e -> {
			LanguageSelector selector = new LanguageSelector();
			selector.selectLanguage(language);
		});
	}
}
