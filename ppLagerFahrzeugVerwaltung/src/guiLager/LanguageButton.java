package guiLager;

import application.Language;
import application.LanguageSelector;
import javafx.scene.control.Button;

public class LanguageButton extends Button {
	
	Language language;
	
	public LanguageButton(Language language) {
		this.language = language;
		setOnAction(e -> {
			LanguageSelector selector = new LanguageSelector();
			selector.selectLanguage(language);
		});
	}
}
