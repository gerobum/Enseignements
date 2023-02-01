package edu.uha.miage.implementations;

import edu.uha.miage.interfaces.TextAnalyzer;

public class EmailAddressAnalyzer implements TextAnalyzer {

	@Override
	public String getDescription() {
		return "deux mots séparés par un arobas";
	}

	@Override
	public boolean isValid(String text) {
		return text != null && text.length() > 2 && text.matches("[a-z.A-Z]+@[a-z.A-Z]+");
	}

}
