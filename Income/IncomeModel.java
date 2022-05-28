package Income;


import Entity.Entity.IncomeBill;
import Entity.Entity.IncomeDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IncomeModel {
    public IncomeBill currentOrder;
    public ObservableList<IncomeDetails> currentChoice;

    public void createNewOrder(){
        this.currentOrder = new IncomeBill();
        currentChoice = FXCollections.observableArrayList();
    }

    public ObservableList<IncomeDetails> getCurrentChoice() {
        return currentChoice;
    }

    public boolean addChosenItem(IncomeDetails incomeDetail) {
        int i = 0;
        for (IncomeDetails id : this.getCurrentChoice()) {
            if(id.getStorageId() == incomeDetail.getStorageId()) {
                id.setQuantity(id.getQuantity() + 1);
                currentChoice.set(i, id);
                return true;
            }
            i++;
        }
        this.getCurrentChoice().add(incomeDetail);
        return true;
    }

    public void removeChosenItem(IncomeDetails incomeDetail) {
        this.getCurrentChoice().remove(incomeDetail);
    }

    public IncomeBill getCurrentOrder() {
        return currentOrder;
    }
}
