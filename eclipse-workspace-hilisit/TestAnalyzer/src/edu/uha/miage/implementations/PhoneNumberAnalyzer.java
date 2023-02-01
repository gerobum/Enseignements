package edu.uha.miage.implementations;

import edu.uha.miage.interfaces.TextAnalyzer;

public class PhoneNumberAnalyzer implements TextAnalyzer {

	public String getDescription() {
		return "10 chiffres";
	}

	public boolean isValid(String text) {
		return text != null && text.length() == 10 && fullOfDigit(text);
	}

	private boolean fullOfDigit(String text) {
		return text.chars().allMatch(Character::isDigit);
	}

}
