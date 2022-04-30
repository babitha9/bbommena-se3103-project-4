package model.observerPattern;

import model.EnemyComposite;

public interface Subject {
    public void addListinor(Observer o);
    public void removeListnor(Observer o);
    public void notifyListners(EnemyComposite.Event event);

}
