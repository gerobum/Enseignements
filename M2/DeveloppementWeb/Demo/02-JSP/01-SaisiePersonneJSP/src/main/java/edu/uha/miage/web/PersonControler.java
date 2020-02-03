
package edu.uha.miage.web;

import edu.uha.miage.metier.Person;
import edu.uha.miage.model.PersonFormModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SaisiePersonne", urlPatterns = {"/SaisiePersonne", "/sp"})
public class PersonControler extends HttpServlet {

    private PersonFormModel model = new PersonFormModel();

    /*
    En réponse à une requête HTTP/GET, le modèle courant est ajouté à la requête
    et cette requête est forwardé au formulaire de saisie.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", model);
        request.getRequestDispatcher("/WEB-INF/PersonForm.jsp").forward(request, response);
    }

    /*
    En réponse à une requête HTTP/POST, c'est-à-dire probablement après avoir
    validé le formulaire lancé depuis doGet(), le modèle courant est calculé
    à partir de la requête qui contient les informations saisies. Il est à 
    nouveau ajouté à la requête et forwardé au formulaire de saisie.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        model = PersonFormModel.handle(request);
        request.setAttribute("model", model);
        request.getRequestDispatcher("/WEB-INF/PersonForm.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        read();
    }

    @Override
    public void destroy() {
        super.destroy();
        write();
    }

    private void read() {
        Person personne;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.bin"))) {
            personne = (Person) in.readObject();
            model.setPerson(personne);
        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
        }
    }

    private void write() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.bin"))) {
            if (model.isAgeOk()) {
                out.writeObject(model.getPerson());
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}
