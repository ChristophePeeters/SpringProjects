package be.vdab.retrovideo.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    public final Set<Integer> ids = new LinkedHashSet<>();

    public void toevoegen(int id) {
        ids.add(id);
    }

    public boolean bevat(int id) {
        return ids.contains(id);
    }

    public boolean isNietLeeg() {
        return !ids.isEmpty();
    }

    public Set<Integer> getIds() {
        return ids;
    }

    public int getSize() {
        return ids.size();
    }

    public void clear(){
        ids.clear();
    }

    public void verwijderen(int id){
        ids.remove(id);
    }
}
