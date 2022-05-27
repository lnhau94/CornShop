package Store.POS.Control;

import Store.POS.Model.PosModel;
import Store.POS.View.PosView;

public class PosController {

    private PosModel model;
    private PosView view;

    public PosController(){
        this.model = new PosModel();
        this.view = new PosView(model);
    }

    public PosModel getModel() {
        return model;
    }

    public PosView getView() {
        return view;
    }
}
