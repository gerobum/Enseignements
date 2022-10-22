/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.core.models;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.core.service.CustomerService;
import java.util.List;

/**
 *
 * @author yvan
 */

// Une sorte de modèle pour l'affichage d'une liste de clients, par catégorie ou non.
/**
 * L'état d'une liste de clients (pour la vue customer/list.html)
 *
 * Il est calculé à partir d'un nom de catégorie et grâce à l'accès à la BDD.
 *
 * Quatre états sont possibles
 *
 * 1. ALL => Dans ce cas, il s'agit de la liste de tous les clients
 *
 * 2. NONE => Cela signifie que cette catégorie n'existe pas.
 *
 * 3. OK => La catégorie existe, categoryName est pertinent. Et il s'agit de la
 * liste des clients de catégorie <categoryName>
 * 
 * 4. WITHOUT => Il s'agit des clients sans catégorie.
 *
 * @author yvan
 */
public class CustomerListStatus {

    public static enum State {
        ALL, NONE, OK, WITHOUT
    }
    private State state;
    private String categoryName;
    private CategoryService categoryService;
    private CustomerService customerService;
    private List<Customer> customers = null;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CustomerListStatus(CustomerService customerService, CategoryService categoryService, String categoryName) {
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.categoryName = categoryName;
        if (categoryName == null || "".equals(categoryName.trim())) {
            state = State.ALL;
            customers = customerService.findAll();
        } else if (categoryName.toUpperCase().startsWith("WITHOUT")) {
                state = State.WITHOUT;
                customers = customerService.findByCategoryOrderByName((Category)null);
        } else {
            if (categoryService.findByName(categoryName) == null) {
                state = State.NONE;
            } else {
                state = State.OK;
                customers = customerService.findByCategoryOrderByName(categoryName);
            }
        }
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public CustomerListStatus() {
        this(null, null, "");
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

}
