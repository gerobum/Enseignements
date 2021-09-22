
package fr.miage.metier.livre;

import java.util.List;

/**
 *
 * @author yvan
 */
public interface IBook {
    String getTitle();
    List<IAuthor> getAuthors();
    String getAbstract();
    String getISBN();
    int getYearOfPublication();
}
