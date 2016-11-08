package plus.serveur;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author yvan
 */
public final class InfoDeCom implements Serializable {
    public final InetAddress adresse;
    public final int portDeDiscussion;
    public final int portDeConnectes;
    public final String pseudo;

    public InfoDeCom(String pseudo, InetAddress adresse, int portDeDiscussion, int portDeConnectes) {
        this.pseudo = pseudo;
        this.adresse = adresse;
        this.portDeDiscussion = portDeDiscussion;
        this.portDeConnectes = portDeConnectes;
    }
    
    @Override
    public String toString() {
        return pseudo + "@" + adresse + ":" + portDeDiscussion +","+portDeConnectes;
    }
}
