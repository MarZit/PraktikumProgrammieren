package languageSupport;

import java.util.ResourceBundle;

public class LanguageTest {
	
	public static void main(String[] args) {
		ResourceBundle language_de = new LanguageResource_de();
		ResourceBundle language_en = new LanguageResource_en();
		
		
		System.out.println(language_de.getString("saveKey"));
		System.out.println(language_en.getString("saveKey"));
		
		
		
	}

}
